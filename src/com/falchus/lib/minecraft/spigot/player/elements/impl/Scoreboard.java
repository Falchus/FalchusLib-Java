package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.falchus.lib.FalchusLib;
import com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.display.objective.WrappedPacketOutScoreboardDisplayObjective;
import com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.objective.WrappedPacketOutScoreboardObjective;
import com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.score.WrappedPacketOutScoreboardScore;
import com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.team.WrappedPacketOutScoreboardTeam;
import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.ScoreboardObjective;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.ScoreboardScore;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.ScoreboardTeam;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.WrappedScoreboard;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.WrappedScoreboardScore;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.criteria.WrappedScoreboardCriteria;

import lombok.NonNull;

public class Scoreboard extends PlayerElement {

	private BiFunction<Integer, Player, String> titleSupplier;
	private BiFunction<Integer, Player, List<String>> linesSupplier;
	
	private List<String> lastLines;
	
	private final com.falchus.lib.minecraft.spigot.wrapper.world.scores.Scoreboard scoreboard;
	private final ScoreboardObjective objective;
	
	private Scoreboard(@NonNull Player player) {
		super(player, true);
		scoreboard = new WrappedScoreboard();
		objective = scoreboard.registerObjective(FalchusLib.nameFull, WrappedScoreboardCriteria.dummy());
		PlayerUtils.sendPacket(player, new WrappedPacketOutScoreboardObjective(objective, 0));
		PlayerUtils.sendPacket(player, new WrappedPacketOutScoreboardDisplayObjective(WrappedPacketOutScoreboardDisplayObjective.DisplaySlot.SIDEBAR, objective));
	}
	
	public void send(@NonNull BiFunction<Integer, Player, String> title, @NonNull BiFunction<Integer, Player, List<String>> lines) {
		titleSupplier = title;
		linesSupplier = lines;
		
		updateRunnable = () -> {
			String newTitle = titleSupplier.apply(frame, player);
			if (newTitle.length() > 32) {
				newTitle = newTitle.substring(0, 32);
			}
			objective.setDisplayName(newTitle);
			PlayerUtils.sendPacket(player, new WrappedPacketOutScoreboardObjective(objective, 2));
			
			List<String> newLines = linesSupplier.apply(frame, player);
			int lastSize = lastLines == null ? 0 : lastLines.size();
			int newSize = newLines.size();
			if (lastSize > newSize) {
				for (int i = newSize + 1; i <= lastSize; i++) {
					ScoreboardTeam team = scoreboard.getTeam(getClass().getSimpleName() + "_" + i);
					if (team != null) {
						PlayerUtils.sendPacket(player, new WrappedPacketOutScoreboardTeam(team, WrappedPacketOutScoreboardTeam.Mode.REMOVE));
					}
					PlayerUtils.sendPacket(player, new WrappedPacketOutScoreboardScore("§" + Integer.toHexString(i), objective));
				}
			}
			lastLines = newLines;
			
		    int score = newLines.size();
		    for (String line : newLines) {
		        if (line.isEmpty()) {
		        	line = "§r";
		        } else if (line.length() > 32) {
		            line = line.substring(0, 32);
		        }
		
		        String teamName = getClass().getSimpleName() + "_" + score;
		        ScoreboardTeam team = scoreboard.getTeam(teamName);
		        boolean create = team == null;
		        if (create) {
		        	team = scoreboard.createTeam(teamName);
		        }
		        
		        String entry = "§" + Integer.toHexString(score);
		        if (!team.getPlayers().contains(entry)) {
		        	scoreboard.addPlayerToTeam(entry, team);
		        }
		        
		        int maxLength = 16;
		        String prefix = line;
		        String suffix = "";
		        if (prefix.length() > 16) {
		        	int index = line.charAt(maxLength - 1) == ChatColor.COLOR_CHAR
		        			? (maxLength - 1) : maxLength;
		        	prefix = line.substring(0, index);
		        	String suffixTmp = line.substring(index);
		        	ChatColor chatColor = null;
		        	
		        	if (suffixTmp.length() >= 2 && suffixTmp.charAt(0) == ChatColor.COLOR_CHAR) {
		        		chatColor = ChatColor.getByChar(suffixTmp.charAt(1));
		        	}
		        	
		        	String color = ChatColor.getLastColors(prefix);
		        	boolean addColor = chatColor == null || chatColor.isFormat();
		        	suffix = (addColor ? (color.isEmpty() ? ChatColor.RESET.toString() : color) : "") + suffixTmp;
		        }
		        team.setPrefix(prefix);
		        team.setSuffix(suffix);
		        
		        if (create || score > lastSize) {
					PlayerUtils.sendPacket(player, new WrappedPacketOutScoreboardTeam(team, WrappedPacketOutScoreboardTeam.Mode.CREATE));
				} else {
					PlayerUtils.sendPacket(player, new WrappedPacketOutScoreboardTeam(team, WrappedPacketOutScoreboardTeam.Mode.UPDATE));
				}
		        
		        ScoreboardScore sbScore = new WrappedScoreboardScore(scoreboard, objective, entry);
		        sbScore.setScore(score);
		        PlayerUtils.sendPacket(player, new WrappedPacketOutScoreboardScore(sbScore));
		        score--;
		    }
		};
		update();
	}
	
	public void send(@NonNull Supplier<String> title, @NonNull Supplier<List<String>> lines) {
		send(
			(frame, player) -> title.get(),
			(frame, player) -> lines.get()
		);
	}

	public void sendUpdating(long intervalTicks, @NonNull BiFunction<Integer, Player, String> title, @NonNull BiFunction<Integer, Player, List<String>> lines) {
		super.sendUpdating(intervalTicks, () ->
			send(
				title,
				lines
			)
		);
	}
	
	public void sendUpdating(long intervalTicks, @NonNull Supplier<String> title, @NonNull Supplier<List<String>> lines) {
		sendUpdating(intervalTicks,
			(frame, player) -> title.get(),
			(frame, player) -> lines.get()
		);
	}
	
	public void remove() {
		super.remove();
		
		lastLines = null;
		
		PlayerUtils.sendPacket(player, new WrappedPacketOutScoreboardObjective(objective, 1));
		scoreboard.unregisterObjective(objective);
	}
	
	public void setTitle(@NonNull String title) {
		send(
			(frame, player) -> title,
			linesSupplier
		);
	}
	
	public void setLines(@NonNull List<String> lines) {
		send(
			titleSupplier,
			(frame, player) -> lines
		);
	}
}

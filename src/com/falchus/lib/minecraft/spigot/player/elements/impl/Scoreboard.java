package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Team;

import com.falchus.lib.FalchusLib;
import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;

import lombok.NonNull;

public class Scoreboard extends PlayerElement {

	private BiFunction<Integer, Player, List<String>> linesSupplier;
	
	private List<String> lastLines;
	
	public final org.bukkit.scoreboard.Scoreboard scoreboard;
	private final Objective objective;
	
	private Scoreboard(@NonNull Player player) {
		super(player);
		org.bukkit.scoreboard.Scoreboard scoreboard = player.getScoreboard();
		if (scoreboard == null || scoreboard == Bukkit.getScoreboardManager().getMainScoreboard()) {
			scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
		}
		
        Objective objective = scoreboard.getObjective(FalchusLib.nameFull);
        if (objective == null) {
        	objective = scoreboard.registerNewObjective(FalchusLib.nameFull, "dummy");
        }
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        
        player.setScoreboard(scoreboard);
        
		this.scoreboard = scoreboard;
        this.objective = objective;
	}
	
	public void send(@NonNull BiFunction<Integer, Player, List<String>> lines) {
		linesSupplier = lines;
		
		updateRunnable = () -> {
			List<String> newLines = linesSupplier.apply(frame, player);
			
			if (lastLines == null || lastLines.size() != newLines.size()) {
				for (Team team : scoreboard.getTeams()) {
					team.unregister();
				}
				for (String entry : scoreboard.getEntries()) {
					scoreboard.resetScores(entry);
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
		        Team team = scoreboard.getTeam(teamName);
		        if (team == null) {
		        	team = scoreboard.registerNewTeam(teamName);
		        }
		        
		        String entry = "§" + Integer.toHexString(score);
		        if (!team.hasEntry(entry)) {
		        	team.addEntry(entry);
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
		
		        objective.getScore(entry).setScore(score);
		        score--;
		    }
		};
		update();
	}
	
	public void send(@NonNull Supplier<List<String>> lines) {
		send(
			(frame, player) -> lines.get()
		);
	}

	public void sendUpdating(long intervalTicks, @NonNull BiFunction<Integer, Player, String> title, @NonNull BiFunction<Integer, Player, List<String>> lines) {
		super.sendUpdating(intervalTicks, () ->
			send(
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
		
		player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
	}
	
	public void setTitle(@NonNull String title) {
		if (title.length() > 32) {
			title = title.substring(0, 32);
		}
		objective.setDisplayName(title);
	}
}

package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.function.Supplier;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.enums.ScoreboardRenderType;
import com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.display.objective.WrappedPacketOutScoreboardDisplayObjective;
import com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.objective.WrappedPacketOutScoreboardObjective;
import com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.score.WrappedPacketOutScoreboardScore;
import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.Scoreboard;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.ScoreboardObjective;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.ScoreboardScore;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.WrappedScoreboard;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.WrappedScoreboardScore;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.criteria.WrappedScoreboardCriteria;

import lombok.NonNull;

public class TablistObjective extends PlayerElement {
	
	private ScoreboardRenderType renderType;
	private Supplier<String> titleSupplier;
	private Supplier<Integer> scoreSupplier;
	
	private final Scoreboard scoreboard;
	private final ScoreboardObjective objective;

	private TablistObjective(@NonNull Player player) {
		super(player, true);
		scoreboard = new WrappedScoreboard();
		objective = scoreboard.registerObjective(getClass().getSimpleName(), WrappedScoreboardCriteria.dummy());
		PlayerUtils.sendPacket(player, new WrappedPacketOutScoreboardObjective(objective, 0));
		PlayerUtils.sendPacket(player, new WrappedPacketOutScoreboardDisplayObjective(WrappedPacketOutScoreboardDisplayObjective.DisplaySlot.LIST, objective));
	}
	
	public void send(@NonNull ScoreboardRenderType renderType, @NonNull Supplier<String> title, @NonNull Supplier<Integer> score) {
		this.renderType = renderType;
		titleSupplier = title;
		scoreSupplier = score;
		
		updateRunnable = () -> {
			String newTitle = titleSupplier.get();
			objective.setDisplayName(newTitle);
			
			WrappedPacketOutScoreboardObjective objectivePacket = new WrappedPacketOutScoreboardObjective(objective, 2);
			objectivePacket.setRenderType(renderType);
			
			int newScore = scoreSupplier.get();
			ScoreboardScore sbScore = new WrappedScoreboardScore(scoreboard, objective, player.getName());
			sbScore.setScore(newScore);
			WrappedPacketOutScoreboardScore scorePacket = new WrappedPacketOutScoreboardScore(sbScore);
			for (Player p : Bukkit.getOnlinePlayers()) {
				PlayerUtils.sendPacket(p, objectivePacket);
				PlayerUtils.sendPacket(p, scorePacket);
			}
		};
		update();
	}
	
	public void sendUpdating(long intervalTicks, @NonNull ScoreboardRenderType renderType, @NonNull Supplier<String> title, @NonNull Supplier<Integer> score) {
		super.sendUpdating(intervalTicks, () ->
			send(
				renderType,
				title,
				score
			)
		);
	}
	
	@Override
	public void remove() {
		super.remove();
		
		WrappedPacketOutScoreboardScore removePacket = new WrappedPacketOutScoreboardScore(player.getName(), objective);
		for (Player p : Bukkit.getOnlinePlayers()) {
			PlayerUtils.sendPacket(p, removePacket);
		}
		
		PlayerUtils.sendPacket(player, new WrappedPacketOutScoreboardObjective(objective, 1));
		scoreboard.unregisterObjective(objective);
	}
	
	public void setRenderType(@NonNull ScoreboardRenderType renderType) {
		send(
			renderType,
			titleSupplier,
			scoreSupplier
		);
	}
	
	public void setTitle(@NonNull String title) {
		send(
			renderType,
			() -> title,
			scoreSupplier
		);
	}
	
	public void setScore(int score) {
		send(
			renderType,
			titleSupplier,
			() -> score
		);
	}
}

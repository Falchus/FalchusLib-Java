package com.falchus.lib.minecraft.spigot.wrapper.world.scores;

import java.util.Collection;

import com.falchus.lib.minecraft.spigot.wrapper.ISpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.criteria.ScoreboardCriteria;

import lombok.NonNull;

public interface Scoreboard extends ISpigotWrapper {

	ScoreboardObjective registerObjective(@NonNull String name, @NonNull ScoreboardCriteria criteria);
	
	Collection<String> getPlayers();
	
	void unregisterObjective(@NonNull ScoreboardObjective objective);
	
	ScoreboardTeam getTeam(@NonNull String name);
	Collection<ScoreboardTeam> getTeams();
	ScoreboardTeam createTeam(@NonNull String name);
	boolean addPlayerToTeam(@NonNull String name, @NonNull ScoreboardTeam team);
}

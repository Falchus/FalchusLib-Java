package com.falchus.lib.minecraft.spigot.wrapper.world.scores;

import java.util.Set;

public interface ScoreboardTeam extends ScoreboardObjective {

	String getName();
	
	Set<String> getPlayers();
	
	/**
	 * <1.13: {@link String}
	 * 1.13+: IChatBaseComponent
	 */
	Object getPrefix();
	void setPrefix(String prefix);
	
	/**
	 * <1.13: {@link String}
	 * 1.13+: IChatBaseComponent
	 */
	Object getSuffix();
	void setSuffix(String suffix);
}

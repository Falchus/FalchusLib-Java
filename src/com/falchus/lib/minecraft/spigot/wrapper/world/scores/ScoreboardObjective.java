package com.falchus.lib.minecraft.spigot.wrapper.world.scores;

import com.falchus.lib.minecraft.spigot.wrapper.ISpigotWrapper;

public interface ScoreboardObjective extends ISpigotWrapper { // TODO

	/**
	 * <1.13: {@link String}
	 * 1.13+: IChatBaseComponent
	 */
	Object getDisplayName();
	void setDisplayName(String displayName);
}

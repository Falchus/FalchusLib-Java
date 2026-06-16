package com.falchus.lib.minecraft.spigot.wrapper.world.scores;

import com.falchus.lib.minecraft.spigot.wrapper.ISpigotWrapper;

public interface ScoreboardScore extends ISpigotWrapper { // TODO
	
	int getScore();
	void setScore(int score);
}

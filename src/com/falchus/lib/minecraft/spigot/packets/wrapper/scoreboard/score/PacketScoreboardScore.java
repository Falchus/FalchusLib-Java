package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.score;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketScoreboardScore extends IPacketWrapper {

	String getOwner();
	void setOwner(String owner);
	
	String getObjectiveName();
	void setObjectiveName(String objectiveName);
	
	int getScore();
	void setScore(int score);
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.server.difficulty;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketServerDifficulty extends IPacketWrapper {

	enum Difficulty {
		PEACEFUL,
		EASY,
		NORMAL,
		HARD
	}
	Difficulty getDifficulty();
	void setDifficulty(Difficulty difficulty);
	
	boolean isLocked();
	void setLocked(boolean locked);
}

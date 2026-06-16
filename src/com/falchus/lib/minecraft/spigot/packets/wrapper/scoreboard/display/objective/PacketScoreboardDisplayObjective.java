package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.display.objective;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketScoreboardDisplayObjective extends IPacketWrapper {

	String getObjectiveName();
	void setObjectiveName(String objectiveName);
}

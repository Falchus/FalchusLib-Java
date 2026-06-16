package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.objective;

import com.falchus.lib.minecraft.spigot.enums.ScoreboardRenderType;
import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketScoreboardObjective extends IPacketWrapper {

	String getObjectiveName();
	void setObjectiveName(String objectiveName);
	
	ScoreboardRenderType getRenderType();
	void setRenderType(ScoreboardRenderType renderType);
	
	int getMethod();
	void setMethod(int method);
}

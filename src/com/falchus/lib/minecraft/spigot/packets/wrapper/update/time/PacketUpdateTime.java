package com.falchus.lib.minecraft.spigot.packets.wrapper.update.time;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketUpdateTime extends IPacketWrapper {

	long getGameTime();
	void setGameTime(long gameTime);
	
	long getDayTime();
	void setDayTime(long dayTime);
}

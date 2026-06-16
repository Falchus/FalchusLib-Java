package com.falchus.lib.minecraft.spigot.packets.wrapper.gamestatechange;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketGameStateChange extends IPacketWrapper {

	float getParam();
	void setParam(float param);
}

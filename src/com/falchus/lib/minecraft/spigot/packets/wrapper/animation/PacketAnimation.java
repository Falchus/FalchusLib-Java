package com.falchus.lib.minecraft.spigot.packets.wrapper.animation;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketAnimation extends IPacketWrapper {

	int getId();
	void setId(int id);
	
	int getAction();
	void setAction(int action);
}

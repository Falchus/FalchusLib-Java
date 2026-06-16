package com.falchus.lib.minecraft.spigot.packets.wrapper.position;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketPosition extends IPacketWrapper {
	
	boolean hasDestination();
	
	double getX();
	double getY();
	double getZ();
	float getYaw();
	float getPitch();
}

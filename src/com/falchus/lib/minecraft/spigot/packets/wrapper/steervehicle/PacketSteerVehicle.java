package com.falchus.lib.minecraft.spigot.packets.wrapper.steervehicle;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketSteerVehicle extends IPacketWrapper {

	float getSideways();
	void setSideways(float sideways);
	
	float getForward();
	void setForward(float forward);
	
	boolean isJumping();
	void setJumping(boolean jumping);
	
	boolean isSneaking();
	void setSneaking(boolean sneaking);
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.flying;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketFlying extends IPacketWrapper {

	double getX();
	void setX(double x);
	
	double getY();
	void setY(double y);
	
	double getZ();
	void setZ(double z);
	
	float getYaw();
	void setYaw(float yaw);
	
	float getPitch();
	void setPitch(float pitch);
	
	boolean isOnGround();
	void setOnGround(boolean onGround);
	
	boolean isHasPos();
	void setHasPos(boolean hasPos);
	
	boolean isHasRot();
	void setHasRot(boolean hasRot);
}

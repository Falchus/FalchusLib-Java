package com.falchus.lib.minecraft.spigot.packets.wrapper.update.health;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketUpdateHealth extends IPacketWrapper {

	float getHealth();
	void setHealth(float health);
	
	int getFood();
	void setFood(int food);
	
	float getSaturation();
	void setSaturation(int saturation);
}

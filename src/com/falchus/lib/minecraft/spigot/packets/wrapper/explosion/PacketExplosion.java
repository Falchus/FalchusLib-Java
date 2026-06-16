package com.falchus.lib.minecraft.spigot.packets.wrapper.explosion;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketExplosion extends IPacketWrapper {

	float getRadius();
	void setRadius(float radius);
}

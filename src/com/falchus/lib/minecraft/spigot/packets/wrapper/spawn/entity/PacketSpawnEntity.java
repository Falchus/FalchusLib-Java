package com.falchus.lib.minecraft.spigot.packets.wrapper.spawn.entity;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketSpawnEntity extends IPacketWrapper {

	int getId();
	void setId(int id);
}

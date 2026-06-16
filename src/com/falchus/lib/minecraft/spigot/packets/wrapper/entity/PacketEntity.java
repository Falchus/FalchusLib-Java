package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketEntity extends IPacketWrapper {

	int getEntityId();
	void setEntityId(int entityId);
}

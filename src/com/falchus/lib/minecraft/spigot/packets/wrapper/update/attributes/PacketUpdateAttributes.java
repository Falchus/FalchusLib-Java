package com.falchus.lib.minecraft.spigot.packets.wrapper.update.attributes;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketUpdateAttributes extends IPacketWrapper {

	int getEntityId();
	void setPos(int entityId);
}

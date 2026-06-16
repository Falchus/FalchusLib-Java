package com.falchus.lib.minecraft.spigot.packets.wrapper.attachentity;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketAttachEntity extends IPacketWrapper {

	int getSourceId();
	void setSourceId(int sourceId);
	
	int getDestId();
	void setDestId(int destId);
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.removeentityeffect;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketRemoveEntityEffect extends IPacketWrapper {

	int getEntityId();
	void setEntityId(int entityId);
}

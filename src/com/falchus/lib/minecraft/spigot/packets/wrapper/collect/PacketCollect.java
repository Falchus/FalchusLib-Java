package com.falchus.lib.minecraft.spigot.packets.wrapper.collect;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketCollect extends IPacketWrapper {

	int getItemId();
	void setItemId(int itemId);
	
	int getPlayerId();
	void setPlayerId(int playerId);
}

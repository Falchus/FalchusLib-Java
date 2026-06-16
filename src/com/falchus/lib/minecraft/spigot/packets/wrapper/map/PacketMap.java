package com.falchus.lib.minecraft.spigot.packets.wrapper.map;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketMap extends IPacketWrapper {

	byte getScale();
	void setScale(byte scale);
}

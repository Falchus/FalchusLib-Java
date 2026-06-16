package com.falchus.lib.minecraft.spigot.packets.wrapper.spectate;

import java.util.UUID;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketSpectate extends IPacketWrapper {

	UUID getUUID();
	void setUUID(int uuid);
}

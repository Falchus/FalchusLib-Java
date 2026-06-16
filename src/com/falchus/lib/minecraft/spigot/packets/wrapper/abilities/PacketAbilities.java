package com.falchus.lib.minecraft.spigot.packets.wrapper.abilities;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketAbilities extends IPacketWrapper {
	
	boolean isFlying();
	void setFlying(boolean isFlying);
}

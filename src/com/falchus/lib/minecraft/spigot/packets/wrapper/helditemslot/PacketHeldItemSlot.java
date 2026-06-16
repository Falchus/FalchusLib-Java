package com.falchus.lib.minecraft.spigot.packets.wrapper.helditemslot;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;

public interface PacketHeldItemSlot extends IPacketWrapper {

	int getSlot();
	void setSlot(int slot);
}

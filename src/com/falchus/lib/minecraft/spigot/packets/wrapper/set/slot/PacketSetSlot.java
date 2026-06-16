package com.falchus.lib.minecraft.spigot.packets.wrapper.set.slot;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.world.item.ItemStack;

public interface PacketSetSlot extends IPacketWrapper {

	int getContainerId();
	void setContainerId(int containerId);
	
	int getSlot();
	void setSlot(int slot);
	
	ItemStack getItemStack();
	void setItemStack(ItemStack itemStack);
}

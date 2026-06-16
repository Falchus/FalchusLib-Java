package com.falchus.lib.minecraft.spigot.packets.wrapper.set.creativeslot;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.world.item.ItemStack;

public interface PacketSetCreativeSlot extends IPacketWrapper {

	ItemStack getItemStack();
	void setItemStack(ItemStack itemStack);
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.set.creativeslot;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.world.item.ItemStack;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketSetCreativeSlotWrapper extends PacketWrapper implements PacketSetCreativeSlot {
	
	Field itemStack;

	PacketSetCreativeSlotWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		itemStack = getFirstField(
			"itemStack",
			"b"
		);
	}

	@Override
	public ItemStack getItemStack() {
		return SpigotWrapper.wrap(getFieldValue(itemStack));
	}
	
	@Override
	public void setItemStack(ItemStack itemStack) {
		setField(this.itemStack, itemStack.getHandle());
	}
}

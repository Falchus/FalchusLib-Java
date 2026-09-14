package com.falchus.lib.minecraft.spigot.packets.wrapper.set.slot;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.world.item.ItemStack;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
<<<<<<< HEAD
class PacketSetSlotWrapper extends PacketWrapper implements PacketSetSlot {
=======
class PacketSetSlotWrapper extends PacketWrapper {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
	Field containerId;
	Field slot;
	Field itemStack;

	PacketSetSlotWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		containerId = getFirstField(
			"containerId",
			"a"
		);
		slot = getFirstField(
			"slot",
			"b"
		);
		itemStack = getFirstField(
			"itemStack",
			"c"
		);
	}

	@Override
	public int getContainerId() {
		return getFieldValue(containerId);
	}
	
	@Override
	public void setContainerId(int containerId) {
		setField(this.containerId, containerId);
	}

	@Override
	public int getSlot() {
		return getFieldValue(slot);
	}
	
	@Override
	public void setSlot(int slot) {
		setField(this.slot, slot);
	}

	@Override
	public ItemStack getItemStack() {
		return SpigotWrapper.wrap(getFieldValue(itemStack));
	}
	
<<<<<<< HEAD
	@Override
	public void setItemStack(ItemStack itemStack) {
		setField(this.itemStack, itemStack.getHandle());
=======
	/**
	 * @param itemStack	ItemStack
	 */
	public void setItemStack(Object itemStack) {
		setField(this.itemStack, itemStack);
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}

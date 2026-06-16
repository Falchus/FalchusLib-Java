package com.falchus.lib.minecraft.spigot.packets.wrapper.helditemslot;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketHeldItemSlotWrapper extends PacketWrapper implements PacketHeldItemSlot {
	
	Field slot;

	PacketHeldItemSlotWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		slot = getFirstField(
			"slot",
			"a"
		);
	}

	@Override
	public int getSlot() {
		return getFieldValue(slot);
	}
	
	@Override
	public void setSlot(int slot) {
		setField(this.slot, slot);
	}
}

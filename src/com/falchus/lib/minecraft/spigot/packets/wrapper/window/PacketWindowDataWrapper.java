package com.falchus.lib.minecraft.spigot.packets.wrapper.window;

import java.lang.reflect.Field;
import java.util.Set;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketWindowDataWrapper extends PacketWindowWrapper implements PacketWindowData {
	
	Field id;
	Field value;

	PacketWindowDataWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		id = getFirstField(
			"id",
			"b"
		);
		value = getFirstField(
			"value",
			"c"
		);
	}

	@Override
	public int getId() {
		return getFieldValue(id);
	}
	
	@Override
	public void setId(int id) {
		setField(this.id, id);
	}

	@Override
	public int getValue() {
		return getFieldValue(value);
	}
	
	@Override
	public void setValue(int value) {
		setField(this.value, value);
	}
}

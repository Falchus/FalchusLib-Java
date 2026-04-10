package com.falchus.lib.minecraft.spigot.packets.wrapper.animation;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketAnimationWrapper extends PacketWrapper {
	
	Field id;
	Field action;

	PacketAnimationWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		id = getFirstField(
			"id",
			"a"
		);
		action = getFirstField(
			"action",
			"b"
		);
	}

	public int getId() {
		return getFieldValue(id);
	}
	
	public void setId(int id) {
		setField(this.id, id);
	}

	public int getAction() {
		return getFieldValue(action);
	}
	
	public void setAction(int action) {
		setField(this.action, action);
	}
}

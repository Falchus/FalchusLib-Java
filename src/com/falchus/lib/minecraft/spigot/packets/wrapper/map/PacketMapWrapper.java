package com.falchus.lib.minecraft.spigot.packets.wrapper.map;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketMapWrapper extends PacketWrapper {
	
	Field scale;

	PacketMapWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		scale = getFirstField(
			"scale",
			"b"
		);
	}

	public byte getScale() {
		return getFieldValue(scale);
	}
	
	public void setScale(byte scale) {
		setField(this.scale, scale);
	}
}

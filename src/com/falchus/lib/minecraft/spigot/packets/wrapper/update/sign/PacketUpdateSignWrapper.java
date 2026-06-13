package com.falchus.lib.minecraft.spigot.packets.wrapper.update.sign;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketUpdateSignWrapper extends PacketWrapper {
	
	Field pos;

	PacketUpdateSignWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		pos = getFirstField(
			"pos",
			"a"
		);
	}

	/**
	 * @return BlockPosition
	 */
	public Object getPos() {
		return getFieldValue(pos);
	}
	
	/**
	 * @param pos	BlockPosition
	 */
	public void setPos(Object pos) {
		setField(this.pos, pos);
	}
}

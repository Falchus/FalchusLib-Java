package com.falchus.lib.minecraft.spigot.packets.wrapper.gamestatechange;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketGameStateChangeWrapper extends PacketWrapper {
	
	Field param;

	PacketGameStateChangeWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		param = getFirstField(
			"param",
			"c"
		);
	}

	public float getParam() {
		return getFieldValue(param);
	}
	
	public void setParam(float param) {
		setField(this.param, param);
	}
}

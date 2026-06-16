package com.falchus.lib.minecraft.spigot.packets.wrapper.position;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketPositionWrapper extends PacketWrapper implements PacketPosition {
	
	Field x;
	Field y;
	Field z;
	Field yaw;
	Field pitch;
	Field relatives;

	// TODO: multi-version
	PacketPositionWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		x = getFirstField(
			"x",
			"a"
		);
		y = getFirstField(
			"y",
			"b"
		);
		z = getFirstField(
			"z",
			"c"
		);
		yaw = getFirstField(
			"yRot",
			"d"
		);
		pitch = getFirstField(
			"xRot",
			"e"
		);
		relatives = getFirstField(
			"relatives",
			"relativeArguments",
			"f"
		);
	}
	
	@Override
	public boolean hasDestination() {
		if (x == null || y == null || z == null || yaw == null || pitch == null) return false;
		if (relatives == null) return false;
		
		Object value = getFieldValue(relatives);
		return !(value instanceof Set<?> set) || set.isEmpty();
	}
	
	@Override
	public double getX() {
		return getFieldValue(x);
	}
	
	@Override
	public double getY() {
		return getFieldValue(y);
	}
	
	@Override
	public double getZ() {
		return getFieldValue(z);
	}
	
	@Override
	public float getYaw() {
		return getFieldValue(yaw);
	}
	
	@Override
	public float getPitch() {
		return getFieldValue(pitch);
	}
}

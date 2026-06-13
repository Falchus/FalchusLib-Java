package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import java.lang.reflect.Field;
import java.util.Set;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedPacketOutEntityVelocity extends PacketEntityWrapper {
	
	Field x;
	Field y;
	Field z;
	
	private WrappedPacketOutEntityVelocity(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutEntityVelocity",
			networkProtocolGame + "PacketPlayOutEntityVelocity"
		));
		x = getFirstField(
			"xa",
			"b"
		);
		y = getFirstField(
			"ya",
			"c"
		);
		z = getFirstField(
			"za",
			"d"
		);
	}
	
	public int getX() {
		return getFieldValue(x);
	}
	
	public void setX(int x) {
		setField(this.x, x);
	}
	
	public int getY() {
		return getFieldValue(y);
	}
	
	public void setY(int y) {
		setField(this.y, y);
	}
	
	public int getZ() {
		return getFieldValue(z);
	}
	
	public void setZ(int z) {
		setField(this.z, z);
	}
}

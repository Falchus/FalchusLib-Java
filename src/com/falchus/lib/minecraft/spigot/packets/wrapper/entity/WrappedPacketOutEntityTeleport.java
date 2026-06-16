package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedPacketOutEntityTeleport extends PacketEntityWrapper {
	
	private static final double unit = ServerUtils.getVersion().isBefore(Version.v1_9) ? 1 / 32D : 1;

	Field x;
	Field y;
	Field z;
	Field onGround;
	
	private WrappedPacketOutEntityTeleport(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutEntityTeleport",
			networkProtocolGame + "PacketPlayOutEntityTeleport"
		));
		
		x = getFirstField(
			"x",
			"b"
		);
		y = getFirstField(
			"y",
			"c"
		);
		z = getFirstField(
			"z",
			"d"
		);
		onGround = getFirstField(
			"onGround",
			"g"
		);
	}
	
	public double getX() {
		return ((Number) getFieldValue(x)).doubleValue() * unit;
	}
	
	public double getY() {
		return ((Number) getFieldValue(y)).doubleValue() * unit;
	}
	
	public double getZ() {
		return ((Number) getFieldValue(z)).doubleValue() * unit;
	}

	public boolean isOnGround() {
		return getFieldValue(onGround);
	}
	
	public void setOnGround(boolean onGround) {
		setField(this.onGround, onGround);
	}
}

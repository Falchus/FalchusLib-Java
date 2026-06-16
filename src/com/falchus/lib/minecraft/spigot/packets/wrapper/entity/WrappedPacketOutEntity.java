package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedPacketOutEntity extends PacketEntityWrapper {
	
	private static final double unit = ServerUtils.getVersion().isBefore(Version.v1_9) ? 1 / 32D : 1 / 4096D;
	
	Field deltaX;
	Field deltaY;
	Field deltaZ;
	Field yRot;
	Field xRot;
	Field onGround;
	Field hasRot;
	
	private WrappedPacketOutEntity(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutEntity",
			networkProtocolGame + "PacketPlayOutEntity"
		));
		
		deltaX = getFirstField(
			"xa",
			"b"
		);
		deltaY = getFirstField(
			"ya",
			"c"
		);
		deltaZ = getFirstField(
			"za",
			"d"
		);
		yRot = getFirstField(
			"yRot",
			"yHeadRot",
			"e"
		);
		xRot = getFirstField(
			"xRot",
			"f"
		);
		onGround = getFirstField(
			"onGround",
			"g"
		);
		hasRot = getFirstField(
			"hasRot",
			"h"
		);
	}
	
	public double getDeltaX() {
		return ((Number) getFieldValue(deltaX)).doubleValue() * unit;
	}
	
	public double getDeltaY() {
		return ((Number) getFieldValue(deltaY)).doubleValue() * unit;
	}
	
	public double getDeltaZ() {
		return ((Number) getFieldValue(deltaZ)).doubleValue() * unit;
	}

	public byte getYRot() {
		return getFieldValue(yRot);
	}
	
	public void setYRot(byte yRot) {
		setField(this.yRot, yRot);
	}

	public byte getXRot() {
		return getFieldValue(xRot);
	}
	
	public void setXRot(byte xRot) {
		setField(this.xRot, xRot);
	}

	public boolean isOnGround() {
		return getFieldValue(onGround);
	}
	
	public void setOnGround(boolean onGround) {
		setField(this.onGround, onGround);
	}

	public boolean isHasRot() {
		return getFieldValue(hasRot);
	}
	
	public void setHasRot(boolean hasRot) {
		setField(this.hasRot, hasRot);
	}
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.flying;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketFlyingWrapper extends PacketWrapper {

	Field x;
	Field y;
	Field z;
	Field yaw;
	Field pitch;
	Field onGround;
	Field hasPos;
	Field hasRot;
	
	PacketFlyingWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		x = getField("x");
		y = getField("y");
		z = getField("z");
		yaw = getFirstField(
			"yaw",
			"yRot"
		);
		pitch = getFirstField(
			"pitch",
			"xRot"
		);
		onGround = getFirstField(
			"onGround",
			"f"
		);
		hasPos = getField("hasPos");
		hasRot = getFirstField(
			"hasRot",
			"hasLook"
		);
	}

	public double getX() {
		return getFieldValue(x);
	}
	
	public void setX(double x) {
		setField(this.x, x);
	}

	public double getY() {
		return getFieldValue(y);
	}
	
	public void setY(double y) {
		setField(this.y, y);
	}

	public double getZ() {
		return getFieldValue(z);
	}
	
	public void setZ(double z) {
		setField(this.z, z);
	}

	public float getYaw() {
		return getFieldValue(yaw);
	}
	
	public void setYaw(float yaw) {
		setField(this.yaw, yaw);
	}

	public float getPitch() {
		return getFieldValue(pitch);
	}
	
	public void setPitch(float pitch) {
		setField(this.pitch, pitch);
	}

	public boolean isOnGround() {
		return getFieldValue(onGround);
	}
	
	public void setOnGround(boolean onGround) {
		setField(this.onGround, onGround);
	}

	public boolean isHasPos() {
		return getFieldValue(hasPos);
	}
	
	public void setHasPos(boolean hasPos) {
		setField(this.hasPos, hasPos);
	}

	public boolean isHasRot() {
		return getFieldValue(hasRot);
	}
	
	public void setHasRot(boolean hasRot) {
		setField(this.hasRot, hasRot);
	}
}

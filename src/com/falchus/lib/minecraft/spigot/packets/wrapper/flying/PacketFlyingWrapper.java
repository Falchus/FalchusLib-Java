package com.falchus.lib.minecraft.spigot.packets.wrapper.flying;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketFlyingWrapper extends PacketWrapper implements PacketFlying {

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

	@Override
	public double getX() {
		return getFieldValue(x);
	}
	
	@Override
	public void setX(double x) {
		setField(this.x, x);
	}

	@Override
	public double getY() {
		return getFieldValue(y);
	}
	
	@Override
	public void setY(double y) {
		setField(this.y, y);
	}

	@Override
	public double getZ() {
		return getFieldValue(z);
	}
	
	@Override
	public void setZ(double z) {
		setField(this.z, z);
	}

	@Override
	public float getYaw() {
		return getFieldValue(yaw);
	}
	
	@Override
	public void setYaw(float yaw) {
		setField(this.yaw, yaw);
	}

	@Override
	public float getPitch() {
		return getFieldValue(pitch);
	}
	
	@Override
	public void setPitch(float pitch) {
		setField(this.pitch, pitch);
	}

	@Override
	public boolean isOnGround() {
		return getFieldValue(onGround);
	}
	
	@Override
	public void setOnGround(boolean onGround) {
		setField(this.onGround, onGround);
	}

	@Override
	public boolean isHasPos() {
		return getFieldValue(hasPos);
	}
	
	@Override
	public void setHasPos(boolean hasPos) {
		setField(this.hasPos, hasPos);
	}

	@Override
	public boolean isHasRot() {
		return getFieldValue(hasRot);
	}
	
	@Override
	public void setHasRot(boolean hasRot) {
		setField(this.hasRot, hasRot);
	}
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.steervehicle;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketSteerVehicleWrapper extends PacketWrapper {
	
	Field sideways;
	Field forward;
	Field jumping;
	Field sneaking;

	PacketSteerVehicleWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		sideways = getFirstField(
			"xxa",
			"a"
		);
		forward = getFirstField(
			"zza",
			"b"
		);
		jumping = getFirstField(
			"isJumping",
			"c"
		);
		sneaking = getFirstField(
			"isSneaking",
			"d"
		);
	}
	
	public float getSideways() {
		return getFieldValue(sideways);
	}
	
	public void setSideways(float sideways) {
		setField(this.sideways, sideways);
	}
	
	public float getForward() {
		return getFieldValue(forward);
	}
	
	public void setForward(float forward) {
		setField(this.forward, forward);
	}
	
	public boolean isJumping() {
		return getFieldValue(jumping);
	}
	
	public void setJumping(boolean jumping) {
		setField(this.jumping, jumping);
	}
	
	public boolean isSneaking() {
		return getFieldValue(sneaking);
	}
	
	public void setSneaking(boolean sneaking) {
		setField(this.sneaking, sneaking);
	}
}

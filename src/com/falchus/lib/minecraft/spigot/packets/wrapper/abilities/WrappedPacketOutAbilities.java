package com.falchus.lib.minecraft.spigot.packets.wrapper.abilities;

import java.lang.reflect.Field;
import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutAbilities extends PacketAbilitiesWrapper {

	Field invulnerable;
	Field canFly;
	Field instabuild;
	Field flyingSpeed;
	Field walkingSpeed;
	
	public WrappedPacketOutAbilities(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutAbilities",
				networkProtocolGame + "PacketPlayOutAbilities"
			)
		);
		
		invulnerable = getFirstField(
			"invulnerable",
			"a"
		);
		canFly = getFirstField(
			"canFly",
			"c"
		);
		instabuild = getFirstField(
			"instabuild",
			"d"
		);
		flyingSpeed = getFirstField(
			"flyingSpeed",
			"e"
		);
		walkingSpeed = getFirstField(
			"walkingSpeed",
			"f"
		);
	}

	public boolean isInvulnerable() {
		return getFieldValue(invulnerable);
	}
	
	public void setInvulnerable(boolean invulnerable) {
		setField(this.invulnerable, invulnerable);
	}

	public boolean isCanFly() {
		return getFieldValue(canFly);
	}
	
	public void setCanFly(boolean canFly) {
		setField(this.canFly, canFly);
	}

	public boolean isInstabuild() {
		return getFieldValue(instabuild);
	}
	
	public void setInstabuild(boolean instabuild) {
		setField(this.instabuild, instabuild);
	}

	public float getFlyingSpeed() {
		return getFieldValue(flyingSpeed);
	}
	
	public void setFlyingSpeed(float flyingSpeed) {
		setField(this.flyingSpeed, flyingSpeed);
	}

	public float getWalkingSpeed() {
		return getFieldValue(walkingSpeed);
	}
	
	public void setWalkingSpeed(float walkingSpeed) {
		setField(this.walkingSpeed, walkingSpeed);
	}
}

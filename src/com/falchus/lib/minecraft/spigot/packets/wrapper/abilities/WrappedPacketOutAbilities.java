package com.falchus.lib.minecraft.spigot.packets.wrapper.abilities;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.world.entity.player.PlayerAbilities;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedPacketOutAbilities extends PacketAbilitiesWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutAbilities",
		networkProtocolGame + "PacketPlayOutAbilities"
	);

	Field invulnerable;
	Field canFly;
	Field instabuild;
	Field flyingSpeed;
	Field walkingSpeed;
	
	private WrappedPacketOutAbilities(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutAbilities",
			networkProtocolGame + "PacketPlayOutAbilities"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
		
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
	
	public WrappedPacketOutAbilities(@NonNull PlayerAbilities abilities) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				abilities.getHandle().getClass(),
				abilities.getHandle()
			)
		).build());
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

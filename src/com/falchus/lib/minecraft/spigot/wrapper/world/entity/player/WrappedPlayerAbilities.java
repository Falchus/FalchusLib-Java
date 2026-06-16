package com.falchus.lib.minecraft.spigot.wrapper.world.entity.player;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedPlayerAbilities extends SpigotWrapper implements PlayerAbilities {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PlayerAbilities",
		worldEntityPlayer + "PlayerAbilities"
	);
	
	Field invulnerable;
	Field flying;
	Field canFly;
	Field canInstantlyBuild;
	Field mayBuild;
	Field flySpeed;
	Field walkSpeed;

	private WrappedPlayerAbilities(@NonNull Object handle) {
		super(handle, names);
		
		invulnerable = getFirstField(
			"isInvulnerable",
			"invulnerable"
		);
		flying = getFirstField(
			"isFlying",
			"flying"
		);
		canFly = getFirstField(
			"canFly",
			"mayfly"
		);
		canInstantlyBuild = getFirstField(
			"canInstantlyBuild",
			"instabuild"
		);
		mayBuild = getField("mayBuild");
		flySpeed = getFirstField(
			"flySpeed",
			"flyingSpeed"
		);
		walkSpeed = getFirstField(
			"walkSpeed",
			"walkingSpeed"
		);
	}
	
	public WrappedPlayerAbilities() {
		this(new ClassInstanceBuilder(
			names
		).build());
	}
	
	@Override
	public boolean isInvulnerable() {
		return getFieldValue(invulnerable);
	}
	
	@Override
	public void setInvulnerable(boolean invulnerable) {
		setField(this.invulnerable, invulnerable);
	}
	
	@Override
	public boolean isFlying() {
		return getFieldValue(flying);
	}
	
	@Override
	public void setFlying(boolean flying) {
		setField(this.flying, flying);
	}
	
	@Override
	public boolean isCanFly() {
		return getFieldValue(canFly);
	}
	
	@Override
	public void setCanFly(boolean canFly) {
		setField(this.canFly, canFly);
	}
	
	@Override
	public boolean isCanInstantlyBuild() {
		return getFieldValue(canInstantlyBuild);
	}
	
	@Override
	public void setCanInstantlyBuild(boolean canInstantlyBuild) {
		setField(this.canInstantlyBuild, canInstantlyBuild);
	}
	
	@Override
	public boolean isMayBuild() {
		return getFieldValue(mayBuild);
	}
	
	@Override
	public void setMayBuild(boolean mayBuild) {
		setField(this.mayBuild, mayBuild);
	}
	
	@Override
	public float getFlySpeed() {
		return getFieldValue(flySpeed);
	}
	
	@Override
	public void setFlySpeed(float flySpeed) {
		setField(this.flySpeed, flySpeed);
	}
	
	@Override
	public float getWalkSpeed() {
		return getFieldValue(walkSpeed);
	}
	
	@Override
	public void setWalkSpeed(float walkSpeed) {
		setField(this.walkSpeed, walkSpeed);
	}
}

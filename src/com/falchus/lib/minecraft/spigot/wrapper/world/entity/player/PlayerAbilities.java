package com.falchus.lib.minecraft.spigot.wrapper.world.entity.player;

import com.falchus.lib.minecraft.spigot.wrapper.ISpigotWrapper;

public interface PlayerAbilities extends ISpigotWrapper {

	boolean isInvulnerable();
	void setInvulnerable(boolean invulnerable);
	
	boolean isFlying();
	void setFlying(boolean flying);
	
	boolean isCanFly();
	void setCanFly(boolean canFly);
	
	boolean isCanInstantlyBuild();
	void setCanInstantlyBuild(boolean canInstantlyBuild);
	
	boolean isMayBuild();
	void setMayBuild(boolean mayBuild);
	
	float getFlySpeed();
	void setFlySpeed(float flySpeed);
	
	float getWalkSpeed();
	void setWalkSpeed(float walkSpeed);
}

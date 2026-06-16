package com.falchus.lib.minecraft.spigot.wrapper.world.entity;

import org.bukkit.entity.LivingEntity;

public interface EntityLiving extends Entity { // TODO
	
	float getMaxHealth();
	
	float getHealth();
	void setHealth(float health);

	@Override
	LivingEntity getBukkitEntity();
}

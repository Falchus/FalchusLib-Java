package com.falchus.lib.minecraft.spigot.wrapper.world.entity;

import java.lang.reflect.Method;
import java.util.Set;

import org.bukkit.entity.LivingEntity;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class EntityLivingWrapper extends EntityWrapper implements EntityLiving { // TODO
	
	Method getMaxHealth;
	Method getHealth;
	Method setHealth;

	EntityLivingWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		getMaxHealth = getMethod("getMaxHealth");
		getHealth = getMethod("getHealth");
		setHealth = getMethod("setHealth",
			float.class
		);
	}
	
	@Override
	public float getMaxHealth() {
		try {
			return (float) getMaxHealth.invoke(handle);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public float getHealth() {
		try {
			return (float) getHealth.invoke(handle);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void setHealth(float health) {
		try {
			setHealth.invoke(handle,
				health
			);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public LivingEntity getBukkitEntity() {
		return (LivingEntity) super.getBukkitEntity();
	}
}

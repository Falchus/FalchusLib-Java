package com.falchus.lib.minecraft.spigot.utils;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;

import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EntityUtils {
	
	/**
	 * @return Entity
	 */
	public static Object getEntity(@NonNull Entity entity) {
		return VersionProvider.get().getEntity(entity);
	}

	/**
	 * @return {@link Entity}
	 */
	public static Entity getBukkitEntity(@NonNull Object entity) {
		return VersionProvider.get().getBukkitEntity(entity);
	}
	
	/**
	 * @return absorption from a {@link Damageable} entity.
	 */
	public static double getAbsorption(@NonNull Damageable entity) {
		return VersionProvider.get().getAbsorption(entity);
	}
	
	/**
	 * Sets absorption.
	 */
	public static void setAbsorption(@NonNull Damageable entity, double absorption) {
		VersionProvider.get().setAbsorption(entity, absorption);
	}
	
	/**
	 * Sets yaw and pitch.
	 */
	public static void setYawPitch(@NonNull Object entity, float yaw, float pitch) {
		VersionProvider.get().setYawPitch(entity, yaw, pitch);
	}
}
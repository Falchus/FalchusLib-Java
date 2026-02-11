package com.falchus.lib.minecraft.spigot.utils;

import org.bukkit.entity.Entity;

import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EntityUtils {

	/**
	 * @return {@link Entity}
	 */
	public static Entity getBukkitEntity(@NonNull Object entity) {
		return VersionProvider.get().getBukkitEntity(entity);
	}
	
	/**
	 * Sets yaw and pitch.
	 */
	public static void setYawPitch(@NonNull Object entity, float yaw, float pitch) {
		VersionProvider.get().setYawPitch(entity, yaw, pitch);
	}
}
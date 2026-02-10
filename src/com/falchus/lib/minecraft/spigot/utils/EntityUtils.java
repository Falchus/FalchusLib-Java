package com.falchus.lib.minecraft.spigot.utils;

import org.bukkit.entity.Entity;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EntityUtils {
	
	private static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();

	/**
	 * @return {@link Entity}
	 */
	public static Entity getBukkitEntity(@NonNull Object entity) {
		return plugin.getVersionAdapter().getBukkitEntity(entity);
	}
	
	/**
	 * Sets yaw and pitch.
	 */
	public static void setYawPitch(@NonNull Object entity, float yaw, float pitch) {
		plugin.getVersionAdapter().setYawPitch(entity, yaw, pitch);
	}
}
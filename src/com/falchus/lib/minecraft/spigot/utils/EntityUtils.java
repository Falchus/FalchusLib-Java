package com.falchus.lib.minecraft.spigot.utils;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;

import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.world.WrappedAxisAlignedBB;

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
	 * @return {@link Entity}
	 */
	public static Entity getEntityById(@NonNull World world, int id) {
		for (Entity entity : world.getEntities()) {
			if (entity.getEntityId() == id) {
				return entity;
			}
		}
		return null;
	}
	
	/**
	 * @return {@link WrappedAxisAlignedBB}
	 */
	public static WrappedAxisAlignedBB getBoundingBox(@NonNull Entity entity) {
		return VersionProvider.get().getBoundingBox(entity);
	}
	
	/**
	 * @return AxisAlignedBB
	 */
	public static Object modifyBoundingBox(@NonNull Object axisAlignedBB, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
		return VersionProvider.get().modifyBoundingBox(axisAlignedBB, minX, minY, minZ, maxX, maxY, maxZ);
	}
	
	/**
	 * @return {@code true} if on ground, {@code false} otherwise.
	 */
	public static boolean isOnGround(@NonNull Entity entity, double yExpand) {
		return !WorldUtils.getCollidingBlocks(entity.getWorld(), modifyBoundingBox(getBoundingBox(entity), 0, -yExpand, 0, 0, 0, 0)).isEmpty();
	}
	
	/**
	 * @return {@code true} if on ground, {@code false} otherwise.
	 */
	public static boolean isOnGround(@NonNull Entity entity) {
		return isOnGround(entity, 0.25);
	}
	
	/**
	 * @return {@code true} if on climbable, {@code false} otherwise.
	 */
	public static boolean isOnClimbable(@NonNull Entity entity) {
		Location location = entity.getLocation();
		return BlockUtils.isClimbable(location.getBlock()) ||
				BlockUtils.isClimbable(location.add(0, 1, 0).getBlock());
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
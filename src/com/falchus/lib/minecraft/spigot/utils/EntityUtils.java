package com.falchus.lib.minecraft.spigot.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
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
	 * @return {@link WrappedAxisAlignedBB}
	 */
	public static WrappedAxisAlignedBB modifyBoundingBox(@NonNull WrappedAxisAlignedBB axisAlignedBB, double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
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
	 * @return {@code true} if in liquid, {@code false} otherwise.
	 */
	public static boolean isInLiquid(@NonNull Entity entity) {
		WrappedAxisAlignedBB axisAlignedBB = modifyBoundingBox(getBoundingBox(entity), 0, -0.5, 0, 0, 0, 0);
		for (double x = axisAlignedBB.getMinX(); x < axisAlignedBB.getMaxX(); x++) {
			for (double y = axisAlignedBB.getMinY(); y < axisAlignedBB.getMaxY(); y++) {
				for (double z = axisAlignedBB.getMinZ(); z < axisAlignedBB.getMaxZ(); z++) {
					Block block = new Location(entity.getWorld(), x, y, z).getBlock();
					
					if (BlockUtils.isLiquid(block)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * @return {@code true} if on slime, {@code false} otherwise.
	 */
	public static boolean isOnSlime(@NonNull Entity entity) {
		WrappedAxisAlignedBB axisAlignedBB = modifyBoundingBox(getBoundingBox(entity), 0, -0.5, 0, 0, 0, 0);
		for (double x = axisAlignedBB.getMinX(); x < axisAlignedBB.getMaxX(); x++) {
			for (double y = axisAlignedBB.getMinY(); y < axisAlignedBB.getMaxY(); y++) {
				for (double z = axisAlignedBB.getMinZ(); z < axisAlignedBB.getMaxZ(); z++) {
					Block block = new Location(entity.getWorld(), x, y, z).getBlock();
					
					if (block.getType() == Material.SLIME_BLOCK) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * @return {@code true} if on stairs, {@code false} otherwise.
	 */
	public static boolean isOnStairs(@NonNull Entity entity) {
		WrappedAxisAlignedBB axisAlignedBB = modifyBoundingBox(getBoundingBox(entity), 0, -0.5, 0, 0, 0, 0);
		for (double x = axisAlignedBB.getMinX(); x < axisAlignedBB.getMaxX(); x++) {
			for (double y = axisAlignedBB.getMinY(); y < axisAlignedBB.getMaxY(); y++) {
				for (double z = axisAlignedBB.getMinZ(); z < axisAlignedBB.getMaxZ(); z++) {
					Block block = new Location(entity.getWorld(), x, y, z).getBlock();
					
					if (BlockUtils.isStair(block) ||
						BlockUtils.isSlab(block) ||
						block.getType() == Material.SKULL ||
						block.getType() == Material.CAKE_BLOCK) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * @return {@code true} if on ice, {@code false} otherwise.
	 */
	public static boolean isOnIce(@NonNull Entity entity) {
		WrappedAxisAlignedBB axisAlignedBB = modifyBoundingBox(getBoundingBox(entity), 0, -0.5, 0, 0, 0, 0);
		for (double x = axisAlignedBB.getMinX(); x < axisAlignedBB.getMaxX(); x++) {
			for (double y = axisAlignedBB.getMinY(); y < axisAlignedBB.getMaxY(); y++) {
				for (double z = axisAlignedBB.getMinZ(); z < axisAlignedBB.getMaxZ(); z++) {
					Block block = new Location(entity.getWorld(), x, y, z).getBlock();
					
					if (block.getType().name().endsWith("ICE")) {
						return true;
					}
				}
			}
		}
		return false;
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
	 * @return {@code true} if under block, {@code false} otherwise.
	 */
	public static boolean isUnderBlock(@NonNull Entity entity) {
		WrappedAxisAlignedBB axisAlignedBB = modifyBoundingBox(getBoundingBox(entity), 0, -0.5, 0, 0, 0, 0);
		for (double x = axisAlignedBB.getMinX(); x < axisAlignedBB.getMaxX(); x++) {
			for (double y = axisAlignedBB.getMinY(); y < axisAlignedBB.getMaxY(); y++) {
				for (double z = axisAlignedBB.getMinZ(); z < axisAlignedBB.getMaxZ(); z++) {
					Block block = new Location(entity.getWorld(), x, y, z).getBlock();
					
					if (block.getType().isSolid()) {
						return true;
					}
				}
			}
		}
		return false;
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
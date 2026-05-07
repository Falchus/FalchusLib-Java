package com.falchus.lib.minecraft.spigot.utils;

import org.bukkit.block.Block;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BlockUtils {
	
	/**
	 * @return {@code true} if liquid, {@code false} otherwise.
	 */
	public static boolean isLiquid(@NonNull Block block) {
		String name = block.getType().name();
		return name.endsWith("WATER") ||
				name.endsWith("LAVA");
	}
	
	/**
	 * @return {@code true} if slab, {@code false} otherwise.
	 */
	public static boolean isSlab(@NonNull Block block) {
		String name = block.getType().name();
		return name.endsWith("SLAB") ||
				name.endsWith("SLAB2") ||
				name.endsWith("STEP");
	}
	
	/**
	 * @return {@code true} if stair, {@code false} otherwise.
	 */
	public static boolean isStair(@NonNull Block block) {
		return block.getType().name().endsWith("STAIRS");
	}
	
	/**
	 * @return {@code true} if climbable, {@code false} otherwise.
	 */
	public static boolean isClimbable(@NonNull Block block) {
		switch (block.getType()) {
			case VINE:
			case LADDER:
				return true;
		
			default:
				return false;
		}
	}
}

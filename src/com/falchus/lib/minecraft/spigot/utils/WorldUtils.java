package com.falchus.lib.minecraft.spigot.utils;

import org.bukkit.World;
import org.bukkit.block.Biome;

import com.falchus.lib.minecraft.spigot.enums.GameRule;
import com.falchus.lib.minecraft.spigot.utils.nms.NmsAdapter;
import com.falchus.lib.minecraft.spigot.utils.nms.NmsProvider;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * Utility class for Spigot world-related operations.
 */
@UtilityClass
public class WorldUtils {
	
	private static final NmsAdapter nms = NmsProvider.get();

	/**
	 * Sets a game rule for the given world.
	 */
	public static void setGameRule(@NonNull World world, @NonNull GameRule gameRule, @NonNull String value) {
		world.setGameRuleValue(gameRule.getKey(), value);
	}
	
	/**
	 * @return World Biomes
	 */
	public static Object[] getWorldBiomes(@NonNull World world) {
		return nms.getWorldBiomes(world);
	}
	
	/**
	 * @return BiomeBase from a Biome
	 */
	public static Object getNmsBiome(Biome biome) {
        return nms.getNmsBiome(biome);
    }
	
	/**
	 * @return id from a Biome
	 */
	public static int getBiomeId(Biome biome) {
		return nms.getBiomeId(biome);
	}
}

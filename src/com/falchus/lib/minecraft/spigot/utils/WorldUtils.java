package com.falchus.lib.minecraft.spigot.utils;

import org.bukkit.World;
import org.bukkit.block.Biome;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.spigot.enums.GameRule;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorldUtils {
	
	private static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();

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
		return plugin.getContexts().getNmsAdapter().getWorldBiomes(world);
	}
	
	/**
	 * @return BiomeBase from a Biome
	 */
	public static Object getNmsBiome(Biome biome) {
        return plugin.getContexts().getNmsAdapter().getNmsBiome(biome);
    }
	
	/**
	 * @return id from a Biome
	 */
	public static int getBiomeId(Biome biome) {
		return plugin.getContexts().getNmsAdapter().getBiomeId(biome);
	}
}

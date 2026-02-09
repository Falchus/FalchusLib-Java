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
		return plugin.getNmsAdapter().getWorldBiomes(world);
	}
	
	/**
	 * @return BiomeBase from a Biome
	 */
	public static Object getNmsBiome(@NonNull Biome biome) {
        return plugin.getNmsAdapter().getNmsBiome(biome);
    }
	
	/**
	 * @return id from a Biome
	 */
	public static int getBiomeId(@NonNull Biome biome) {
		return plugin.getNmsAdapter().getBiomeId(biome);
	}
	
	/**
	 * @return {@link Biome}
	 */
	public static Biome getBiome(@NonNull com.falchus.lib.minecraft.spigot.enums.Biome biome) {
		if (ServerUtils.getMinorVersion() < 17) {
			return Biome.valueOf(biome.name());
		}
		return Biome.valueOf(biome.getModernName());
	}
}

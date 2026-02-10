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
		return plugin.getVersionAdapter().getWorldBiomes(world);
	}
	
	/**
	 * @return BiomeBase from a Biome
	 */
	public static Object getNmsBiome(Biome biome) {
        return plugin.getVersionAdapter().getNmsBiome(biome);
    }
	
	/**
	 * @return id from a Biome
	 */
	public static int getBiomeId(Biome biome) {
		return plugin.getVersionAdapter().getBiomeId(biome);
	}
	
	/**
	 * @return WorldServer from a World
	 */
	public static Object getWorldServer(World world) {
		return plugin.getVersionAdapter().getWorldServer(world);
	}
	
	/**
	 * @return {@link Biome}
	 */
	public static Biome getBiome(com.falchus.lib.minecraft.spigot.enums.Biome biome) {
		if (ServerUtils.getMinorVersion() < 17) {
			return Biome.valueOf(biome.name());
		}
		return Biome.valueOf(biome.getModernName());
	}
}

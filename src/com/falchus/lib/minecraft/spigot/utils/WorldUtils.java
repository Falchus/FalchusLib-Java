package com.falchus.lib.minecraft.spigot.utils;

import org.bukkit.World;
import org.bukkit.block.Biome;

import com.falchus.lib.minecraft.spigot.enums.GameRule;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorldUtils {

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
		return VersionProvider.get().getWorldBiomes(world);
	}
	
	/**
	 * @return BiomeBase from a Biome
	 */
	public static Object getNmsBiome(Biome biome) {
        return VersionProvider.get().getNmsBiome(biome);
    }
	
	/**
	 * @return id from a Biome
	 */
	public static int getBiomeId(Biome biome) {
		return VersionProvider.get().getBiomeId(biome);
	}
	
	/**
	 * @return WorldServer from a World
	 */
	public static Object getWorldServer(World world) {
		return VersionProvider.get().getWorldServer(world);
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

package com.falchus.lib.minecraft.spigot.utils;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;

import com.falchus.lib.minecraft.spigot.enums.GameRule;
import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.world.WrappedAxisAlignedBB;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class WorldUtils {

	/**
	 * Sets a game rule for the given world.
	 */
	public static void setGameRule(@NonNull World world, @NonNull GameRule gameRule, @NonNull String value) {
        VersionProvider.get().setGameRule(world, gameRule, value);
	}
	
	/**
	 * @return BiomeBase[]
	 */
	public static Object[] getBiomes() {
		return VersionProvider.get().getBiomes();
	}
	
	/**
	 * @return id from a Biome
	 */
	public static int getBiomeId(Biome biome) {
		return VersionProvider.get().getBiomeId(biome);
	}
	
	/**
	 * @return BiomeBase from a Biome
	 */
	public static Object getNmsBiome(Biome biome) {
        return VersionProvider.get().getNmsBiome(biome);
    }
	
	/**
	 * @return WorldServer from a World
	 */
	public static Object getWorldServer(World world) {
		return VersionProvider.get().getWorldServer(world);
	}
	
	/**
	 * @return {@link List}
	 */
	public static List<WrappedAxisAlignedBB> getCollidingBlocks(@NonNull World world, @NonNull WrappedAxisAlignedBB axisAlignedBB) {
		return VersionProvider.get().getCollidingBlocks(world, axisAlignedBB);
	}
	
	/**
	 * @return {@link Biome}
	 */
	public static Biome getBiome(com.falchus.lib.minecraft.spigot.enums.Biome biome) {
		if (ServerUtils.getVersion().isBefore(Version.v1_17)) {
			return Biome.valueOf(biome.name());
		}
		return Biome.valueOf(biome.getModernName());
	}
	
	/**
	 * @return {@link Material}
	 */
	public static Material getMaterial(com.falchus.lib.minecraft.spigot.enums.Material material) {
		if (ServerUtils.getVersion().isBefore(Version.v1_13)) {
			return Material.valueOf(material.getLegacyName());
		}
		return Material.valueOf(material.name());
	}
}

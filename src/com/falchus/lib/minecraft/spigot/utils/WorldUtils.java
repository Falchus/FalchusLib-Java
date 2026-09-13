package com.falchus.lib.minecraft.spigot.utils;

<<<<<<< HEAD
import java.util.ArrayList;
=======
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
import java.util.List;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

import com.falchus.lib.minecraft.spigot.enums.GameRule;
import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
<<<<<<< HEAD
import com.falchus.lib.minecraft.spigot.wrapper.world.AxisAlignedBB;
=======
import com.falchus.lib.minecraft.spigot.wrapper.world.WrappedAxisAlignedBB;
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git

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
	 * @return World from a {@link World}
	 */
	public static Object getWorld(@NonNull World world) {
		return VersionProvider.get().getWorld(world);
	}
	
	/**
	 * @return WorldServer from a {@link World}
	 */
	public static Object getWorldServer(@NonNull World world) {
		return VersionProvider.get().getWorldServer(world);
	}
	
	/**
	 * @return {@link List}
	 */
<<<<<<< HEAD
	public static List<AxisAlignedBB> getCollidingBlocks(@NonNull World world, @NonNull AxisAlignedBB axisAlignedBB) {
=======
	public static List<WrappedAxisAlignedBB> getCollidingBlocks(@NonNull World world, @NonNull WrappedAxisAlignedBB axisAlignedBB) {
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
		return VersionProvider.get().getCollidingBlocks(world, axisAlignedBB);
	}
	
	public static List<Block> getBlocksInBox(@NonNull World world, @NonNull AxisAlignedBB axisAlignedBB) {
		int minX = (int) Math.floor(axisAlignedBB.getMinX());
		int maxX = (int) Math.floor(axisAlignedBB.getMaxX());
		int minY = (int) Math.floor(axisAlignedBB.getMinY());
		int maxY = (int) Math.floor(axisAlignedBB.getMaxY());
		int minZ = (int) Math.floor(axisAlignedBB.getMinZ());
		int maxZ = (int) Math.floor(axisAlignedBB.getMaxZ());
		
		List<Block> blocks = new ArrayList<>();
		for (int x = minX; x <= maxX; x++) {
			for (int y = minY; y <= maxY; y++) {
				for (int z = minZ; z <= maxZ; z++) {
					blocks.add(world.getBlockAt(x, y, z));
				}
			}
		}
		return blocks;
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

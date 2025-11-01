package com.falchus.lib.minecraft.spigot.utils;

import java.lang.reflect.Field;

import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;

import com.falchus.lib.minecraft.spigot.enums.GameRule;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.minecraft.server.v1_8_R3.BiomeBase;
import net.minecraft.server.v1_8_R3.WorldChunkManager;
import net.minecraft.server.v1_8_R3.WorldServer;

/**
 * Utility class for Spigot world-related operations.
 */
@UtilityClass
public class WorldUtils {

	/**
	 * Sets a game rule for the given world.
	 */
	public static void setGameRule(@NonNull World world, @NonNull GameRule gameRule, @NonNull String value) {
		world.setGameRuleValue(gameRule.getKey(), value);
	}
	
	/**
	 * Gets world biomes.
	 */
	public static BiomeBase[] getWorldBiomes(@NonNull World world) {
		try {
			WorldServer nmsWorld = ((CraftWorld) world).getHandle();
			WorldChunkManager worldChunkManager = nmsWorld.getWorldChunkManager();
			
			Field biomesField = WorldChunkManager.class.getDeclaredField("biomes");
			biomesField.setAccessible(true);
			
			return (BiomeBase[]) biomesField.get(worldChunkManager);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Returns BiomeBase from a Biome
	 */
	public static BiomeBase getNmsBiome(Biome biome) {
		if (biome == null) return BiomeBase.PLAINS;
		
		switch (biome) {
			case BEACH: return BiomeBase.BEACH;
			
			case BIRCH_FOREST:
			case BIRCH_FOREST_MOUNTAINS:
				return BiomeBase.BIRCH_FOREST;
			
			case BIRCH_FOREST_HILLS:
			case BIRCH_FOREST_HILLS_MOUNTAINS:
				return BiomeBase.BIRCH_FOREST_HILLS;
			
			case COLD_BEACH: return BiomeBase.COLD_BEACH;
			case COLD_TAIGA: return BiomeBase.COLD_TAIGA;
			
			case COLD_TAIGA_HILLS:
			case COLD_TAIGA_MOUNTAINS:
				return BiomeBase.COLD_TAIGA_HILLS;
			
			case DEEP_OCEAN: return BiomeBase.DEEP_OCEAN;
			case DESERT: return BiomeBase.DESERT;
			
			case DESERT_HILLS:
			case DESERT_MOUNTAINS:
				return BiomeBase.DESERT_HILLS;
			
			case EXTREME_HILLS:
			case EXTREME_HILLS_MOUNTAINS:
				return BiomeBase.EXTREME_HILLS;
			
			case EXTREME_HILLS_PLUS:
			case EXTREME_HILLS_PLUS_MOUNTAINS:
				return BiomeBase.EXTREME_HILLS_PLUS;
			
			case FOREST:
			case FLOWER_FOREST:
				return BiomeBase.FOREST;
			
			case FOREST_HILLS: return BiomeBase.FOREST_HILLS;
			case FROZEN_OCEAN: return BiomeBase.FROZEN_OCEAN;
			case FROZEN_RIVER: return BiomeBase.FROZEN_RIVER;
			case HELL: return BiomeBase.HELL;
			case ICE_MOUNTAINS: return BiomeBase.ICE_MOUNTAINS;
			
			case ICE_PLAINS:
			case ICE_PLAINS_SPIKES:
				return BiomeBase.ICE_PLAINS;
			
			case JUNGLE: return BiomeBase.JUNGLE;
			
			case JUNGLE_EDGE:
			case JUNGLE_EDGE_MOUNTAINS:
				return BiomeBase.JUNGLE_EDGE;
			
			case JUNGLE_HILLS:
			case JUNGLE_MOUNTAINS:
				return BiomeBase.JUNGLE_HILLS;
			
			case MEGA_TAIGA: return BiomeBase.MEGA_TAIGA;
			case MEGA_TAIGA_HILLS: return BiomeBase.MEGA_TAIGA_HILLS;
			
			case MESA:
			case MESA_BRYCE:
			case MESA_PLATEAU:
			case MESA_PLATEAU_FOREST:
			case MESA_PLATEAU_FOREST_MOUNTAINS:
			case MESA_PLATEAU_MOUNTAINS:
				return BiomeBase.MESA;
			
			case MUSHROOM_ISLAND: return BiomeBase.MUSHROOM_ISLAND;
			case MUSHROOM_SHORE: return BiomeBase.MUSHROOM_SHORE;
			case OCEAN: return BiomeBase.OCEAN;
			
			case PLAINS:
			case SUNFLOWER_PLAINS:
				return BiomeBase.PLAINS;
			
			case RIVER: return BiomeBase.RIVER;
			
			case ROOFED_FOREST:
			case ROOFED_FOREST_MOUNTAINS:
				return BiomeBase.ROOFED_FOREST;
			
			case SAVANNA:
			case SAVANNA_MOUNTAINS:
				return BiomeBase.SAVANNA;
			
			case SAVANNA_PLATEAU:
			case SAVANNA_PLATEAU_MOUNTAINS:
				return BiomeBase.SAVANNA_PLATEAU;
			
			case SKY: return BiomeBase.SKY;
			case SMALL_MOUNTAINS: return BiomeBase.SMALL_MOUNTAINS;
			case STONE_BEACH: return BiomeBase.STONE_BEACH;
			
			case SWAMPLAND:
			case SWAMPLAND_MOUNTAINS:
				return BiomeBase.SWAMPLAND;
			
			case TAIGA:
			case MEGA_SPRUCE_TAIGA:
			case MEGA_SPRUCE_TAIGA_HILLS:
				return BiomeBase.TAIGA;
			
			case TAIGA_HILLS:
			case TAIGA_MOUNTAINS:
				return BiomeBase.TAIGA_HILLS;
			
			default: return BiomeBase.PLAINS;
		}
	}
}

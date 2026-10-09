package com.falchus.lib.minecraft.spigot.enums;

import java.util.function.Function;

import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Biome {
	BADLANDS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MESA";
			default -> null;
		}
	),
	BEACH(
		v -> switch (v) {
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BEACHES";
			default -> null;
		}
	),
	BIRCH_FOREST,
	DARK_FOREST(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ROOFED_FOREST";
			default -> null;
		}
	),
	DEEP_OCEAN,
	DESERT,
	ERODED_BADLANDS(
		v -> switch (v) {
			case v1_8_8 -> "MESA_BRYCE";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MUTATED_MESA";
			default -> null;
		}
	),
	FLOWER_FOREST(
		v -> switch (v) {
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MUTATED_FOREST";
			default -> null;
		}
	),
	FOREST,
	FROZEN_OCEAN,
	FROZEN_RIVER,
	ICE_SPIKES(
		v -> switch (v) {
			case v1_8_8 -> "ICE_PLAINS_SPIKES";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MUTATED_ICE_FLATS";
			default -> null;
		}
	),
	JUNGLE,
	MUSHROOM_FIELDS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MUSHROOM_ISLAND";
			default -> null;
		}
	),
	NETHER_WASTES(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "HELL";
			case v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2 -> "NETHER";
			default -> null;
		}
	),
	OCEAN,
	OLD_GROWTH_BIRCH_FOREST(
		v -> switch (v) {
			case v1_8_8 -> "BIRCH_FOREST_MOUNTAINS";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MUTATED_BIRCH_FOREST";
			case v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2, v1_16, v1_16_1, v1_16_2, v1_16_3, v1_16_4, v1_16_5, v1_17, v1_17_1 -> "TALL_BIRCH_FOREST";
			default -> null;
		}
	),
	OLD_GROWTH_PINE_TAIGA(
		v -> switch (v) {
			case v1_8_8 -> "MEGA_TAIGA";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "REDWOOD_TAIGA";
			case v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2, v1_16, v1_16_1, v1_16_2, v1_16_3, v1_16_4, v1_16_5, v1_17, v1_17_1 -> "GIANT_TREE_TAIGA";
			default -> null;
		}
	),
	OLD_GROWTH_SPRUCE_TAIGA(
		v -> switch (v) {
			case v1_8_8 -> "MEGA_SPRUCE_TAIGA";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MUTATED_REDWOOD_TAIGA";
			case v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2, v1_16, v1_16_1, v1_16_2, v1_16_3, v1_16_4, v1_16_5, v1_17, v1_17_1 -> "GIANT_SPRUCE_TAIGA";
			default -> null;
		}
	),
	PLAINS,
	RIVER,
	SAVANNA,
	SAVANNA_PLATEAU(
		v -> switch (v) {
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SAVANNA_ROCK";
			default -> null;
		}
	),
	SNOWY_BEACH(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "COLD_BEACH";
			default -> null;
		}
	),
	SNOWY_PLAINS(
		v -> switch (v) {
			case v1_8_8 -> "ICE_PLAINS";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ICE_FLATS";
			case v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2, v1_16, v1_16_1, v1_16_2, v1_16_3, v1_16_4, v1_16_5, v1_17, v1_17_1 -> "SNOWY_TUNDRA";
			default -> null;
		}
	),
	SNOWY_TAIGA(
		v -> switch (v) {
			case v1_8_8 -> "COLD_TAIGA";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "TAIGA_COLD";
			default -> null;
		}
	),
	SPARSE_JUNGLE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2, v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2, v1_16, v1_16_1, v1_16_2, v1_16_3, v1_16_4, v1_16_5, v1_17, v1_17_1 -> "JUNGLE_EDGE";
			default -> null;
		}
	),
	STONY_SHORE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STONE_BEACH";
			case v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2, v1_16, v1_16_1, v1_16_2, v1_16_3, v1_16_4, v1_16_5, v1_17, v1_17_1 -> "STONE_SHORE";
			default -> null;
		}
	),
	SUNFLOWER_PLAINS(
		v -> switch (v) {
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MUTATED_PLAINS";
			default -> null;
		}
	),
	SWAMP(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SWAMPLAND";
			default -> null;
		}
	),
	TAIGA,
	THE_END(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SKY";
			default -> null;
		}
	),
	WINDSWEPT_GRAVELLY_HILLS(
		v -> switch (v) {
			case v1_8_8 -> "EXTREME_HILLS_MOUNTAINS";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MUTATED_EXTREME_HILLS";
			case v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2, v1_16, v1_16_1, v1_16_2, v1_16_3, v1_16_4, v1_16_5, v1_17, v1_17_1 -> "GRAVELLY_MOUNTAINS";
			default -> null;
		}
	),
	WINDSWEPT_HILLS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "EXTREME_HILLS";
			case v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2, v1_16, v1_16_1, v1_16_2, v1_16_3, v1_16_4, v1_16_5, v1_17, v1_17_1 -> "MOUNTAINS";
			default -> null;
		}
	),
	WINDSWEPT_SAVANNA(
		v -> switch (v) {
			case v1_8_8 -> "SAVANNA_MOUNTAINS";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MUTATED_SAVANNA";
			case v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2, v1_16, v1_16_1, v1_16_2, v1_16_3, v1_16_4, v1_16_5, v1_17, v1_17_1 -> "SHATTERED_SAVANNA";
			default -> null;
		}
	),
	WOODED_BADLANDS(
		v -> switch (v) {
			case v1_8_8 -> "MESA_PLATEAU_FOREST";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MESA_ROCK";
			case v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2, v1_16, v1_16_1, v1_16_2, v1_16_3, v1_16_4, v1_16_5, v1_17, v1_17_1 -> "WOODED_BADLANDS_PLATEAU";
			default -> null;
		}
	),
	WINDSWEPT_FOREST(
		v -> switch (v) {
			case v1_8_8 -> "EXTREME_HILLS_PLUS";
			case v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "EXTREME_HILLS_WITH_TREES";
			case v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2, v1_16, v1_16_1, v1_16_2, v1_16_3, v1_16_4, v1_16_5, v1_17, v1_17_1 -> "WOODED_MOUNTAINS";
			default -> null;
		}
	);

	private final Function<Version, String> name;

	Biome() {
		this(null);
	}

	public String getName() {
		if (name != null) {
			String n = name.apply(ServerUtils.getVersion());
			if (n != null) {
				return n;
			}
		}
		return name();
	}
	
	public static Biome ofBukkit(org.bukkit.block.Biome biome) {
		String name = biome.name();
		try {
			return Biome.valueOf(name);
		} catch (Exception e) {}
		for (Biome value : values()) {
			if (value.getName().equals(name)) {
				return value;
			}
		}
		return null;
	}

	public org.bukkit.block.Biome toBukkit() {
		return org.bukkit.block.Biome.valueOf(getName());
	}
}

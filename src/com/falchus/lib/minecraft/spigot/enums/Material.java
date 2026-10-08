package com.falchus.lib.minecraft.spigot.enums;

import java.util.function.Function;

import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Material {
	ACACIA_DOOR(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ACACIA_DOOR_ITEM";
			default -> null;
		}
	),
	ACACIA_FENCE,
	ACACIA_FENCE_GATE,
	ACACIA_LEAVES(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "LEAVES_2";
			default -> null;
		}
	),
	ACACIA_LOG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "LOG_2";
			default -> null;
		}
	),
	ACACIA_PLANKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	ACACIA_SAPLING(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SAPLING";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	ACACIA_SLAB(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD_STEP";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	ACACIA_STAIRS,
	ACTIVATOR_RAIL,
	AIR,
	ALLIUM(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RED_ROSE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	ANDESITE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STONE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 5;
			default -> null;
		}
	),
	ANVIL,
	APPLE,
	ARMOR_STAND,
	ARROW,
	AZURE_BLUET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RED_ROSE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	BAKED_POTATO,
	BARRIER,
	BAT_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 65;
			default -> null;
		}
	),
	BEACON,
	BEDROCK,
	BEEF(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RAW_BEEF";
			default -> null;
		}
	),
	BIRCH_DOOR(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BIRCH_DOOR_ITEM";
			default -> null;
		}
	),
	BIRCH_FENCE,
	BIRCH_FENCE_GATE,
	BIRCH_LEAVES(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "LEAVES";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	BIRCH_LOG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "LOG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	BIRCH_PLANKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	BIRCH_SAPLING(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SAPLING";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	BIRCH_SLAB(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD_STEP";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	BIRCH_STAIRS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BIRCH_WOOD_STAIRS";
			default -> null;
		}
	),
	BLACK_BANNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BANNER";
			default -> null;
		}
	),
	BLACK_BED(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BED";
			default -> null;
		},
		v -> switch (v) {
			case v1_12, v1_12_1, v1_12_2 -> 15;
			default -> null;
		}
	),
	BLACK_CARPET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARPET";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 15;
			default -> null;
		}
	),
	BLACK_STAINED_GLASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 15;
			default -> null;
		}
	),
	BLACK_STAINED_GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS_PANE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 15;
			default -> null;
		}
	),
	BLACK_TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_CLAY";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 15;
			default -> null;
		}
	),
	BLACK_WOOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 15;
			default -> null;
		}
	),
	BLAZE_POWDER,
	BLAZE_ROD,
	BLAZE_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 61;
			default -> null;
		}
	),
	BLUE_BANNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BANNER";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	BLUE_BED(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BED";
			default -> null;
		},
		v -> switch (v) {
			case v1_12, v1_12_1, v1_12_2 -> 11;
			default -> null;
		}
	),
	BLUE_CARPET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARPET";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 11;
			default -> null;
		}
	),
	BLUE_ORCHID(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RED_ROSE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	BLUE_STAINED_GLASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 11;
			default -> null;
		}
	),
	BLUE_STAINED_GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS_PANE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 11;
			default -> null;
		}
	),
	BLUE_TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_CLAY";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 11;
			default -> null;
		}
	),
	BLUE_WOOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 11;
			default -> null;
		}
	),
	BONE,
	BONE_MEAL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "INK_SACK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 15;
			default -> null;
		}
	),
	BOOK,
	BOOKSHELF,
	BOW,
	BOWL,
	BREAD,
	BREWING_STAND(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BREWING_STAND_ITEM";
			default -> null;
		}
	),
	BRICK(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CLAY_BRICK";
			default -> null;
		}
	),
	BRICKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BRICK";
			default -> null;
		}
	),
	BRICK_SLAB(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STEP";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	BRICK_STAIRS,
	BROWN_BANNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BANNER";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	BROWN_BED(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BED";
			default -> null;
		},
		v -> switch (v) {
			case v1_12, v1_12_1, v1_12_2 -> 12;
			default -> null;
		}
	),
	BROWN_CARPET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARPET";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 12;
			default -> null;
		}
	),
	BROWN_MUSHROOM,
	BROWN_MUSHROOM_BLOCK(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "HUGE_MUSHROOM_1";
			default -> null;
		}
	),
	BROWN_STAINED_GLASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 12;
			default -> null;
		}
	),
	BROWN_STAINED_GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS_PANE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 12;
			default -> null;
		}
	),
	BROWN_TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_CLAY";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 12;
			default -> null;
		}
	),
	BROWN_WOOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 12;
			default -> null;
		}
	),
	BUCKET,
	CACTUS,
	CAKE,
	CARROT(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARROT_ITEM";
			default -> null;
		}
	),
	CARROTS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARROT";
			default -> null;
		}
	),
	CARROT_ON_A_STICK(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARROT_STICK";
			default -> null;
		}
	),
	CAULDRON(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CAULDRON_ITEM";
			default -> null;
		}
	),
	CAVE_SPIDER_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 59;
			default -> null;
		}
	),
	CHAINMAIL_BOOTS,
	CHAINMAIL_CHESTPLATE,
	CHAINMAIL_HELMET,
	CHAINMAIL_LEGGINGS,
	CHARCOAL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "COAL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	CHEST,
	CHEST_MINECART(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STORAGE_MINECART";
			default -> null;
		}
	),
	CHICKEN(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RAW_CHICKEN";
			default -> null;
		}
	),
	CHICKEN_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 93;
			default -> null;
		}
	),
	CHIPPED_ANVIL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ANVIL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	CHISELED_QUARTZ_BLOCK(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "QUARTZ_BLOCK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	CHISELED_RED_SANDSTONE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RED_SANDSTONE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	CHISELED_SANDSTONE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SANDSTONE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	CHISELED_STONE_BRICKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SMOOTH_BRICK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	CLAY,
	CLAY_BALL,
	CLOCK(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WATCH";
			default -> null;
		}
	),
	COAL,
	COAL_BLOCK,
	COAL_ORE,
	COARSE_DIRT(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "DIRT";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	COBBLESTONE,
	COBBLESTONE_SLAB(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STEP";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	COBBLESTONE_STAIRS,
	COBBLESTONE_WALL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "COBBLE_WALL";
			default -> null;
		}
	),
	COBWEB(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WEB";
			default -> null;
		}
	),
	COCOA,
	COCOA_BEANS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "INK_SACK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	COD(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RAW_FISH";
			default -> null;
		}
	),
	COMMAND_BLOCK(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "COMMAND";
			default -> null;
		}
	),
	COMMAND_BLOCK_MINECART(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "COMMAND_MINECART";
			default -> null;
		}
	),
	COMPARATOR(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "REDSTONE_COMPARATOR";
			default -> null;
		}
	),
	COMPASS,
	COOKED_BEEF,
	COOKED_CHICKEN,
	COOKED_COD(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "COOKED_FISH";
			default -> null;
		}
	),
	COOKED_MUTTON,
	COOKED_PORKCHOP(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "GRILLED_PORK";
			default -> null;
		}
	),
	COOKED_RABBIT,
	COOKED_SALMON(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "COOKED_FISH";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	COOKIE,
	COW_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 92;
			default -> null;
		}
	),
	CRACKED_STONE_BRICKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SMOOTH_BRICK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	CRAFTING_TABLE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WORKBENCH";
			default -> null;
		}
	),
	CREEPER_HEAD(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SKULL_ITEM";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	CREEPER_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 50;
			default -> null;
		}
	),
	CUT_RED_SANDSTONE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RED_SANDSTONE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	CUT_SANDSTONE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SANDSTONE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	CYAN_BANNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BANNER";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 6;
			default -> null;
		}
	),
	CYAN_BED(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BED";
			default -> null;
		},
		v -> switch (v) {
			case v1_12, v1_12_1, v1_12_2 -> 9;
			default -> null;
		}
	),
	CYAN_CARPET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARPET";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 9;
			default -> null;
		}
	),
	CYAN_DYE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "INK_SACK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 6;
			default -> null;
		}
	),
	CYAN_STAINED_GLASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 9;
			default -> null;
		}
	),
	CYAN_STAINED_GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS_PANE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 9;
			default -> null;
		}
	),
	CYAN_TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_CLAY";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 9;
			default -> null;
		}
	),
	CYAN_WOOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 9;
			default -> null;
		}
	),
	DAMAGED_ANVIL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ANVIL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	DANDELION(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "YELLOW_FLOWER";
			default -> null;
		}
	),
	DARK_OAK_DOOR(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "DARK_OAK_DOOR_ITEM";
			default -> null;
		}
	),
	DARK_OAK_FENCE,
	DARK_OAK_FENCE_GATE,
	DARK_OAK_LEAVES(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "LEAVES_2";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	DARK_OAK_LOG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "LOG_2";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	DARK_OAK_PLANKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 5;
			default -> null;
		}
	),
	DARK_OAK_SAPLING(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SAPLING";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 5;
			default -> null;
		}
	),
	DARK_OAK_SLAB(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD_STEP";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 5;
			default -> null;
		}
	),
	DARK_OAK_STAIRS,
	DARK_PRISMARINE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "PRISMARINE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	DAYLIGHT_DETECTOR,
	DEAD_BUSH,
	DETECTOR_RAIL,
	DIAMOND,
	DIAMOND_AXE,
	DIAMOND_BLOCK,
	DIAMOND_BOOTS,
	DIAMOND_CHESTPLATE,
	DIAMOND_HELMET,
	DIAMOND_HOE,
	DIAMOND_HORSE_ARMOR(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "DIAMOND_BARDING";
			default -> null;
		}
	),
	DIAMOND_LEGGINGS,
	DIAMOND_ORE,
	DIAMOND_PICKAXE,
	DIAMOND_SHOVEL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "DIAMOND_SPADE";
			default -> null;
		}
	),
	DIAMOND_SWORD,
	DIORITE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STONE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	DIRT,
	DISPENSER,
	DRAGON_EGG,
	DROPPER,
	EGG,
	EMERALD,
	EMERALD_BLOCK,
	EMERALD_ORE,
	ENCHANTED_BOOK,
	ENCHANTED_GOLDEN_APPLE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "GOLDEN_APPLE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	ENCHANTING_TABLE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ENCHANTMENT_TABLE";
			default -> null;
		}
	),
	ENDERMAN_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 58;
			default -> null;
		}
	),
	ENDERMITE_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 67;
			default -> null;
		}
	),
	ENDER_CHEST,
	ENDER_EYE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "EYE_OF_ENDER";
			default -> null;
		}
	),
	ENDER_PEARL,
	END_PORTAL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ENDER_PORTAL";
			default -> null;
		}
	),
	END_PORTAL_FRAME(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ENDER_PORTAL_FRAME";
			default -> null;
		}
	),
	END_STONE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "ENDER_STONE";
			default -> null;
		}
	),
	EXPERIENCE_BOTTLE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "EXP_BOTTLE";
			default -> null;
		}
	),
	FARMLAND(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SOIL";
			default -> null;
		}
	),
	FEATHER,
	FERMENTED_SPIDER_EYE,
	FERN(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "LONG_GRASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	FILLED_MAP(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MAP";
			default -> null;
		}
	),
	FIRE,
	FIREWORK_ROCKET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "FIREWORK";
			default -> null;
		}
	),
	FIREWORK_STAR(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "FIREWORK_CHARGE";
			default -> null;
		}
	),
	FIRE_CHARGE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "FIREBALL";
			default -> null;
		}
	),
	FISHING_ROD,
	FLINT,
	FLINT_AND_STEEL,
	FLOWER_POT(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "FLOWER_POT_ITEM";
			default -> null;
		}
	),
	FURNACE,
	FURNACE_MINECART(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "POWERED_MINECART";
			default -> null;
		}
	),
	GHAST_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 56;
			default -> null;
		}
	),
	GHAST_TEAR,
	GLASS,
	GLASS_BOTTLE,
	GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "THIN_GLASS";
			default -> null;
		}
	),
	GLISTERING_MELON_SLICE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SPECKLED_MELON";
			default -> null;
		}
	),
	GLOWSTONE,
	GLOWSTONE_DUST,
	GOLDEN_APPLE,
	GOLDEN_AXE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "GOLD_AXE";
			default -> null;
		}
	),
	GOLDEN_BOOTS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "GOLD_BOOTS";
			default -> null;
		}
	),
	GOLDEN_CARROT,
	GOLDEN_CHESTPLATE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "GOLD_CHESTPLATE";
			default -> null;
		}
	),
	GOLDEN_HELMET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "GOLD_HELMET";
			default -> null;
		}
	),
	GOLDEN_HOE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "GOLD_HOE";
			default -> null;
		}
	),
	GOLDEN_HORSE_ARMOR(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "GOLD_BARDING";
			default -> null;
		}
	),
	GOLDEN_LEGGINGS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "GOLD_LEGGINGS";
			default -> null;
		}
	),
	GOLDEN_PICKAXE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "GOLD_PICKAXE";
			default -> null;
		}
	),
	GOLDEN_SHOVEL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "GOLD_SPADE";
			default -> null;
		}
	),
	GOLDEN_SWORD(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "GOLD_SWORD";
			default -> null;
		}
	),
	GOLD_BLOCK,
	GOLD_INGOT,
	GOLD_NUGGET,
	GOLD_ORE,
	GRANITE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STONE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	GRASS_BLOCK(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "GRASS";
			default -> null;
		}
	),
	GRAVEL,
	GRAY_BANNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BANNER";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 8;
			default -> null;
		}
	),
	GRAY_BED(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BED";
			default -> null;
		},
		v -> switch (v) {
			case v1_12, v1_12_1, v1_12_2 -> 7;
			default -> null;
		}
	),
	GRAY_CARPET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARPET";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 7;
			default -> null;
		}
	),
	GRAY_DYE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "INK_SACK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 8;
			default -> null;
		}
	),
	GRAY_STAINED_GLASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 7;
			default -> null;
		}
	),
	GRAY_STAINED_GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS_PANE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 7;
			default -> null;
		}
	),
	GRAY_TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_CLAY";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 7;
			default -> null;
		}
	),
	GRAY_WOOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 7;
			default -> null;
		}
	),
	GREEN_BANNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BANNER";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	GREEN_BED(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BED";
			default -> null;
		},
		v -> switch (v) {
			case v1_12, v1_12_1, v1_12_2 -> 13;
			default -> null;
		}
	),
	GREEN_CARPET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARPET";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 13;
			default -> null;
		}
	),
	GREEN_DYE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "INK_SACK";
			case v1_13, v1_13_1, v1_13_2 -> "CACTUS_GREEN";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	GREEN_STAINED_GLASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 13;
			default -> null;
		}
	),
	GREEN_STAINED_GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS_PANE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 13;
			default -> null;
		}
	),
	GREEN_TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_CLAY";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 13;
			default -> null;
		}
	),
	GREEN_WOOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 13;
			default -> null;
		}
	),
	GUARDIAN_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 68;
			default -> null;
		}
	),
	GUNPOWDER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SULPHUR";
			default -> null;
		}
	),
	HAY_BLOCK,
	HEAVY_WEIGHTED_PRESSURE_PLATE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "IRON_PLATE";
			default -> null;
		}
	),
	HOPPER,
	HOPPER_MINECART,
	HORSE_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 100;
			default -> null;
		}
	),
	ICE,
	INFESTED_CHISELED_STONE_BRICKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGGS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 5;
			default -> null;
		}
	),
	INFESTED_COBBLESTONE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGGS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	INFESTED_CRACKED_STONE_BRICKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGGS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	INFESTED_MOSSY_STONE_BRICKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGGS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	INFESTED_STONE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGGS";
			default -> null;
		}
	),
	INFESTED_STONE_BRICKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGGS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	INK_SAC(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "INK_SACK";
			default -> null;
		}
	),
	IRON_AXE,
	IRON_BARS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "IRON_FENCE";
			default -> null;
		}
	),
	IRON_BLOCK,
	IRON_BOOTS,
	IRON_CHESTPLATE,
	IRON_DOOR,
	IRON_HELMET,
	IRON_HOE,
	IRON_HORSE_ARMOR(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "IRON_BARDING";
			default -> null;
		}
	),
	IRON_INGOT,
	IRON_LEGGINGS,
	IRON_ORE,
	IRON_PICKAXE,
	IRON_SHOVEL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "IRON_SPADE";
			default -> null;
		}
	),
	IRON_SWORD,
	IRON_TRAPDOOR,
	ITEM_FRAME,
	JACK_O_LANTERN,
	JUKEBOX,
	JUNGLE_DOOR(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "JUNGLE_DOOR_ITEM";
			default -> null;
		}
	),
	JUNGLE_FENCE,
	JUNGLE_FENCE_GATE,
	JUNGLE_LEAVES(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "LEAVES";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	JUNGLE_LOG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "LOG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	JUNGLE_PLANKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	JUNGLE_SAPLING(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SAPLING";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	JUNGLE_SLAB(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD_STEP";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	JUNGLE_STAIRS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "JUNGLE_WOOD_STAIRS";
			default -> null;
		}
	),
	LADDER,
	LAPIS_BLOCK,
	LAPIS_LAZULI(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "INK_SACK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	LAPIS_ORE,
	LARGE_FERN(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "DOUBLE_PLANT";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	LAVA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STATIONARY_LAVA";
			default -> null;
		}
	),
	LAVA_BUCKET,
	LEAD(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "LEASH";
			default -> null;
		}
	),
	LEATHER,
	LEATHER_BOOTS,
	LEATHER_CHESTPLATE,
	LEATHER_HELMET,
	LEATHER_LEGGINGS,
	LEVER,
	LIGHT_BLUE_BANNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BANNER";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 12;
			default -> null;
		}
	),
	LIGHT_BLUE_BED(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BED";
			default -> null;
		},
		v -> switch (v) {
			case v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	LIGHT_BLUE_CARPET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARPET";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	LIGHT_BLUE_DYE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "INK_SACK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 12;
			default -> null;
		}
	),
	LIGHT_BLUE_STAINED_GLASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	LIGHT_BLUE_STAINED_GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS_PANE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	LIGHT_BLUE_TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_CLAY";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	LIGHT_BLUE_WOOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	LIGHT_GRAY_BANNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BANNER";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 7;
			default -> null;
		}
	),
	LIGHT_GRAY_BED(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BED";
			default -> null;
		},
		v -> switch (v) {
			case v1_12, v1_12_1, v1_12_2 -> 8;
			default -> null;
		}
	),
	LIGHT_GRAY_CARPET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARPET";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 8;
			default -> null;
		}
	),
	LIGHT_GRAY_DYE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "INK_SACK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 7;
			default -> null;
		}
	),
	LIGHT_GRAY_STAINED_GLASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 8;
			default -> null;
		}
	),
	LIGHT_GRAY_STAINED_GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS_PANE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 8;
			default -> null;
		}
	),
	LIGHT_GRAY_TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_CLAY";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 8;
			default -> null;
		}
	),
	LIGHT_GRAY_WOOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 8;
			default -> null;
		}
	),
	LIGHT_WEIGHTED_PRESSURE_PLATE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "GOLD_PLATE";
			default -> null;
		}
	),
	LILAC(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "DOUBLE_PLANT";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	LILY_PAD(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WATER_LILY";
			default -> null;
		}
	),
	LIME_BANNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BANNER";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 10;
			default -> null;
		}
	),
	LIME_BED(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BED";
			default -> null;
		},
		v -> switch (v) {
			case v1_12, v1_12_1, v1_12_2 -> 5;
			default -> null;
		}
	),
	LIME_CARPET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARPET";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 5;
			default -> null;
		}
	),
	LIME_DYE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "INK_SACK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 10;
			default -> null;
		}
	),
	LIME_STAINED_GLASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 5;
			default -> null;
		}
	),
	LIME_STAINED_GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS_PANE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 5;
			default -> null;
		}
	),
	LIME_TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_CLAY";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 5;
			default -> null;
		}
	),
	LIME_WOOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 5;
			default -> null;
		}
	),
	MAGENTA_BANNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BANNER";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 13;
			default -> null;
		}
	),
	MAGENTA_BED(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BED";
			default -> null;
		},
		v -> switch (v) {
			case v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	MAGENTA_CARPET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARPET";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	MAGENTA_DYE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "INK_SACK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 13;
			default -> null;
		}
	),
	MAGENTA_STAINED_GLASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	MAGENTA_STAINED_GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS_PANE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	MAGENTA_TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_CLAY";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	MAGENTA_WOOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	MAGMA_CREAM,
	MAGMA_CUBE_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 62;
			default -> null;
		}
	),
	MAP(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "EMPTY_MAP";
			default -> null;
		}
	),
	MELON(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MELON_BLOCK";
			default -> null;
		}
	),
	MELON_SEEDS,
	MELON_SLICE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MELON";
			default -> null;
		}
	),
	MELON_STEM,
	MILK_BUCKET,
	MINECART,
	MOOSHROOM_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 96;
			default -> null;
		}
	),
	MOSSY_COBBLESTONE,
	MOSSY_COBBLESTONE_WALL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "COBBLE_WALL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	MOSSY_STONE_BRICKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SMOOTH_BRICK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	MOVING_PISTON(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "PISTON_MOVING_PIECE";
			default -> null;
		}
	),
	MUSHROOM_STEW(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MUSHROOM_SOUP";
			default -> null;
		}
	),
	MUSIC_DISC_11(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RECORD_11";
			default -> null;
		}
	),
	MUSIC_DISC_13(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "GOLD_RECORD";
			default -> null;
		}
	),
	MUSIC_DISC_BLOCKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RECORD_3";
			default -> null;
		}
	),
	MUSIC_DISC_CAT(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "GREEN_RECORD";
			default -> null;
		}
	),
	MUSIC_DISC_CHIRP(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RECORD_4";
			default -> null;
		}
	),
	MUSIC_DISC_FAR(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RECORD_5";
			default -> null;
		}
	),
	MUSIC_DISC_MALL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RECORD_6";
			default -> null;
		}
	),
	MUSIC_DISC_MELLOHI(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RECORD_7";
			default -> null;
		}
	),
	MUSIC_DISC_STAL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RECORD_8";
			default -> null;
		}
	),
	MUSIC_DISC_STRAD(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RECORD_9";
			default -> null;
		}
	),
	MUSIC_DISC_WAIT(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RECORD_12";
			default -> null;
		}
	),
	MUSIC_DISC_WARD(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RECORD_10";
			default -> null;
		}
	),
	MUTTON,
	MYCELIUM(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MYCEL";
			default -> null;
		}
	),
	NAME_TAG,
	NETHERRACK,
	NETHER_BRICK(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "NETHER_BRICK_ITEM";
			default -> null;
		}
	),
	NETHER_BRICKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "NETHER_BRICK";
			default -> null;
		}
	),
	NETHER_BRICK_FENCE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "NETHER_FENCE";
			default -> null;
		}
	),
	NETHER_BRICK_SLAB(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STEP";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 6;
			default -> null;
		}
	),
	NETHER_BRICK_STAIRS,
	NETHER_PORTAL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "PORTAL";
			default -> null;
		}
	),
	NETHER_QUARTZ_ORE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "QUARTZ_ORE";
			default -> null;
		}
	),
	NETHER_STAR,
	NETHER_WART(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "NETHER_STALK";
			default -> null;
		}
	),
	NOTE_BLOCK,
	OAK_BOAT(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BOAT";
			default -> null;
		}
	),
	OAK_BUTTON(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD_BUTTON";
			default -> null;
		}
	),
	OAK_DOOR(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD_DOOR";
			default -> null;
		}
	),
	OAK_FENCE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "FENCE";
			default -> null;
		}
	),
	OAK_FENCE_GATE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "FENCE_GATE";
			default -> null;
		}
	),
	OAK_LEAVES(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "LEAVES";
			default -> null;
		}
	),
	OAK_LOG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "LOG";
			default -> null;
		}
	),
	OAK_PLANKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD";
			default -> null;
		}
	),
	OAK_PRESSURE_PLATE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD_PLATE";
			default -> null;
		}
	),
	OAK_SAPLING(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SAPLING";
			default -> null;
		}
	),
	OAK_SIGN(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SIGN";
			case v1_13, v1_13_1, v1_13_2 -> "SIGN";
			default -> null;
		}
	),
	OAK_SLAB(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD_STEP";
			default -> null;
		}
	),
	OAK_STAIRS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD_STAIRS";
			default -> null;
		}
	),
	OAK_TRAPDOOR(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "TRAP_DOOR";
			default -> null;
		}
	),
	OBSIDIAN,
	OCELOT_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 98;
			default -> null;
		}
	),
	ORANGE_BANNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BANNER";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 14;
			default -> null;
		}
	),
	ORANGE_BED(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BED";
			default -> null;
		},
		v -> switch (v) {
			case v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	ORANGE_CARPET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARPET";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	ORANGE_DYE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "INK_SACK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 14;
			default -> null;
		}
	),
	ORANGE_STAINED_GLASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	ORANGE_STAINED_GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS_PANE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	ORANGE_TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_CLAY";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	ORANGE_TULIP(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RED_ROSE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 5;
			default -> null;
		}
	),
	ORANGE_WOOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	OXEYE_DAISY(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RED_ROSE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 8;
			default -> null;
		}
	),
	PACKED_ICE,
	PAINTING,
	PAPER,
	PEONY(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "DOUBLE_PLANT";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 5;
			default -> null;
		}
	),
	PETRIFIED_OAK_SLAB(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STEP";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	PIG_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 90;
			default -> null;
		}
	),
	PINK_BANNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BANNER";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 9;
			default -> null;
		}
	),
	PINK_BED(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BED";
			default -> null;
		},
		v -> switch (v) {
			case v1_12, v1_12_1, v1_12_2 -> 6;
			default -> null;
		}
	),
	PINK_CARPET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARPET";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 6;
			default -> null;
		}
	),
	PINK_DYE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "INK_SACK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 9;
			default -> null;
		}
	),
	PINK_STAINED_GLASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 6;
			default -> null;
		}
	),
	PINK_STAINED_GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS_PANE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 6;
			default -> null;
		}
	),
	PINK_TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_CLAY";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 6;
			default -> null;
		}
	),
	PINK_TULIP(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RED_ROSE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 7;
			default -> null;
		}
	),
	PINK_WOOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 6;
			default -> null;
		}
	),
	PISTON(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "PISTON_BASE";
			default -> null;
		}
	),
	PISTON_HEAD(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "PISTON_EXTENSION";
			default -> null;
		}
	),
	PLAYER_HEAD(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SKULL_ITEM";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	PODZOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "DIRT";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	POISONOUS_POTATO,
	POLISHED_ANDESITE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STONE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 6;
			default -> null;
		}
	),
	POLISHED_DIORITE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STONE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	POLISHED_GRANITE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STONE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	POPPY(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RED_ROSE";
			default -> null;
		}
	),
	PORKCHOP(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "PORK";
			default -> null;
		}
	),
	POTATO(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "POTATO_ITEM";
			default -> null;
		}
	),
	POTATOES(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "POTATO";
			default -> null;
		}
	),
	POTION,
	POWERED_RAIL,
	PRISMARINE,
	PRISMARINE_BRICKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "PRISMARINE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	PRISMARINE_CRYSTALS,
	PRISMARINE_SHARD,
	PUFFERFISH(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RAW_FISH";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 3;
			default -> null;
		}
	),
	PUMPKIN,
	PUMPKIN_PIE,
	PUMPKIN_SEEDS,
	PUMPKIN_STEM,
	PURPLE_BANNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BANNER";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 5;
			default -> null;
		}
	),
	PURPLE_BED(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BED";
			default -> null;
		},
		v -> switch (v) {
			case v1_12, v1_12_1, v1_12_2 -> 10;
			default -> null;
		}
	),
	PURPLE_CARPET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARPET";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 10;
			default -> null;
		}
	),
	PURPLE_DYE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "INK_SACK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 5;
			default -> null;
		}
	),
	PURPLE_STAINED_GLASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 10;
			default -> null;
		}
	),
	PURPLE_STAINED_GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS_PANE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 10;
			default -> null;
		}
	),
	PURPLE_TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_CLAY";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 10;
			default -> null;
		}
	),
	PURPLE_WOOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 10;
			default -> null;
		}
	),
	QUARTZ,
	QUARTZ_BLOCK,
	QUARTZ_PILLAR(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "QUARTZ_BLOCK";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	QUARTZ_SLAB(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STEP";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 7;
			default -> null;
		}
	),
	QUARTZ_STAIRS,
	RABBIT,
	RABBIT_FOOT,
	RABBIT_HIDE,
	RABBIT_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 101;
			default -> null;
		}
	),
	RABBIT_STEW,
	RAIL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RAILS";
			default -> null;
		}
	),
	REDSTONE,
	REDSTONE_BLOCK,
	REDSTONE_LAMP(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "REDSTONE_LAMP_OFF";
			default -> null;
		}
	),
	REDSTONE_ORE,
	REDSTONE_TORCH(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "REDSTONE_TORCH_ON";
			default -> null;
		}
	),
	REDSTONE_WIRE,
	RED_BANNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BANNER";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	RED_BED(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BED";
			default -> null;
		},
		v -> switch (v) {
			case v1_12, v1_12_1, v1_12_2 -> 14;
			default -> null;
		}
	),
	RED_CARPET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARPET";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 14;
			default -> null;
		}
	),
	RED_DYE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "INK_SACK";
			case v1_13, v1_13_1, v1_13_2 -> "ROSE_RED";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	RED_MUSHROOM,
	RED_MUSHROOM_BLOCK(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "HUGE_MUSHROOM_2";
			default -> null;
		}
	),
	RED_SAND(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SAND";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	RED_SANDSTONE,
	RED_SANDSTONE_SLAB(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STONE_SLAB2";
			default -> null;
		}
	),
	RED_SANDSTONE_STAIRS,
	RED_STAINED_GLASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 14;
			default -> null;
		}
	),
	RED_STAINED_GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS_PANE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 14;
			default -> null;
		}
	),
	RED_TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_CLAY";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 14;
			default -> null;
		}
	),
	RED_TULIP(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RED_ROSE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	RED_WOOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 14;
			default -> null;
		}
	),
	REPEATER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "DIODE";
			default -> null;
		}
	),
	ROSE_BUSH(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "DOUBLE_PLANT";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	ROTTEN_FLESH,
	SADDLE,
	SALMON(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RAW_FISH";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	SAND,
	SANDSTONE,
	SANDSTONE_SLAB(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STEP";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	SANDSTONE_STAIRS,
	SEA_LANTERN,
	SHEARS,
	SHEEP_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 91;
			default -> null;
		}
	),
	SHORT_GRASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "LONG_GRASS";
			case v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2, v1_16, v1_16_1, v1_16_2, v1_16_3, v1_16_4, v1_16_5, v1_17, v1_17_1, v1_18, v1_18_1, v1_18_2, v1_19, v1_19_1, v1_19_2, v1_19_3, v1_19_4, v1_20_1, v1_20_2 -> "GRASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	SILVERFISH_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 60;
			default -> null;
		}
	),
	SKELETON_SKULL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SKULL_ITEM";
			default -> null;
		}
	),
	SKELETON_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 51;
			default -> null;
		}
	),
	SLIME_BALL,
	SLIME_BLOCK,
	SLIME_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 55;
			default -> null;
		}
	),
	SMOOTH_STONE_SLAB(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STEP";
			case v1_13, v1_13_1, v1_13_2 -> "STONE_SLAB";
			default -> null;
		}
	),
	SNOW,
	SNOWBALL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SNOW_BALL";
			default -> null;
		}
	),
	SNOW_BLOCK,
	SOUL_SAND,
	SPAWNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MOB_SPAWNER";
			default -> null;
		}
	),
	SPIDER_EYE,
	SPIDER_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 52;
			default -> null;
		}
	),
	SPONGE,
	SPRUCE_DOOR(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SPRUCE_DOOR_ITEM";
			default -> null;
		}
	),
	SPRUCE_FENCE,
	SPRUCE_FENCE_GATE,
	SPRUCE_LEAVES(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "LEAVES";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	SPRUCE_LOG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "LOG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	SPRUCE_PLANKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	SPRUCE_SAPLING(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SAPLING";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	SPRUCE_SLAB(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD_STEP";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	SPRUCE_STAIRS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SPRUCE_WOOD_STAIRS";
			default -> null;
		}
	),
	SQUID_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 94;
			default -> null;
		}
	),
	STICK,
	STICKY_PISTON(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "PISTON_STICKY_BASE";
			default -> null;
		}
	),
	STONE,
	STONE_AXE,
	STONE_BRICKS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SMOOTH_BRICK";
			default -> null;
		}
	),
	STONE_BRICK_SLAB(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STEP";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 5;
			default -> null;
		}
	),
	STONE_BRICK_STAIRS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SMOOTH_STAIRS";
			default -> null;
		}
	),
	STONE_BUTTON,
	STONE_HOE,
	STONE_PICKAXE,
	STONE_PRESSURE_PLATE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STONE_PLATE";
			default -> null;
		}
	),
	STONE_SHOVEL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STONE_SPADE";
			default -> null;
		}
	),
	STONE_SWORD,
	STRING,
	SUGAR,
	SUGAR_CANE,
	SUNFLOWER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "DOUBLE_PLANT";
			default -> null;
		}
	),
	TALL_GRASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "DOUBLE_PLANT";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "HARD_CLAY";
			default -> null;
		}
	),
	TNT,
	TNT_MINECART(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "EXPLOSIVE_MINECART";
			default -> null;
		}
	),
	TORCH,
	TRAPPED_CHEST,
	TRIPWIRE,
	TRIPWIRE_HOOK,
	TROPICAL_FISH(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RAW_FISH";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	VILLAGER_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 120;
			default -> null;
		}
	),
	VINE,
	WATER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STATIONARY_WATER";
			default -> null;
		}
	),
	WATER_BUCKET,
	WET_SPONGE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SPONGE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	WHEAT,
	WHEAT_SEEDS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SEEDS";
			default -> null;
		}
	),
	WHITE_BANNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BANNER";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 15;
			default -> null;
		}
	),
	WHITE_BED(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BED";
			default -> null;
		}
	),
	WHITE_CARPET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARPET";
			default -> null;
		}
	),
	WHITE_STAINED_GLASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS";
			default -> null;
		}
	),
	WHITE_STAINED_GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS_PANE";
			default -> null;
		}
	),
	WHITE_TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_CLAY";
			default -> null;
		}
	),
	WHITE_TULIP(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "RED_ROSE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 6;
			default -> null;
		}
	),
	WHITE_WOOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOL";
			default -> null;
		}
	),
	WITCH_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 66;
			default -> null;
		}
	),
	WITHER_SKELETON_SKULL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SKULL_ITEM";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 1;
			default -> null;
		}
	),
	WOLF_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 95;
			default -> null;
		}
	),
	WOODEN_AXE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD_AXE";
			default -> null;
		}
	),
	WOODEN_HOE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD_HOE";
			default -> null;
		}
	),
	WOODEN_PICKAXE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD_PICKAXE";
			default -> null;
		}
	),
	WOODEN_SHOVEL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD_SPADE";
			default -> null;
		}
	),
	WOODEN_SWORD(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOD_SWORD";
			default -> null;
		}
	),
	WRITABLE_BOOK(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BOOK_AND_QUILL";
			default -> null;
		}
	),
	WRITTEN_BOOK,
	YELLOW_BANNER(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BANNER";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 11;
			default -> null;
		}
	),
	YELLOW_BED(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "BED";
			default -> null;
		},
		v -> switch (v) {
			case v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	YELLOW_CARPET(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "CARPET";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	YELLOW_DYE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "INK_SACK";
			case v1_13, v1_13_1, v1_13_2 -> "DANDELION_YELLOW";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 11;
			default -> null;
		}
	),
	YELLOW_STAINED_GLASS(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	YELLOW_STAINED_GLASS_PANE(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_GLASS_PANE";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	YELLOW_TERRACOTTA(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "STAINED_CLAY";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	YELLOW_WOOL(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "WOOL";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 4;
			default -> null;
		}
	),
	ZOMBIE_HEAD(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "SKULL_ITEM";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> 2;
			default -> null;
		}
	),
	ZOMBIE_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 54;
			default -> null;
		}
	),
	ZOMBIFIED_PIGLIN_SPAWN_EGG(
		v -> switch (v) {
			case v1_8_8, v1_9, v1_9_2, v1_9_4, v1_10, v1_10_2, v1_11, v1_11_1, v1_11_2, v1_12, v1_12_1, v1_12_2 -> "MONSTER_EGG";
			case v1_13, v1_13_1, v1_13_2, v1_14, v1_14_1, v1_14_2, v1_14_3, v1_14_4, v1_15, v1_15_1, v1_15_2 -> "ZOMBIE_PIGMAN_SPAWN_EGG";
			default -> null;
		},
		v -> switch (v) {
			case v1_8_8 -> 57;
			default -> null;
		}
	);

	private final Function<Version, String> name;
	private final Function<Version, Integer> durability;

	Material(Function<Version, String> name) {
		this(name, null);
	}

	Material() {
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

	public Integer getDurability() {
		return durability != null ? durability.apply(ServerUtils.getVersion()) : null;
	}

	public org.bukkit.Material toBukkit() {
		return org.bukkit.Material.valueOf(getName());
	}
}

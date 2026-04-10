package com.falchus.lib.minecraft.spigot.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Material {
    BLACK_WOOL(15, "WOOL"),
    BLUE_WOOL(11, "WOOL"),
    CAULDRON("CAULDRON_ITEM"),
    CLOCK("WATCH"),
    COBBLESTONE_WALL("COBBLE_WALL"),
    COBWEB("WEB"),
    DANDELION("YELLOW_FLOWER"),
    ENCHANTING_TABLE("ENCHANTMENT_TABLE"),
    ENDER_EYE("EYE_OF_ENDER"),
    END_STONE("ENDER_STONE"),
    EXPERIENCE_BOTTLE("EXP_BOTTLE"),
    GRASS_BLOCK("GRASS"),
    GRAY_STAINED_GLASS_PANE(7, "STAINED_GLASS_PANE"),
    GRAY_WOOL(7, "WOOL"),
    GREEN_WOOL(13, "WOOL"),
    LAPIS_LAZULI(4, "INK_SACK"),
    LAVA("STATIONARY_LAVA"),
    LIGHT_BLUE_WOOL(3, "WOOL"),
    LIME_STAINED_GLASS_PANE(5, "STAINED_GLASS_PANE"),
    LIME_WOOL(5, "WOOL"),
    NETHER_BRICKS("NETHER_BRICK"),
    OAK_FENCE("FENCE"),
    OAK_FENCE_GATE("FENCE_GATE"),
    OAK_LOG("LOG"),
    OAK_PLANKS("WOOD"),
    ORANGE_WOOL(1, "WOOL"),
    PINK_WOOL(6, "WOOL"),
    PLAYER_HEAD(3, "SKULL_ITEM"),
    POPPY("RED_ROSE"),
    PURPLE_WOOL(10, "WOOL"),
    QUARTZ_SLAB(7, "STEP"),
    RED_BED("BED"),
    RED_DYE(1, "INK_SACK"),
    RED_STAINED_GLASS_PANE(14, "STAINED_GLASS_PANE"),
    RED_WOOL(14, "WOOL"),
    SNOWBALL("SNOW_BALL"),
    WATER("STATIONARY_WATER"),
    WOODEN_AXE("WOOD_AXE"),
    WOODEN_SWORD("WOOD_SWORD"),
    YELLOW_STAINED_GLASS_PANE(4, "STAINED_GLASS_PANE"),
    YELLOW_WOOL(4, "WOOL");

    private final int legacyDurability;
    private final String legacyName;

    Material(String legacyName) {
        this(0, legacyName);
    }
}

package com.falchus.lib.minecraft.spigot.utils;

import com.falchus.lib.minecraft.spigot.enums.GameRule;
import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;

@UtilityClass
public class WorldUtils {

    /**
     * Sets a game rule for the given world.
     * <p>
     * {@code World.setGameRuleValue(String, String)} was deprecated in Paper 1.18
     * and removed in Paper 26.x. On 1.13+ we resolve the typed
     * {@link org.bukkit.GameRule} constant by name and use the non-deprecated
     * {@code World.setGameRule(GameRule<T>, T)} overload. On 1.8–1.12 the legacy
     * String overload is still used because the typed API does not exist there.
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static void setGameRule(@NonNull World world, @NonNull GameRule gameRule, @NonNull String value) {
        if (ServerUtils.getVersion().isAfter(Version.v1_12)) {
            org.bukkit.GameRule<?> rule = org.bukkit.GameRule.getByName(gameRule.getKey());
            if (rule == null) {
                throw new IllegalArgumentException("Unknown game rule: " + gameRule.getKey());
            }

            Class<?> type = rule.getType();
            if (type == Boolean.class) {
                world.setGameRule((org.bukkit.GameRule<Boolean>) rule, Boolean.parseBoolean(value));
            } else if (type == Integer.class) {
                world.setGameRule((org.bukkit.GameRule<Integer>) rule, Integer.parseInt(value));
            } else {
                // Fallback for any future typed game rules — attempt the String cast.
                // This will throw a ClassCastException at runtime if the type truly
                // cannot accept a String, which is a programmer error.
                world.setGameRule((org.bukkit.GameRule) rule, value);
            }
        } else {
            // 1.8–1.12: typed API unavailable, legacy String overload is fine.
            world.setGameRuleValue(gameRule.getKey(), value);
        }
    }

    /**
     * @return the NMS {@code BiomeBase[]} array for the given world.
     */
    public static Object[] getWorldBiomes(@NonNull World world) {
        return VersionProvider.get().getWorldBiomes(world);
    }

    /**
     * @return the NMS {@code BiomeBase} for the given Bukkit {@link Biome}.
     */
    public static Object getNmsBiome(Biome biome) {
        return VersionProvider.get().getNmsBiome(biome);
    }

    /**
     * @return the numeric biome id for the given Bukkit {@link Biome}.
     */
    public static int getBiomeId(Biome biome) {
        return VersionProvider.get().getBiomeId(biome);
    }

    /**
     * @return the NMS {@code WorldServer} for the given Bukkit {@link World}.
     */
    public static Object getWorldServer(World world) {
        return VersionProvider.get().getWorldServer(world);
    }

    /**
     * @return the Bukkit {@link Biome} matching the library's cross-version
     * {@link com.falchus.lib.minecraft.spigot.enums.Biome} enum.
     */
    public static Biome getBiome(@NonNull com.falchus.lib.minecraft.spigot.enums.Biome biome) {
        if (ServerUtils.getVersion().isBefore(Version.v1_17)) {
            return Biome.valueOf(biome.name());
        }
        return Biome.valueOf(biome.getModernName());
    }

    /**
     * @return the Bukkit {@link Material} matching the library's cross-version
     * {@link com.falchus.lib.minecraft.spigot.enums.Material} enum.
     */
    public static Material getMaterial(@NonNull com.falchus.lib.minecraft.spigot.enums.Material material) {
        if (ServerUtils.getVersion().isBefore(Version.v1_13)) {
            return Material.valueOf(material.getLegacyName());
        }
        return Material.valueOf(material.name());
    }
}
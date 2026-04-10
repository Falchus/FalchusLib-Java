package com.falchus.lib.minecraft.spigot.utils.version;

import com.falchus.lib.minecraft.FalchusLibMinecraft;
import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.utils.version.v1_21_R1.VersionAdapter_v1_21_R1;
import com.falchus.lib.minecraft.spigot.utils.version.v1_9_R1.VersionAdapter_v1_9_R1;
import com.falchus.lib.minecraft.spigot.utils.version.v26_1_R1.VersionAdapter_v26_1_R1;
import com.falchus.lib.minecraft.spigot.utils.version.v_1_13_R1.VersionAdapter_v_1_13_R1;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;

/**
 * Provides the {@link IVersionAdapter} for the current server version.
 * <p>
 * Selection order (most specific → least specific):
 * <ol>
 *     <li>Folia on any supported version → {@link VersionAdapterFolia}</li>
 *     <li>Paper 26.x.x → {@link VersionAdapter_v26_1_R1}</li>
 *     <li>1.21.x → {@link VersionAdapter_v1_21_R1}</li>
 *     <li>1.17–1.20 → {@link VersionAdapterModern}</li>
 *     <li>1.13–1.16 → {@link VersionAdapter_v_1_13_R1}</li>
 *     <li>1.9–1.12 → {@link VersionAdapter_v1_9_R1}</li>
 *     <li>1.8 → {@link VersionAdapter}</li>
 * </ol>
 */
@UtilityClass
public class VersionProvider {

    private static IVersionAdapter adapter;

    private static IVersionAdapter load() {
        if (FalchusLibMinecraft.isFolia()) {
            return new VersionAdapterFolia();
        }

        Version version = ServerUtils.getVersion();
        int major = ServerUtils.getMajorVersion();

        if (major >= 26) {
            return new VersionAdapter_v26_1_R1();
        }

        if (version.isAfter(Version.v1_20)) {
            return new VersionAdapter_v1_21_R1();
        }

        if (version.isAfter(Version.v1_16)) {
            return new VersionAdapterModern();
        }

        if (version.isAfter(Version.v1_12)) {
            return new VersionAdapter_v_1_13_R1();
        }

        if (version.isAfter(Version.v1_8)) {
            return new VersionAdapter_v1_9_R1();
        }

        if (version.isBefore(Version.v1_9)) {
            return new VersionAdapter();
        }

        try {
            String[] parts = Bukkit.getServer().getClass().getPackageName().split("\\.");
            if (parts.length >= 4) {
                String ver = parts[3];
                return (IVersionAdapter) new ClassInstanceBuilder(
                        VersionProvider.class.getPackageName() + "." + ver
                                + "." + VersionAdapter.class.getSimpleName() + "_" + ver
                ).build();
            }
        } catch (Exception ignored) {
        }

        return new VersionAdapter();
    }

    public static IVersionAdapter get() {
        if (adapter == null) {
            adapter = load();
        }
        return adapter;
    }

    /**
     * Resets the cached adapter — useful in tests or hot-reload scenarios.
     */
    public static void reset() {
        adapter = null;
    }
}
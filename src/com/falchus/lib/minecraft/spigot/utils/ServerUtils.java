package com.falchus.lib.minecraft.spigot.utils;

import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.Server;

@UtilityClass
public class ServerUtils {

    /**
     * @return MinecraftServer
     */
    public static Object getMcServer() {
        return VersionProvider.get().getMcServer();
    }

    /**
     * @return {@link Server}
     */
    public static Object getBukkitServer() {
        return VersionProvider.get().getBukkitServer();
    }

    /**
     * @return {@link Version}
     */
    public static Version getVersion() {
        int major = getMajorVersion();
        int minor = getMinorVersion();

        Version version = null;
        for (Version v : Version.values()) {
            if (v.getMajor() < major || (v.getMajor() == major && v.getMinor() <= minor)) {
                if (version == null || v.isAfter(version)) {
                    version = v;
                }
            }
        }
        if (version == null) {
            throw new IllegalStateException("Unsupported server version: " + getVersionString());
        }
        return version;
    }

    /**
     * @return e.g. "1.8.8"
     */
    public static String getVersionString() {
        return VersionProvider.get().getVersion();
    }

    /**
     * @return e.g. 26 for 26.1
     */
    public static int getMajorVersion() {
        String bukkitVersion = Bukkit.getBukkitVersion();
        String mc = bukkitVersion.split("-")[0];

        try {
            return Integer.parseInt(mc.split("\\.")[0]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return e.g. 8 for 1.8.8
     */
    public static int getMinorVersion() {
        String bukkitVersion = Bukkit.getBukkitVersion();
        String mc = bukkitVersion.split("-")[0];

        try {
            return Integer.parseInt(mc.split("\\.")[1]);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return recent TPS
     */
    public static double[] getRecentTps() {
        return VersionProvider.get().getRecentTps();
    }
}
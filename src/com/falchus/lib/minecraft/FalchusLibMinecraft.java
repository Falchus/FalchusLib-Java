package com.falchus.lib.minecraft;

import com.falchus.lib.minecraft.enums.Software;
import org.jetbrains.annotations.Nullable;

/**
 * Class for detecting the Minecraft server software at runtime.
 */
public class FalchusLibMinecraft {

    private static Software cached;

    /**
     * Detects the server software by checking for known classes.
     * Result is cached after first call.
     *
     * @return {@link Software} or {@code null} if unknown.
     */
    public static @Nullable Software getSoftware() {
        if (cached != null) return cached;

        String[] classNames = {
                "io.papermc.paper.threadedregions.RegionizedServer",
                "org.bukkit.plugin.java.JavaPlugin",
                "com.velocitypowered.api.plugin.Plugin"
        };
        Software[] softwares = {
                Software.FOLIA,
                Software.SPIGOT,
                Software.VELOCITY
        };

        for (int i = 0; i < classNames.length; i++) {
            try {
                Class.forName(classNames[i]);
                cached = softwares[i];
                return cached;
            } catch (ClassNotFoundException ignored) {
            }
        }
        return null;
    }

    /**
     * @return {@code true} if the server is running Folia.
     */
    public static boolean isFolia() {
        return getSoftware() == Software.FOLIA;
    }
}
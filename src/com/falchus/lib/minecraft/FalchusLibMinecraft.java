package com.falchus.lib.minecraft;

import com.falchus.lib.minecraft.enums.Software;

/**
 * Class for detecting the Minecraft server software at runtime.
 */
public class FalchusLibMinecraft {

    private static Software software;

	/**
	 * Detects the server software by checking for known classes.
	 * 
     * @return {@link Software} or {@code null} if unknown.
	 */
    public static Software getSoftware() {
        if (software != null) return software;
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
                software = softwares[i];
                return software;
            } catch (ClassNotFoundException ignored) {}
        }
        return null;
    }
}

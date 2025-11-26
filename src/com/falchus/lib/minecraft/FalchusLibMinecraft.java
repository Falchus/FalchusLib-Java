package com.falchus.lib.minecraft;

import com.falchus.lib.minecraft.enums.Software;

/**
 * Class for detecting the Minecraft server software at runtime.
 */
public class FalchusLibMinecraft {
	
	/**
	 * Detects the server software by checking for known classes.
	 * 
     * @return {@link Software} or {@code null} if unknown.
	 */
	public static Software getSoftware() {
		String[] classNames = {
            "org.bukkit.plugin.java.JavaPlugin",
            "net.md_5.bungee.api.plugin.Plugin",
            "com.velocitypowered.api.plugin.Plugin"
		};
		Software[] softwares = {
			Software.SPIGOT,
			Software.BUNGEECORD,
			Software.VELOCITY
		};
		
		for (int i = 0; i < classNames.length; i++) {
			try {
				Class.forName(classNames[i]);
				return softwares[i];
			} catch (ClassNotFoundException ignored) {}
		}
		return null;
	}
}

package com.falchus.lib.minecraft.spigot.utils;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;

import lombok.experimental.UtilityClass;

/**
 * Utility class for server.
 */
@UtilityClass
public class ServerUtils {
	
	private static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();

	/**
	 * @return MinecraftServer
	 */
	public static Object getMinecraftServer() {
		return plugin.getContexts().getNmsAdapter().getMinecraftServer();
	}
	
	/**
	 * @return e.g. "1.8.8"
	 */
	public static String getVersion() {
		return plugin.getContexts().getNmsAdapter().getVersion();
	}
}
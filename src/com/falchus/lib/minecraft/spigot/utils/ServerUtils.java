package com.falchus.lib.minecraft.spigot.utils;

import org.bukkit.Server;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ServerUtils {
	
	private static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();

	/**
	 * @return MinecraftServer
	 */
	public static Object getMinecraftServer() {
		return plugin.getNmsAdapter().getMinecraftServer();
	}
	
	/**
	 * @return {@link Server}
	 */
	public static Object getBukkitServer() {
		return plugin.getNmsAdapter().getBukkitServer();
	}
	
	/**
	 * @return e.g. "1.8.8"
	 */
	public static String getVersion() {
		return plugin.getNmsAdapter().getVersion();
	}
	
	/**
	 * @return e.g. 8 for 1.8.8
	 */
	public static int getMinorVersion() {
		return plugin.getNmsAdapter().getMinorVersion();
	}
	
	/**
	 * @return recent TPS
	 */
	public static double[] getRecentTps() {
		return plugin.getNmsAdapter().getRecentTps();
	}
}
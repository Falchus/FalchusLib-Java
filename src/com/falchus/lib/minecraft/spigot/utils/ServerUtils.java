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
		return plugin.getContexts().getNmsAdapter().getMinecraftServer();
	}
	
	/**
	 * @return {@link Server}
	 */
	public static Object getBukkitServer() {
		return plugin.getContexts().getNmsAdapter().getBukkitServer();
	}
	
	/**
	 * @return e.g. "1.8.8"
	 */
	public static String getVersion() {
		return plugin.getContexts().getNmsAdapter().getVersion();
	}
}
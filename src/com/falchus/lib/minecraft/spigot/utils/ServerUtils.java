package com.falchus.lib.minecraft.spigot.utils;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;

import lombok.experimental.UtilityClass;
import net.minecraft.server.v1_8_R3.MinecraftServer;

/**
 * Utility class for server.
 */
@UtilityClass
public class ServerUtils {

	/**
	 * Gets {@link MinecraftServer}.
	 */
	public static MinecraftServer getMinecraftServer() {
		return ((CraftServer) Bukkit.getServer()).getServer();
	}
	
	/**
	 * Gets the version of the server.
	 * 
	 * @return e.g. "1.8.8"
	 */
	public static String getVersion() {
		return getMinecraftServer().getVersion();
	}
}
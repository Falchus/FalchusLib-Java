package com.falchus.lib.minecraft.spigot.utils;

import org.bukkit.Server;

import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ServerUtils {

	/**
	 * @return MinecraftServer
	 */
	public static Object getMinecraftServer() {
		return VersionProvider.get().getMinecraftServer();
	}
	
	/**
	 * @return {@link Server}
	 */
	public static Object getBukkitServer() {
		return VersionProvider.get().getBukkitServer();
	}
	
	/**
	 * @return e.g. "1.8.8"
	 */
	public static String getVersion() {
		return VersionProvider.get().getVersion();
	}
	
	/**
	 * @return e.g. 8 for 1.8.8
	 */
	public static int getMinorVersion() {
		return VersionProvider.get().getMinorVersion();
	}
	
	/**
	 * @return recent TPS
	 */
	public static double[] getRecentTps() {
		return VersionProvider.get().getRecentTps();
	}
}
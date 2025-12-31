package com.falchus.lib.minecraft.spigot.utils;

import com.falchus.lib.minecraft.spigot.utils.nms.NmsAdapter;
import com.falchus.lib.minecraft.spigot.utils.nms.NmsProvider;

import lombok.experimental.UtilityClass;

/**
 * Utility class for server.
 */
@UtilityClass
public class ServerUtils {
	
	private static final NmsAdapter nms = NmsProvider.get();

	/**
	 * @return MinecraftServer
	 */
	public static Object getMinecraftServer() {
		return nms.getMinecraftServer();
	}
	
	/**
	 * @return e.g. "1.8.8"
	 */
	public static String getVersion() {
		return nms.getVersion();
	}
}
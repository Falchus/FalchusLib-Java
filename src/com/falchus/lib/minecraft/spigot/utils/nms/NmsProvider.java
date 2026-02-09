package com.falchus.lib.minecraft.spigot.utils.nms;

import org.bukkit.Bukkit;

import com.falchus.lib.minecraft.spigot.utils.nms.v1_9_R1.NmsAdapter_v1_9_R1;

import lombok.experimental.UtilityClass;

/**
 * Provides the {@link NmsAdapter} for the current server version.
 * By default, returns {@link NmsAdapterDefault}.
 */
@UtilityClass
public class NmsProvider {

	private static NmsAdapter adapter;
	
	private static NmsAdapter load() {
		String bukkitVersion = Bukkit.getBukkitVersion();
		String mc = bukkitVersion.split("-")[0];
		
		int minor;
		try {
            minor = Integer.parseInt(mc.split("\\.")[1]);
		} catch (Exception e) {
            return new NmsAdapterDefault();
        }
		
        if (minor >= 17) {
        	return new NmsAdapterModern();
        } else if (minor >= 9) {
        	return new NmsAdapter_v1_9_R1();
        }
		
        String packageName = Bukkit.getServer().getClass().getPackageName();
        String[] parts = packageName.split("\\.");
        if (parts.length < 4) {
        	return new NmsAdapterDefault();
        }
        
        String version = parts[3];
		try {
			Class<?> clazz = Class.forName(NmsProvider.class.getPackageName() + "." + version + ".NmsAdapter_" + version);
			return (NmsAdapter) clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			return new NmsAdapterDefault();
		}
	}
	
	public static NmsAdapter get() {
		if (adapter == null) {
			adapter = load();
		}
		return adapter;
	}
}

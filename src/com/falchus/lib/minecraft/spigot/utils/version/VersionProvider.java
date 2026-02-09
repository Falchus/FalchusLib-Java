package com.falchus.lib.minecraft.spigot.utils.version;

import org.bukkit.Bukkit;

import com.falchus.lib.minecraft.spigot.utils.version.v1_9_R1.VersionAdapter_v1_9_R1;
import com.falchus.lib.utils.ReflectionUtils;

import lombok.experimental.UtilityClass;

/**
 * Provides the {@link IVersionAdapter} for the current server version.
 * By default, returns {@link VersionAdapter}.
 */
@UtilityClass
public class VersionProvider {

	private static IVersionAdapter adapter;
	
	private static IVersionAdapter load() {
		String bukkitVersion = Bukkit.getBukkitVersion();
		String mc = bukkitVersion.split("-")[0];
		
		int minor;
		try {
            minor = Integer.parseInt(mc.split("\\.")[1]);
		} catch (Exception e) {
            return new VersionAdapter();
        }
		
        if (minor >= 17) {
        	return new VersionAdapterModern();
        } else if (minor >= 9) {
        	return new VersionAdapter_v1_9_R1();
        }
		
        String packageName = Bukkit.getServer().getClass().getPackageName();
        String[] parts = packageName.split("\\.");
        if (parts.length < 4) {
        	return new VersionAdapter();
        }
        
        String version = parts[3];
		try {
			Class<?> clazz = ReflectionUtils.getClass(VersionProvider.class.getPackageName() + "." + version + "." + VersionAdapter.class.getSimpleName() + "_" + version);
			return (IVersionAdapter) clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			return new VersionAdapter();
		}
	}
	
	public static IVersionAdapter get() {
		if (adapter == null) {
			adapter = load();
		}
		return adapter;
	}
}

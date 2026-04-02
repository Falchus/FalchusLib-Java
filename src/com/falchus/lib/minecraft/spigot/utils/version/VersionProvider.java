package com.falchus.lib.minecraft.spigot.utils.version;

import org.bukkit.Bukkit;

import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.utils.version.v1_9_R1.VersionAdapter_v1_9_R1;
import com.falchus.lib.minecraft.spigot.utils.version.v_1_13_R1.VersionAdapter_v_1_13_R1;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.experimental.UtilityClass;

/**
 * Provides the {@link IVersionAdapter} for the current server version.
 * By default, returns {@link VersionAdapter}.
 */
@UtilityClass
public class VersionProvider {

	private static IVersionAdapter adapter;
	
	private static IVersionAdapter load() {
		Version version = ServerUtils.getVersion();
		if (version.isAfter(Version.v1_16)) return new VersionAdapterModern();
		if (version.isAfter(Version.v1_12)) return new VersionAdapter_v_1_13_R1();
		if (version.isAfter(Version.v1_8)) return new VersionAdapter_v1_9_R1();
		if (version.isBefore(Version.v1_9)) return new VersionAdapter();
		
		try {
	        String[] parts = Bukkit.getServer().getClass().getPackageName().split("\\.");
	        if (parts.length >= 4) {
	            String ver = parts[3];
    			return (IVersionAdapter) new ClassInstanceBuilder(
    				VersionProvider.class.getPackageName() + "." + ver + "." + VersionAdapter.class.getSimpleName() + "_" + ver
    			).build();
	        }
		} catch (Exception ignored) {}
		return new VersionAdapter();
	}
	
	public static IVersionAdapter get() {
		if (adapter == null) {
			adapter = load();
		}
		return adapter;
	}
}

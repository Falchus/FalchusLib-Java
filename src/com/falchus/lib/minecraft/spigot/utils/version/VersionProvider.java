package com.falchus.lib.minecraft.spigot.utils.version;

import org.bukkit.Bukkit;

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
		int major = ServerUtils.getMajorVersion();
		int minor = ServerUtils.getMinorVersion();
    	switch (major) {
    		case 1:
    			if (minor >= 17) {
    	        	return new VersionAdapterModern();
    			} else if (minor >= 13) {
    				return new VersionAdapter_v_1_13_R1();
    			} else if (minor >= 9) {
    				return new VersionAdapter_v1_9_R1();
    			}
    			break;
    		case 2:
    			return new VersionAdapterModern();

			default:
				return new VersionAdapter();
    	}
		
        String packageName = Bukkit.getServer().getClass().getPackageName();
        String[] parts = packageName.split("\\.");
        if (parts.length < 4) {
        	return new VersionAdapter();
        }
        
        String version = parts[3];
		try {
			return (IVersionAdapter) new ClassInstanceBuilder(
				VersionProvider.class.getPackageName() + "." + version + "." + VersionAdapter.class.getSimpleName() + "_" + version
			).build();
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

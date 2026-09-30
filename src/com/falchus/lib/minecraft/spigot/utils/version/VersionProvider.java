package com.falchus.lib.minecraft.spigot.utils.version;

import org.bukkit.Bukkit;

import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.utils.version.v1_13_R1.VersionAdapter_v1_13_R1;
import com.falchus.lib.minecraft.spigot.utils.version.v1_15_R1.VersionAdapter_v1_15_R1;
import com.falchus.lib.minecraft.spigot.utils.version.v1_20_R4.VersionAdapter_v1_20_R4;
import com.falchus.lib.minecraft.spigot.utils.version.v1_21_R1.VersionAdapter_v1_21_R1;
import com.falchus.lib.minecraft.spigot.utils.version.v1_9_R1.VersionAdapter_v1_9_R1;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.experimental.UtilityClass;

/**
 * Provides the {@link IVersionAdapter} for the current server version.
 * By default, returns {@link VersionAdapter}.
 */
@UtilityClass
public class VersionProvider {

	private static IVersionAdapter adapter;

	private static IVersionAdapter load(String name) {
		try {
			String[] parts = Bukkit.getServer().getClass().getPackageName().split("\\.");
			if (parts.length < 4) return null;
			String ver = parts[3];
			name = name != null
				? "_" + name
				: "";
			return (IVersionAdapter) new ClassInstanceBuilder(
				VersionProvider.class.getPackageName() + "." + ver + "." + VersionAdapter.class.getSimpleName() + "_" + ver + name
			).build();
		} catch (Exception ignored) {
			return null;
		}
	}
	
	private static IVersionAdapter load() {
		Version version = ServerUtils.getVersion();
		Version.Software software = version.getSoftware();
		if (software != null) {
			for (String name : software.getNames()) {
				IVersionAdapter adapter = load(name);
				if (adapter != null) {
					return adapter;
				}
			}
		}

		if (version.isAfter(Version.v1_20_6)) return new VersionAdapter_v1_21_R1();
		if (version.isAfter(Version.v1_20_2)) return new VersionAdapter_v1_20_R4();
		if (version.isAfter(Version.v1_16_5)) return new VersionAdapterModern();
		if (version.isAfter(Version.v1_14_4)) return new VersionAdapter_v1_15_R1();
		if (version.isAfter(Version.v1_12_2)) return new VersionAdapter_v1_13_R1();
		if (version.isAfter(Version.v1_8_8)) return new VersionAdapter_v1_9_R1();
		if (version.isBefore(Version.v1_9)) return new VersionAdapter();

		IVersionAdapter adapter = load(null);
		return adapter != null ? adapter : new VersionAdapter();
	}
	
	public static IVersionAdapter get() {
		if (adapter == null) {
			adapter = load();
		}
		return adapter;
	}
}

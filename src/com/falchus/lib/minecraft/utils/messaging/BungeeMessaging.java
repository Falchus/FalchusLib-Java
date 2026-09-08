package com.falchus.lib.minecraft.utils.messaging;

import com.falchus.lib.FalchusLib;

import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BungeeMessaging {
	
	@Setter private static BungeeMessagingAdapter adapter;
	
	public static final String channel = FalchusLib.nameFull.toLowerCase() + ":bungee";
	
	public static void execute(@NonNull String command) {
		getAdapter().execute(command);
	}
	
	public static void connect(@NonNull String name, @NonNull String server) {
		getAdapter().connect(name, server);
	}
	
	public static boolean isAvailable() {
		return getAdapter().isAvailable();
	}
	
	public static BungeeMessagingAdapter getAdapter() {
		if (adapter == null) {
			throw new IllegalStateException("Adapter not set");
		}
		return adapter;
	}
}

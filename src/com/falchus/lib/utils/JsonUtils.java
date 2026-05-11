package com.falchus.lib.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {
	
	public static String getObject(@NonNull String json, @NonNull String key) {
		return json
				.split("\"" + key + "\":")[1];
	}

	public static String getString(@NonNull String json, @NonNull String key) {
		return json
				.split("\"" + key + "\": \"")[1]
				.split("\"")[0];
	}
	
	public static long getLong(@NonNull String json, @NonNull String key) {
		String raw = json
				.split("\"" + key + "\":")[1]
				.split("[,}]")[0]
				.replaceAll("[^0-9]", "");
		return raw.isEmpty() ? 0 : Long.parseLong(raw);
	}
	
	public static boolean getBoolean(@NonNull String json, @NonNull String key) {
		String raw = json
				.split("\"" + key + "\":")[1]
				.split("[,}]")[0]
				.trim();
		return raw.equals("true");
	}
}

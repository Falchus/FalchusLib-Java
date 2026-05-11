package com.falchus.lib.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {
	
	public static JsonElement get(@NonNull String json, @NonNull String path) {
		JsonElement current = JsonParser.parseString(json);
		for (String key : path.split("\\.")) {
			if (current == null || current.isJsonNull()) return null;
			
			if (key.endsWith("]")) {
				int bracket = key.indexOf('[');
				current = current.getAsJsonObject().get(key.substring(0, bracket));
				if (current == null || current.isJsonNull()) return null;
				
				current = current.getAsJsonArray().get(Integer.parseInt(key.substring(bracket + 1, key.length() - 1)));
				continue;
			}
			
			JsonObject object = current.getAsJsonObject();
			current = object.get(key);
		}
		return current;
	}

	public static String getString(@NonNull String json, @NonNull String path) {
		JsonElement element = get(json, path);
		return element == null
				? null
				: element.getAsString();
	}
	
	public static long getLong(@NonNull String json, @NonNull String path) {
		JsonElement element = get(json, path);
		return element == null
				? 0
				: element.getAsLong();
	}
	
	public static boolean getBoolean(@NonNull String json, @NonNull String path) {
		JsonElement element = get(json, path);
		return element != null
				&& element.getAsBoolean();
	}
}

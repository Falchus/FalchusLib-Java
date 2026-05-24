package com.falchus.lib.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JsonUtils {
	
	private static final Pattern bracketPattern = Pattern.compile("\\[(\\d+)\\]");
	private static final Pattern dotPattern = Pattern.compile("\\.");
	
	public static JsonElement get(@NonNull String json, @NonNull String path) {
		if (json.trim().isEmpty()) return null;
		
		try {
			JsonElement current = JsonParser.parseString(json);
			for (String key : dotPattern.split(path)) {
				if (current == null || current.isJsonNull()) return null;
				
				if (key.contains("[")) {
					int bracket = key.indexOf('[');
					String base = key.substring(0, bracket);
					if (!base.isEmpty()) {
						if (!current.isJsonObject()) return null;
						current = current.getAsJsonObject().get(base);
					}
					
					Matcher matcher = bracketPattern.matcher(key.substring(bracket));
					while (matcher.find()) {
						if (current == null || current.isJsonNull() || !current.isJsonArray()) return null;
						int i = Integer.parseInt(matcher.group(1));
						if (i < 0 || i >= current.getAsJsonArray().size()) return null;
						
						current = current.getAsJsonArray().get(i);
					}
					continue;
				}
				if (!current.isJsonObject()) return null;
				JsonObject object = current.getAsJsonObject();
				current = object.get(key);
			}
			return current;
		} catch (Exception e) {
			return null;
		}
	}

	public static String getString(@NonNull String json, @NonNull String path) {
		JsonElement element = get(json, path);
		return element == null || element.isJsonNull() || !element.isJsonPrimitive()
				? null
				: element.getAsString();
	}
	
	public static long getLong(@NonNull String json, @NonNull String path) {
		JsonElement element = get(json, path);
		return element == null || element.isJsonNull() || !element.isJsonPrimitive()
				? 0
				: element.getAsLong();
	}
	
	public static boolean getBoolean(@NonNull String json, @NonNull String path) {
		JsonElement element = get(json, path);
		return element != null && !element.isJsonNull() && element.isJsonPrimitive()
				&& element.getAsBoolean();
	}
}

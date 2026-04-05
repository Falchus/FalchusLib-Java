package com.falchus.lib.storage.serializer.json;

import org.json.simple.JSONArray;

public abstract class JsonArraySerializer<T> extends JsonSerializer<T, JSONArray> {
	
	@Override
	public String serialize(T value) {
		return to(value).toJSONString();
	}
	
	@Override
	public T deserialize(String content) {
		if (content == null || content.isBlank()) {
			return from(new JSONArray());
		}
		
		try {
			Object raw = parser.parse(content);
			if (raw instanceof JSONArray json) {
				return from(json);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return from(new JSONArray());
	}
}

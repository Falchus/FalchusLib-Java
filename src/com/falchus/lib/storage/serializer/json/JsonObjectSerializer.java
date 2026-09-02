package com.falchus.lib.storage.serializer.json;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

public class JsonObjectSerializer<T> extends JsonSerializer<T, JSONObject> {
	
	@Override
	public String serialize(T value) {
		return to(value).toJSONString();
	}
	
	@Override
	public T deserialize(String content) {
		if (content == null || content.isBlank()) {
			return from(new JSONObject());
		}
		
		try {
			Object raw = parser.parse(content);
			if (raw instanceof JSONObject json) {
				return from(json);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return from(new JSONObject());
	}
	
	@Override
	public JSONObject to(T value) {
		return new JSONObject((Map<?, ?>) value);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T from(JSONObject json) {
		return (T) new HashMap<>(json);
	}
}

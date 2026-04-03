package com.falchus.lib.storage.serializer.json;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.falchus.lib.interfaces.Mapper;
import com.falchus.lib.storage.serializer.Serializer;

public abstract class JsonObjectSerializer<T> implements Mapper<T, JSONObject>, Serializer<T> {
	
	private final JSONParser parser = new JSONParser();
	
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
}

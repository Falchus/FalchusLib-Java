package com.falchus.lib.storage.impl.json;

import java.nio.file.Path;

import org.json.simple.JSONObject;

import com.falchus.lib.storage.serializer.Serializer;

public class JsonObjectStorage extends JsonStorage {

	public JsonObjectStorage(Serializer<?> serializer, Path folder, String fileName) {
		super(serializer, folder, fileName, new JSONObject().toJSONString());
	}
}

package com.falchus.lib.storage.impl.json;

import java.nio.file.Path;

import org.json.simple.JSONArray;

import com.falchus.lib.storage.serializer.Serializer;

public class JsonArrayStorage extends JsonStorage {

	public JsonArrayStorage(Serializer<?> serializer, Path folder, String fileName) {
		super(serializer, folder, fileName, new JSONArray().toJSONString());
	}
}

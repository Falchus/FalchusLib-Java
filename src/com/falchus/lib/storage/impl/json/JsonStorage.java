package com.falchus.lib.storage.impl.json;

import java.nio.file.Path;

import com.falchus.lib.storage.Storage;
import com.falchus.lib.storage.serializer.Serializer;

public class JsonStorage extends Storage {

	public JsonStorage(Serializer<?> serializer, Path folder, String fileName, String defaultContent) {
		super(serializer, folder, fileName + ".json", defaultContent);
	}
}

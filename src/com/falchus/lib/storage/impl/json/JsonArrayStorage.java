package com.falchus.lib.storage.impl.json;

import com.falchus.lib.storage.serializer.Serializer;
import org.json.simple.JSONArray;

import java.nio.file.Path;

public class JsonArrayStorage extends JsonStorage {

    public JsonArrayStorage(Serializer<?> serializer, Path folder, String fileName) {
        super(serializer, folder, fileName, new JSONArray().toJSONString());
    }
}

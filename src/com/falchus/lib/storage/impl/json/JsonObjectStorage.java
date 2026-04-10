package com.falchus.lib.storage.impl.json;

import com.falchus.lib.storage.serializer.Serializer;
import org.json.simple.JSONObject;

import java.nio.file.Path;

public class JsonObjectStorage extends JsonStorage {

    public JsonObjectStorage(Serializer<?> serializer, Path folder, String fileName) {
        super(serializer, folder, fileName, new JSONObject().toJSONString());
    }
}

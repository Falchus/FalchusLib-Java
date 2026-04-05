package com.falchus.lib.storage.serializer.json;

import org.json.simple.parser.JSONParser;

import com.falchus.lib.interfaces.Mapper;
import com.falchus.lib.storage.serializer.Serializer;

abstract class JsonSerializer<T, U> implements Mapper<T, U>, Serializer<T> {
	
	final JSONParser parser = new JSONParser();
}

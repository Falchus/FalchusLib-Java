package com.falchus.lib.storage.serializer;

public interface Serializer<T> {

	String serialize(T value);
	
	T deserialize(String content);
}

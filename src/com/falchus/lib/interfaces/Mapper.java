package com.falchus.lib.interfaces;

public interface Mapper<T, U> {

	U to(T value);
	
	T from(U value);
}

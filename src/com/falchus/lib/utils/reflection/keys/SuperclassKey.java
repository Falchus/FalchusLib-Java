package com.falchus.lib.utils.reflection.keys;

import java.util.Set;

import lombok.NonNull;

public record SuperclassKey(@NonNull Set<Class<?>> classes) {
	
	public SuperclassKey(@NonNull Class<?>... classes) {
		this(Set.of(classes));
	}
}

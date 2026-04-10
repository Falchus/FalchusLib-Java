package com.falchus.lib.utils.reflection.keys;

import java.util.List;
import java.util.Set;

import lombok.NonNull;

public record MethodKey(@NonNull Set<Class<?>> classes, List<Class<?>> params, @NonNull Set<String> names) {
	
	public MethodKey(@NonNull Class<?> clazz, @NonNull String name, @NonNull Class<?>... params) {
		this(Set.of(clazz), List.of(params), Set.of(name));
	}
	
	public MethodKey(@NonNull Class<?> clazz, List<Class<?>> params, @NonNull String... names) {
		this(Set.of(clazz), params, Set.of(names));
	}
	
	public MethodKey(@NonNull Set<Class<?>> classes, List<Class<?>> params, @NonNull String... names) {
		this(classes, params, Set.of(names));
	}
}

package com.falchus.lib.utils.reflection.keys;

import lombok.NonNull;

import java.util.List;
import java.util.Set;

public record ConstructorKey(@NonNull Set<Class<?>> classes, Set<List<Class<?>>> params) {

    public ConstructorKey(@NonNull Class<?> clazz, Class<?>... params) {
        this(Set.of(clazz), Set.of(List.of(params)));
    }

    public ConstructorKey(@NonNull Class<?> clazz, Set<List<Class<?>>> params) {
        this(Set.of(clazz), params);
    }
}

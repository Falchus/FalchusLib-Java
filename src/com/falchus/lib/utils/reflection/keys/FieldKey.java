package com.falchus.lib.utils.reflection.keys;

import lombok.NonNull;

import java.util.Set;

public record FieldKey(@NonNull Set<Class<?>> classes, @NonNull Set<String> names) {

    public FieldKey(@NonNull Class<?> clazz, @NonNull String name) {
        this(Set.of(clazz), Set.of(name));
    }

    public FieldKey(@NonNull Class<?> clazz, @NonNull String... names) {
        this(Set.of(clazz), Set.of(names));
    }

    public FieldKey(@NonNull Set<Class<?>> classes, @NonNull String... names) {
        this(classes, Set.of(names));
    }
}

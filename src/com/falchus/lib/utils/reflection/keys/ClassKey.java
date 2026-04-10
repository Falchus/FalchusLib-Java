package com.falchus.lib.utils.reflection.keys;

import lombok.NonNull;

import java.util.Set;

public record ClassKey(@NonNull Set<String> names) {

    public ClassKey(@NonNull String... names) {
        this(Set.of(names));
    }
}

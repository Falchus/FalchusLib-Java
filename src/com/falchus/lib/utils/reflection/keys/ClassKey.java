package com.falchus.lib.utils.reflection.keys;

import java.util.Set;

import lombok.NonNull;

public record ClassKey(@NonNull Set<String> names) {
	
	public ClassKey(@NonNull String... names) {
		this(Set.of(names));
	}
}

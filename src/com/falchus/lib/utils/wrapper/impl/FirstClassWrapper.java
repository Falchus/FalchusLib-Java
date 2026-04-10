package com.falchus.lib.utils.wrapper.impl;

import java.util.Set;
import java.util.stream.Collectors;

import com.falchus.lib.utils.reflection.ReflectionUtils;

import lombok.NonNull;

public class FirstClassWrapper<T> extends ClassWrapper<T> {

	public FirstClassWrapper(@NonNull T handle, @NonNull Set<String> names) {
		super(handle, names.stream()
				.map(name -> {
					try {
						return ReflectionUtils.getFirstClass(name);
					} catch (RuntimeException e) {
						return null;
					}
				})
				.filter(c -> c != null)
				.collect(Collectors.toSet())
			);
	}
}

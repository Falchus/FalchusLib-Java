package com.falchus.lib.utils.wrapper;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;
import com.falchus.lib.utils.reflection.Dummy;
import com.falchus.lib.utils.wrapper.impl.FirstClassWrapper;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class WrapperRegistry {

	private static final Map<Class<?>, Function<Object, Wrapper<?>>> registry = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	public static <T> void register(@NonNull Class<T> clazz, @NonNull Function<T, Wrapper<?>> factory) {
		registry.putIfAbsent(clazz, obj -> factory.apply((T) obj));
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends FirstClassWrapper<?>> void register(@NonNull Class<T> clazz) {
		T dummy = (T) new ClassInstanceBuilder(
			clazz
		).withParams(
			Map.of(
				Object.class,
				Dummy.instance
			)
		).build();
		for (Class<?> classes : dummy.getClasses()) {
			register(classes, obj ->
				(T) new ClassInstanceBuilder(
					clazz
				).withParams(
					Map.of(
						Object.class,
						obj
					)
				).build()
			);
		}
	}
	
	public static <T extends Wrapper<?>> T wrap(@NonNull Object handle) {
		return wrap(handle, null);
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Wrapper<?>> T wrap(@NonNull Object handle, Class<T> type) {
		Class<?> clazz = handle.getClass();
		
		Function<Object, Wrapper<?>> factory = registry.get(clazz);
		if (factory != null) {
			Wrapper<?> result = factory.apply(handle);
			if (type == null || type.isInstance(result)) {
				return (T) result;
			}
		}
		
		for (Map.Entry<Class<?>, Function<Object, Wrapper<?>>> entry : registry.entrySet()) {
			if (entry.getKey().isAssignableFrom(clazz)) {
				Wrapper<?> result = entry.getValue().apply(handle);
				if (type == null || type.isInstance(result)) {
					return (T) result;
				}
			}
		}
		return (T) new Wrapper<>(handle);
	}
}

package com.falchus.lib.utils;

import java.lang.reflect.Field;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReflectionUtils {

	public static Object getField(@NonNull Object instance, @NonNull String name) {
		try {
			Field field = instance.getClass().getDeclaredField(name);
			field.setAccessible(true);
			return field.get(instance);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Object getStaticField(@NonNull Class<?> clazz, @NonNull String name) {
		try {
			Field field = clazz.getDeclaredField(name);
			field.setAccessible(true);
			return field.get(null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void setField(@NonNull Object instance, @NonNull String name, Object value) {
		try {
            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);
            field.set(instance, value);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
    public static void setStaticField(@NonNull Class<?> clazz, @NonNull String name, Object value) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            field.set(null, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

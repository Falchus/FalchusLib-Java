package com.falchus.lib.utils;

import java.lang.reflect.Field;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReflectionUtils {

    public static Class<?> getClass(@NonNull String className) {
        try {
            return Class.forName(className);
        } catch (Exception e) {
            throw new RuntimeException("Class not found: " + className, e);
        }
    }
	
    public static Field getField(@NonNull Object instance, @NonNull String name) {
        try {
            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
    public static Field getStaticField(@NonNull Class<?> clazz, @NonNull String name) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return field;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Class<?> getFirstAvailableClass(@NonNull String... classNames) {
        for (String name : classNames) {
            try {
                return Class.forName(name);
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("None of the classes exist: " + String.join(", ", classNames));
    }
    
    public static Field getFirstAvailableField(@NonNull Object instance, @NonNull String... names) {
        for (String name : names) {
            try {
                return getField(instance, name);
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("None of the fields exist: " + String.join(", ", names));
    }
    
    public static Field getFirstAvailableStaticField(@NonNull Class<?> clazz, @NonNull String... names) {
        for (String name : names) {
            try {
                return getStaticField(clazz, name);
            } catch (Exception ignored) {}
        }
        throw new RuntimeException("None of the static fields exist: " + String.join(", ", names));
    }
    
    public static void setField(@NonNull Object instance, @NonNull String name, Object value) {
        try {
            Field field = getField(instance, name);
            field.set(instance, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void setStaticField(@NonNull Class<?> clazz, @NonNull String name, Object value) {
        try {
            Field field = getStaticField(clazz, name);
            field.set(null, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

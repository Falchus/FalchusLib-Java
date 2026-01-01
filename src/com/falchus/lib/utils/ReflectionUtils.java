package com.falchus.lib.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReflectionUtils {

    public static Class<?> getClass(@NonNull String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
        	return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Class<?> getFirstClass(@NonNull String... names) {
        for (String name : names) {
        	Class<?> found = getClass(name);
        	if (found != null) {
        		return found;
        	}
        }
        throw new RuntimeException("None of the classes exist: " + String.join(", ", names));
    }
	
    public static Field getField(@NonNull Object instance, @NonNull String name) {
        return getField(instance.getClass(), name);
    }
    
    public static Field getField(@NonNull Class<?> clazz, @NonNull String name) {
        try {
            Field field = clazz.getDeclaredField(name);
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Field getFirstField(@NonNull Object instance, @NonNull String... names) {
        return getFirstField(instance.getClass(), names);
    }
    
    public static Field getFirstField(@NonNull Class<?> clazz, @NonNull String... names) {
        for (String name : names) {
            Field found = getField(clazz, name);
            if (found != null) {
            	return found;
            }
        }
        throw new RuntimeException("None of the fields exist: " + String.join(", ", names));
    }
    
    public static void setField(@NonNull Object instance, @NonNull String name, Object value) {
        try {
            Field field = getField(instance, name);
            field.set(instance, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void setField(@NonNull Class<?> clazz, @NonNull String name, Object value) {
        try {
            Field field = getField(clazz, name);
            field.set(null, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Method getMethod(@NonNull Class<?> clazz, @NonNull String name, Class<?>... params) {
        try {
            Method method = clazz.getDeclaredMethod(name, params);
            method.setAccessible(true);
            return method;
        } catch (NoSuchMethodException e) {
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Method getFirstMethod(@NonNull Class<?> clazz, List<Class<?>> params, @NonNull String... names) {
        for (String name : names) {
            Method found = getMethod(clazz, name, params.toArray(new Class[0]));
            if (found != null) {
                return found;
            }
        }
        throw new RuntimeException("None of the methods exist: " + String.join(", ", names));
    }
}

package com.falchus.lib.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

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
    
    public static Field getField(@NonNull Class<?> clazz, @NonNull String name) {
    	Class<?> current = clazz;
    	
    	while (current != null) {
    		try {
    			Field field = current.getField(name);
    			field.setAccessible(true);
    			return field;
    		} catch (NoSuchFieldException ignored) {}
    		
    		try {
    			Field field = current.getDeclaredField(name);
    			field.setAccessible(true);
    			return field;
    		} catch (NoSuchFieldException ignored) {}
    		
    		current = current.getSuperclass();
    	}
    	return null;
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
    
    public static Field getFirstField(@NonNull Object instance, @NonNull String... names) {
        return getFirstField(instance.getClass(), names);
    }
    
    public static void setField(@NonNull Object instance, @NonNull Field field, Object value) {
        try {
            field.set(instance, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void setField(@NonNull Object instance, @NonNull String name, Object value) {
        try {
            Field field = getField(instance.getClass(), name);
            setField(instance, field, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void setField(@NonNull Field field, Object value) {
        try {
            field.set(null, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void setField(@NonNull Class<?> clazz, @NonNull String name, Object value) {
        try {
            Field field = getField(clazz, name);
            setField(field, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static Method getMethod(@NonNull Class<?> clazz, @NonNull String name, Class<?>... params) {
    	Class<?> current = clazz;
    	
    	while (current != null) {
            try {
                Method method = current.getMethod(name, params);
                method.setAccessible(true);
                return method;
            } catch (NoSuchMethodException ignored) {}
            
            try {
                Method method = current.getDeclaredMethod(name, params);
                method.setAccessible(true);
                return method;
            } catch (NoSuchMethodException ignored) {}
            
        	current = current.getSuperclass();
    	}
    	return null;
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
    
    public static Constructor<?> getConstructor(@NonNull Class<?> clazz, Class<?>... params) {
        try {
            Constructor<?> ctor = clazz.getDeclaredConstructor(params);
            ctor.setAccessible(true);
            return ctor;
        } catch (NoSuchMethodException e) {
        	return null;
        } catch (Exception e) {
        	throw new RuntimeException(e);
        }
    }
    
    public static Constructor<?> getFirstConstructor(@NonNull Class<?> clazz, Set<List<Class<?>>> params) {
        for (List<Class<?>> list : params) {
        	Constructor<?> found = getConstructor(clazz, list.toArray(new Class[0]));
            if (found != null) {
                return found;
            }
        }
        throw new RuntimeException("No matching constructor found for class: " + clazz.getName());
    }
}

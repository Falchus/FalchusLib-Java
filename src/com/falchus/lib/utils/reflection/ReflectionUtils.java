package com.falchus.lib.utils.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.falchus.lib.utils.reflection.keys.*;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReflectionUtils {
	
	private final Map<ClassKey, Optional<Class<?>>> classes = new ConcurrentHashMap<>();
	private final Map<ClassKey, Optional<Class<?>>> firstClasses = new ConcurrentHashMap<>();
	
	private final Map<FieldKey, Optional<Field>> fields = new ConcurrentHashMap<>();
	private final Map<FieldKey, Optional<Field>> firstFields = new ConcurrentHashMap<>();
	private final Map<FieldKey, Optional<Field>> firstFieldsClasses = new ConcurrentHashMap<>();
	
	private final Map<MethodKey, Optional<Method>> methods = new ConcurrentHashMap<>();
	private final Map<MethodKey, Optional<Method>> firstMethods = new ConcurrentHashMap<>();
	private final Map<MethodKey, Optional<Method>> firstMethodsClasses = new ConcurrentHashMap<>();
	
	private final Map<ConstructorKey, Optional<Constructor<?>>> constructors = new ConcurrentHashMap<>();
	private final Map<ConstructorKey, Optional<Constructor<?>>> firstConstructors = new ConcurrentHashMap<>();
	private final Map<ConstructorKey, Optional<Constructor<?>>> firstConstructorsClasses = new ConcurrentHashMap<>();

    public static Class<?> getClass(@NonNull String name) {
    	return classes.computeIfAbsent(new ClassKey(name), k -> {
            try {
                return Optional.of(Class.forName(name));
            } catch (ClassNotFoundException e) {
            	return Optional.empty();
            }
    	}).orElseThrow(() ->
    		new RuntimeException()
    	);
    }
    
    public static Class<?> getFirstClass(@NonNull String... names) {
    	return firstClasses.computeIfAbsent(new ClassKey(names), k -> {
            for (String name : names) {
            	Class<?> found = getClass(name);
            	if (found != null) {
            		return Optional.of(found);
            	}
            }
            return Optional.empty();
    	}).orElseThrow(() ->
    		new RuntimeException("None of the classes exist: " + String.join(", ", names))
    	);
    }
    
    public static Field getField(@NonNull Class<?> clazz, @NonNull String name) {
    	return fields.computeIfAbsent(new FieldKey(clazz, name), k -> {
        	Class<?> current = clazz;
        	
        	while (current != null) {
        		try {
        			Field field = current.getField(name);
        			field.setAccessible(true);
        			return Optional.of(field);
        		} catch (NoSuchFieldException ignored) {}
        		
        		try {
        			Field field = current.getDeclaredField(name);
        			field.setAccessible(true);
        			return Optional.of(field);
        		} catch (NoSuchFieldException ignored) {}
        		
        		current = current.getSuperclass();
        	}
        	return Optional.empty();
    	}).orElse(null);
    }
    
    public static Field getField(@NonNull Object instance, @NonNull String name) {
    	return getField(instance.getClass(), name);
    }
    
    public static Field getFirstField(@NonNull Class<?> clazz, @NonNull String... names) {
    	return firstFields.computeIfAbsent(new FieldKey(clazz, names), k -> {
            for (String name : names) {
                Field found = getField(clazz, name);
                if (found != null) {
                	return Optional.of(found);
                }
            }
            return Optional.empty();
    	}).orElseThrow(() ->
    		new RuntimeException("None of the fields exist: " + String.join(", ", names))
    	);
    }
    
    public static Field getFirstField(@NonNull Object instance, @NonNull String... names) {
        return getFirstField(instance.getClass(), names);
    }
    
    public static Field getFirstField(@NonNull Set<Class<?>> classes, @NonNull String... names) {
    	return firstFieldsClasses.computeIfAbsent(new FieldKey(classes, names), k -> {
        	for (Class<?> clazz : classes) {
        		try {
        			return Optional.of(getFirstField(clazz, names));
        		} catch (RuntimeException ignored) {}
        	}
        	return Optional.empty();
    	}).orElseThrow(() ->
    		new RuntimeException("None of the fields exist in classes: " + classes + " - " + String.join(", ", names))
		);
    }
    
    @SuppressWarnings("unchecked")
	public static <T> T getFieldValue(Object instance, @NonNull Field field) {
    	try {
    		return (T) field.get(instance);
    	} catch (Exception e) {
    		throw new RuntimeException(e);
    	}
    }
    
	public static <T> T getFieldValue(@NonNull Field field) {
		return getFieldValue(null, field);
	}
    
    public static void setField(Object instance, @NonNull Field field, Object value) {
        try {
            field.set(instance, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void setField(Object instance, @NonNull String name, Object value) {
        try {
            setField(instance, getField(instance.getClass(), name), value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void setField(@NonNull Field field, Object value) {
    	setField(null, field, value);
    }
    
    public static void setField(@NonNull Class<?> clazz, @NonNull String name, Object value) {
        try {
            setField(getField(clazz, name), value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public static void setFirstField(Object instance, Object value, @NonNull String... names) {
    	setField(instance, getFirstField(instance, names), value);
    }
    
    public static void setFirstField(@NonNull Class<?> clazz, Object value, @NonNull String... names) {
    	setField(getFirstField(clazz, names), value);
    }
    
    public static void setFirstField(Object instance, @NonNull Set<Class<?>> classes, Object value, @NonNull String... names) {
    	for (Class<?> clazz : classes) {
    		try {
    			setField(instance, getFirstField(clazz, names), value);
    			return;
    		} catch (RuntimeException ignored) {}
    	}
        throw new RuntimeException("None of the fields exist in classes: " + classes + " - " + String.join(", ", names));
    }
    
    public static Method getMethod(@NonNull Class<?> clazz, @NonNull String name, Class<?>... params) {
    	return methods.computeIfAbsent(new MethodKey(clazz, name, params), k -> {
        	Class<?> current = clazz;
        	
        	while (current != null) {
                try {
                    Method method = current.getMethod(name, params);
                    method.setAccessible(true);
                    return Optional.of(method);
                } catch (NoSuchMethodException ignored) {}
                
                try {
                    Method method = current.getDeclaredMethod(name, params);
                    method.setAccessible(true);
                    return Optional.of(method);
                } catch (NoSuchMethodException ignored) {}
                
            	current = current.getSuperclass();
        	}
        	return Optional.empty();
    	}).orElse(null);
    }
    
    public static Method getMethod(@NonNull Object instance, @NonNull String name, Class<?>... params) {
    	return getMethod(instance.getClass(), name, params);
    }
    
    public static Method getFirstMethod(@NonNull Class<?> clazz, List<Class<?>> params, @NonNull String... names) {
    	return firstMethods.computeIfAbsent(new MethodKey(clazz, params, names), k -> {
            for (String name : names) {
                Method found = getMethod(clazz, name, params.toArray(new Class[0]));
                if (found != null) {
                    return Optional.of(found);
                }
            }
            return Optional.empty();
    	}).orElseThrow(() ->
    		new RuntimeException("None of the methods exist: " + String.join(", ", names))
    	);
    }
    
    public static Method getFirstMethod(@NonNull Object instance, List<Class<?>> params, @NonNull String... names) {
    	return getFirstMethod(instance.getClass(), params, names);
    }
    
    public static Method getFirstMethod(@NonNull Set<Class<?>> classes, List<Class<?>> params, @NonNull String... names) {
    	return firstMethodsClasses.computeIfAbsent(new MethodKey(classes, params, names), k -> {
        	for (Class<?> clazz : classes) {
        		try {
        			return Optional.of(getFirstMethod(clazz, params, names));
        		} catch (RuntimeException ignored) {}
        	}
        	return Optional.empty();
    	}).orElseThrow(() ->
			new RuntimeException("None of the methods exist in classes: " + classes + " - " + String.join(", ", names))
		);
    }
    
    public static Constructor<?> getConstructor(@NonNull Class<?> clazz, Class<?>... params) {
    	return constructors.computeIfAbsent(new ConstructorKey(clazz, params), k -> {
            try {
                Constructor<?> ctor = clazz.getDeclaredConstructor(params);
                ctor.setAccessible(true);
                return Optional.of(ctor);
            } catch (NoSuchMethodException e) {
            	return Optional.empty();
            }
    	}).orElseThrow(() ->
    		new RuntimeException()
    	);
    }
    
    public static Constructor<?> getConstructor(@NonNull Object instance, Class<?>... params) {
    	return getConstructor(instance.getClass(), params);
    }
    
    public static Constructor<?> getFirstConstructor(@NonNull Class<?> clazz, Set<List<Class<?>>> params) {
    	return firstConstructors.computeIfAbsent(new ConstructorKey(clazz, params), k -> {
            for (List<Class<?>> list : params) {
            	Constructor<?> found = getConstructor(clazz, list.toArray(new Class[0]));
                if (found != null) {
                    return Optional.of(found);
                }
            }
            return Optional.empty();
    	}).orElseThrow(() ->
			new RuntimeException("No matching constructor found for class: " + clazz.getName())
		);
    }
    
    public static Constructor<?> getFirstConstructor(@NonNull Object instance, Set<List<Class<?>>> params) {
    	return getFirstConstructor(instance.getClass(), params);
    }
    
    public static Constructor<?> getFirstConstructor(@NonNull Set<Class<?>> classes, Set<List<Class<?>>> params) {
    	return firstConstructorsClasses.computeIfAbsent(new ConstructorKey(classes, params), k -> {
        	for (Class<?> clazz : classes) {
        		try {
        			return Optional.of(getFirstConstructor(clazz, params));
        		} catch (RuntimeException ignored) {}
        	}
        	return Optional.empty();
    	}).orElseThrow(() ->
			new RuntimeException("No matching constructor found for classes: " + classes)
		);
    }
}

package com.falchus.lib.utils.wrapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;
import com.falchus.lib.utils.reflection.ReflectionUtils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Wrapper<T> {

	@NonNull protected T handle;
	
	protected Field getField(@NonNull String name) {
		return ReflectionUtils.getField(handle, name);
	}
	
	protected Field getFirstField(@NonNull String... names) {
		return ReflectionUtils.getFirstField(handle, names);
	}
	
	protected <U> U getFieldValue(@NonNull Field field) {
		return ReflectionUtils.getFieldValue(handle, field);
	}
	
	protected void setField(@NonNull Field field, Object value) {
		ReflectionUtils.setField(handle, field, value);
	}
	
	protected void setFirstField(Object value, @NonNull String... names) {
		ReflectionUtils.setFirstField(handle, value, names);
	}
	
	protected Method getMethod(@NonNull String name, Class<?>... params) {
		return ReflectionUtils.getMethod(handle, name, params);
	}
	
	protected Method getFirstMethod(List<Class<?>> params, @NonNull String... names) {
		return ReflectionUtils.getFirstMethod(handle, params, names);
	}
	
	protected Constructor<?> getConstructor(Class<?>... params) {
		return ReflectionUtils.getConstructor(handle, params);
    }
	
	protected Constructor<?> getFirstConstructor(Set<List<Class<?>>> params) {
		return ReflectionUtils.getFirstConstructor(handle, params);
	}
	
	@SuppressWarnings("unchecked")
	public <U extends Wrapper<?>> U as(@NonNull Class<U> clazz, Class<?>... params) {
		if (clazz.isInstance(this)) {
			return (U) this;
		}
		
		try {
			Map<Class<?>, Object>[] paramMaps = new Map[params.length];
			for (int i = 0; i < params.length; i++) {
				paramMaps[i] = Map.of(params[i], handle);
			}
			
			return (U) new ClassInstanceBuilder(
				clazz
			).withParams(
				paramMaps
			).build();
		} catch (Exception e) {
			return null;
		}
	}
}

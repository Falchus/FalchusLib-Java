package com.falchus.lib.utils.wrapper.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import com.falchus.lib.utils.reflection.Dummy;
import com.falchus.lib.utils.reflection.ReflectionUtils;
import com.falchus.lib.utils.wrapper.Wrapper;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class ClassWrapper<T> extends Wrapper<T> {

	protected final Set<Class<?>> classes;
	
	public ClassWrapper(@NonNull T handle, @NonNull Set<Class<?>> classes) {
		super(handle);
		this.classes = classes;
		
		if (handle instanceof Dummy) return;
		if (!classes.stream().anyMatch(clazz -> clazz.isAssignableFrom(handle.getClass()))) {
			throw new RuntimeException("Handle " + handle.getClass().getName() + " is not assignable to any of: " + classes.stream().map(Class::getName).toList());
		}
	}
	
	@Override
	protected Field getFirstField(@NonNull String... names) {
		return ReflectionUtils.getFirstField(classes, names);
	}
	
	@Override
	protected void setFirstField(Object value, @NonNull String... names) {
		ReflectionUtils.setFirstField(handle, classes, value, names);
	}
	
	@Override
	protected Method getFirstMethod(List<Class<?>> params, @NonNull String... names) {
		return ReflectionUtils.getFirstMethod(classes, params, names);
	}
	
	@Override
	protected Constructor<?> getFirstConstructor(Set<List<Class<?>>> params) {
		return ReflectionUtils.getFirstConstructor(classes, params);
	}
}

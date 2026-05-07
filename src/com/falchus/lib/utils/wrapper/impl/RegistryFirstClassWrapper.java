package com.falchus.lib.utils.wrapper.impl;

import java.util.Set;

import com.falchus.lib.utils.wrapper.WrapperRegistry;

import lombok.NonNull;

public class RegistryFirstClassWrapper<T> extends FirstClassWrapper<T> {
	
	private boolean registered;
	
	public RegistryFirstClassWrapper(@NonNull T handle, @NonNull Set<String> names) {
		super(handle, names);
		register();
	}
	
	@SuppressWarnings("unchecked")
	protected Class<? extends FirstClassWrapper<?>>[] getWrappers() {
		return new Class[] {};
	}
	
	private void register() {
		if (registered) return;
		registered = true;
		
		for (Class<? extends FirstClassWrapper<?>> wrapper : getWrappers()) {
			WrapperRegistry.register(wrapper);
		}
	}
	
	public static <U extends FirstClassWrapper<?>> U wrap(Object handle) {
		return WrapperRegistry.wrap(handle);
	}
}

package com.falchus.lib.utils.wrapper.impl;

import java.util.Set;

import com.falchus.lib.utils.wrapper.IWrapper;

public interface IClassWrapper<T> extends IWrapper<T> {

	Set<Class<?>> getClasses();
}

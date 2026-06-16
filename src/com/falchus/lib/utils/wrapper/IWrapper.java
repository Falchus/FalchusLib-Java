package com.falchus.lib.utils.wrapper;

import lombok.NonNull;

public interface IWrapper<T> {
	
	@NonNull T getHandle();
}

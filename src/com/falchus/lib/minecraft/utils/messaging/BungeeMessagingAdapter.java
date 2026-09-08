package com.falchus.lib.minecraft.utils.messaging;

import lombok.NonNull;

public interface BungeeMessagingAdapter {
	
	void execute(@NonNull String command);
	
	void connect(@NonNull String name, @NonNull String server);
	
	boolean isAvailable();
}

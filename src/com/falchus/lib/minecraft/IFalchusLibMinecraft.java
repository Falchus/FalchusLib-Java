package com.falchus.lib.minecraft;

import java.util.UUID;

import lombok.NonNull;

public interface IFalchusLibMinecraft {
	
	void sendMessage(@NonNull UUID uuid, @NonNull String channel, byte[] data);
}

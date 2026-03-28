package com.falchus.lib.minecraft.spigot.packets.wrapper.chat;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;

abstract class PacketChatWrapper extends PacketWrapper {
	
	final Field message;

	PacketChatWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		message = getFirstField(
			"unsignedContent",
			"content",
			"message",
			"a"
		);
	}
	
	public abstract void setMessage(String message);
	
	/**
	 * In:	{@link String}
	 * Out:	IChatBaseComponent
	 */
	public Object getMessage() {
		try {
			return message.get(handle);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

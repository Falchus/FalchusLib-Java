package com.falchus.lib.minecraft.spigot.packets.wrapper.chat;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketChatWrapper extends PacketWrapper implements PacketChat {
	
	Field message;

	PacketChatWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		message = getFirstField(
			"unsignedContent",
			"content",
			"message",
			"a"
		);
	}

	@Override
	public Object getMessage() {
		return getFieldValue(message);
	}
	
	public abstract void setMessage(String message);
}

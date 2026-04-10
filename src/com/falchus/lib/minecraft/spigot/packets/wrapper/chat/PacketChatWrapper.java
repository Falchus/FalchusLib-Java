package com.falchus.lib.minecraft.spigot.packets.wrapper.chat;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketChatWrapper extends PacketWrapper {

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

    /**
     * In:	{@link String}
     * Out:	IChatBaseComponent
     */
    public Object getMessage() {
        return getFieldValue(message);
    }

    public abstract void setMessage(String message);
}

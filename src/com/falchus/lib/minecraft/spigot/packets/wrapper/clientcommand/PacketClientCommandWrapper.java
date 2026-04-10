package com.falchus.lib.minecraft.spigot.packets.wrapper.clientcommand;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
abstract class PacketClientCommandWrapper extends PacketWrapper {

    Field action;

    PacketClientCommandWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        action = getFirstField(
                "action",
                "a"
        );
    }

    /**
     * @return PacketPlayInClientCommand$EnumClientCommand
     */
    public Object getAction() {
        return getFieldValue(action);
    }

    /**
     * @param action: PacketPlayInClientCommand$EnumClientCommand
     */
    public void setAction(Object action) {
        setField(this.action, action);
    }
}

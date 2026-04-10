package com.falchus.lib.minecraft.spigot.packets.wrapper.window.data;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketWindowDataWrapper extends PacketWrapper {

    Field containerId;
    Field id;
    Field value;

    PacketWindowDataWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        containerId = getFirstField(
                "containerId",
                "a"
        );
        id = getFirstField(
                "id",
                "b"
        );
        value = getFirstField(
                "value",
                "c"
        );
    }

    public int getContainerId() {
        return getFieldValue(containerId);
    }

    public void setContainerId(int containerId) {
        setField(this.containerId, containerId);
    }

    public int getId() {
        return getFieldValue(id);
    }

    public void setId(int id) {
        setField(this.id, id);
    }

    public int getValue() {
        return getFieldValue(value);
    }

    public void setValue(int value) {
        setField(this.value, value);
    }
}

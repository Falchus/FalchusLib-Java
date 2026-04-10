package com.falchus.lib.minecraft.spigot.packets.wrapper.update.window;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketUpdateWindow extends PacketWrapper {

    Field containerId;

    PacketUpdateWindow(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        containerId = getFirstField(
                "containerId",
                "a"
        );
    }

    public int getContainerId() {
        return getFieldValue(containerId);
    }

    public void setContainerId(int containerId) {
        setField(this.containerId, containerId);
    }
}

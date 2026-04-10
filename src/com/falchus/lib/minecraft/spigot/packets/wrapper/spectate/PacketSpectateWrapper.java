package com.falchus.lib.minecraft.spigot.packets.wrapper.spectate;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;
import java.util.UUID;

@FieldDefaults(makeFinal = true)
abstract class PacketSpectateWrapper extends PacketWrapper {

    Field uuid;

    PacketSpectateWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        uuid = getFirstField(
                "uuid",
                "a"
        );
    }

    public UUID getUUID() {
        return getFieldValue(uuid);
    }

    public void setUUID(int cameraId) {
        setField(this.uuid, cameraId);
    }
}

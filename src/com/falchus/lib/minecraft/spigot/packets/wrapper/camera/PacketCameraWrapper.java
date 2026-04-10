package com.falchus.lib.minecraft.spigot.packets.wrapper.camera;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketCameraWrapper extends PacketWrapper {

    Field cameraId;

    PacketCameraWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        cameraId = getFirstField(
                "cameraId",
                "a"
        );
    }

    public int getCameraId() {
        return getFieldValue(cameraId);
    }

    public void setCameraId(int cameraId) {
        setField(this.cameraId, cameraId);
    }
}

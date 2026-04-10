package com.falchus.lib.minecraft.spigot.packets.wrapper.attachentity;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketAttachEntityWrapper extends PacketWrapper {

    Field sourceId;
    Field destId;

    PacketAttachEntityWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        sourceId = getFirstField(
                "sourceId",
                "b"
        );
        destId = getFirstField(
                "destId",
                "c"
        );
    }

    public int getSourceId() {
        return getFieldValue(sourceId);
    }

    public void setSourceId(int sourceId) {
        setField(this.sourceId, sourceId);
    }

    public int getDestId() {
        return getFieldValue(destId);
    }

    public void setDestId(int destId) {
        setField(this.destId, destId);
    }
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.update.attributes;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
abstract class PacketUpdateAttributesWrapper extends PacketWrapper {

    Field entityId;

    PacketUpdateAttributesWrapper(@NonNull Object handle, @NonNull Set<String> names) {
        super(handle, names);

        entityId = getFirstField(
                "entityId",
                "a"
        );
    }

    public void setPos(int entityId) {
        setField(this.entityId, entityId);
    }

    public int getEntityId() {
        return getFieldValue(entityId);
    }
}

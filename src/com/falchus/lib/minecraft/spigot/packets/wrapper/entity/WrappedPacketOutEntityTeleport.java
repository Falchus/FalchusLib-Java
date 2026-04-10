package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
public class WrappedPacketOutEntityTeleport extends PacketEntityWrapper {

    Field id;
    Field onGround;

    WrappedPacketOutEntityTeleport(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutEntityTeleport",
                        networkProtocolGame + "PacketPlayOutEntityTeleport"
                )
        );

        id = getFirstField(
                "id",
                "a"
        );
        onGround = getFirstField(
                "onGround",
                "g"
        );
    }

    public int getId() {
        return getFieldValue(id);
    }

    public void setId(int id) {
        setField(this.id, id);
    }

    public boolean isOnGround() {
        return getFieldValue(onGround);
    }

    public void setOnGround(boolean onGround) {
        setField(this.onGround, onGround);
    }
}

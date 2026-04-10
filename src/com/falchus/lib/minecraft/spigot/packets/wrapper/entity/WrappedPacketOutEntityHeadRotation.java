package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
public class WrappedPacketOutEntityHeadRotation extends PacketEntityWrapper {

    Field yHeadRot;

    WrappedPacketOutEntityHeadRotation(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutEntityHeadRotation",
                        networkProtocolGame + "PacketPlayOutEntityHeadRotation"
                )
        );

        yHeadRot = getFirstField(
                "yHeadRot",
                "b"
        );
    }

    public byte getYHeadRot() {
        return getFieldValue(yHeadRot);
    }

    public void setYHeadRot(byte yHeadRot) {
        setField(this.yHeadRot, yHeadRot);
    }
}

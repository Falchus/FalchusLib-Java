package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.lang.reflect.Field;
import java.util.Set;

@FieldDefaults(makeFinal = true)
public class WrappedPacketOutEntity extends PacketEntityWrapper {

    Field yRot;
    Field xRot;
    Field onGround;
    Field hasRot;

    WrappedPacketOutEntity(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutEntity",
                        networkProtocolGame + "PacketPlayOutEntity"
                )
        );

        yRot = getFirstField(
                "yRot",
                "yHeadRot",
                "e",
                "b"
        );
        xRot = getFirstField(
                "xRot",
                "f"
        );
        onGround = getFirstField(
                "onGround",
                "g"
        );
        hasRot = getFirstField(
                "hasRot",
                "h"
        );
    }

    public byte getYRot() {
        return getFieldValue(yRot);
    }

    public void setYRot(byte yRot) {
        setField(this.yRot, yRot);
    }

    public byte getXRot() {
        return getFieldValue(xRot);
    }

    public void setXRot(byte xRot) {
        setField(this.xRot, xRot);
    }

    public boolean isOnGround() {
        return getFieldValue(onGround);
    }

    public void setOnGround(boolean onGround) {
        setField(this.onGround, onGround);
    }

    public boolean isHasRot() {
        return getFieldValue(hasRot);
    }

    public void setHasRot(boolean hasRot) {
        setField(this.hasRot, hasRot);
    }
}

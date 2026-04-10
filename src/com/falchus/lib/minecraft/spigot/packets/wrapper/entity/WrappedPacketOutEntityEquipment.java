package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutEntityEquipment extends PacketEntityWrapper {

    WrappedPacketOutEntityEquipment(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutEntityEquipment",
                        networkProtocolGame + "PacketPlayOutEntityEquipment"
                )
        );
    }
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutEntityMetadata extends PacketEntityWrapper {

    WrappedPacketOutEntityMetadata(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutEntityMetadata",
                        networkProtocolGame + "PacketPlayOutEntityMetadata"
                )
        );
    }
}

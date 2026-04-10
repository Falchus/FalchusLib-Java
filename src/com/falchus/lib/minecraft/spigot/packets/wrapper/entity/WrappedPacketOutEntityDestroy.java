package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutEntityDestroy extends PacketEntityWrapper {

    WrappedPacketOutEntityDestroy(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutEntityDestroy",
                        networkProtocolGame + "PacketPlayOutEntityDestroy"
                )
        );
    }
}

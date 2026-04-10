package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutEntityVelocity extends PacketEntityWrapper {

    WrappedPacketOutEntityVelocity(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutEntityVelocity",
                        networkProtocolGame + "PacketPlayOutEntityVelocity"
                )
        );
    }
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.world.event;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutWorldEvent extends PacketWorldEventWrapper {

    public WrappedPacketOutWorldEvent(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutWorldEvent",
                        networkProtocolGame + "PacketPlayOutWorldEvent"
                )
        );
    }
}

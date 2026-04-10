package com.falchus.lib.minecraft.spigot.packets.wrapper.map;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutMap extends PacketMapWrapper {

    public WrappedPacketOutMap(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutMap",
                        networkProtocolGame + "PacketPlayOutMap"
                )
        );
    }
}

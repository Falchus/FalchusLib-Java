package com.falchus.lib.minecraft.spigot.packets.wrapper.update.window;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutWindowItems extends PacketUpdateWindowItems {

    public WrappedPacketOutWindowItems(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutWindowItems",
                        networkProtocolGame + "PacketPlayOutWindowItems"
                )
        );
    }
}

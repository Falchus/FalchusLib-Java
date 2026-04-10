package com.falchus.lib.minecraft.spigot.packets.wrapper.window.items;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutWindowItems extends PacketWindowItemsWrapper {

    public WrappedPacketOutWindowItems(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutWindowItems",
                        networkProtocolGame + "PacketPlayOutWindowItems"
                )
        );
    }
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.open.window;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutOpenWindow extends PacketOpenWindowWrapper {

    public WrappedPacketOutOpenWindow(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutOpenWindow",
                        networkProtocolGame + "PacketPlayOutOpenWindow"
                )
        );
    }
}

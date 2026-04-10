package com.falchus.lib.minecraft.spigot.packets.wrapper.window.data;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutWindowData extends PacketWindowDataWrapper {

    public WrappedPacketOutWindowData(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutWindowData",
                        networkProtocolGame + "PacketPlayOutWindowData"
                )
        );
    }
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.update.window;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutWindowData extends PacketUpdateWindowData {

    public WrappedPacketOutWindowData(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutWindowData",
                        networkProtocolGame + "PacketPlayOutWindowData"
                )
        );
    }
}

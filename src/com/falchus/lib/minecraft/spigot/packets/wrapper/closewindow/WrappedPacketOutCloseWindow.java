package com.falchus.lib.minecraft.spigot.packets.wrapper.closewindow;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutCloseWindow extends PacketCloseWindowWrapper {

    public WrappedPacketOutCloseWindow(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutCloseWindow",
                        networkProtocolGame + "PacketPlayOutCloseWindow"
                )
        );
    }
}

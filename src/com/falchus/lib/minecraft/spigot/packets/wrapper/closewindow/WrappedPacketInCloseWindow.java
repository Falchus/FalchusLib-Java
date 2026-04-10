package com.falchus.lib.minecraft.spigot.packets.wrapper.closewindow;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketInCloseWindow extends PacketCloseWindowWrapper {

    public WrappedPacketInCloseWindow(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInCloseWindow",
                        networkProtocolGame + "PacketPlayInCloseWindow"
                )
        );
    }
}

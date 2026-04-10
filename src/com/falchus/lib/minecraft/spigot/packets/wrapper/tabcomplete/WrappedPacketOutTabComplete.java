package com.falchus.lib.minecraft.spigot.packets.wrapper.tabcomplete;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutTabComplete extends PacketTabCompleteWrapper {

    public WrappedPacketOutTabComplete(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutTabComplete",
                        networkProtocolGame + "PacketPlayOutTabComplete"
                )
        );
    }
}

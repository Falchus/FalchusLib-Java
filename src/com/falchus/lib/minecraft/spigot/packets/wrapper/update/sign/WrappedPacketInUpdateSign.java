package com.falchus.lib.minecraft.spigot.packets.wrapper.update.sign;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketInUpdateSign extends PacketUpdateSignWrapper {

    public WrappedPacketInUpdateSign(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInUpdateSign",
                        networkProtocolGame + "PacketPlayInUpdateSign"
                )
        );
    }
}

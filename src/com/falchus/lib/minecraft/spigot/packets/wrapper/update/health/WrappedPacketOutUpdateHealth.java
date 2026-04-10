package com.falchus.lib.minecraft.spigot.packets.wrapper.update.health;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutUpdateHealth extends PacketUpdateHealthWrapper {

    public WrappedPacketOutUpdateHealth(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutUpdateHealth",
                        networkProtocolGame + "PacketPlayOutUpdateHealth"
                )
        );
    }
}

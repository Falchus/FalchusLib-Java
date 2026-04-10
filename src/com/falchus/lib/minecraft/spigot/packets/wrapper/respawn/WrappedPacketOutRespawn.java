package com.falchus.lib.minecraft.spigot.packets.wrapper.respawn;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutRespawn extends PacketRespawnWrapper {

    WrappedPacketOutRespawn(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutRespawn",
                        networkProtocolGame + "PacketPlayOutRespawn"
                )
        );
    }
}

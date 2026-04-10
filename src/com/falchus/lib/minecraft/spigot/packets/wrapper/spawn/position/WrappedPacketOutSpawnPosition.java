package com.falchus.lib.minecraft.spigot.packets.wrapper.spawn.position;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutSpawnPosition extends PacketSpawnPositionWrapper {

    public WrappedPacketOutSpawnPosition(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutSpawnPosition",
                        networkProtocolGame + "PacketPlayOutSpawnPosition"
                )
        );
    }
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.spawn.entity;

import lombok.NonNull;

import java.util.Set;

// TODO: add all entity spawn packets
public class WrappedPacketOutSpawnEntity extends PacketSpawnEntityWrapper {

    public WrappedPacketOutSpawnEntity(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutSpawnEntity",
                        networkProtocolGame + "PacketPlayOutSpawnEntity"
                )
        );
    }
}

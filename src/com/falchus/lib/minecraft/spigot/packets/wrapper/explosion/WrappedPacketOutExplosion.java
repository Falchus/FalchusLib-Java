package com.falchus.lib.minecraft.spigot.packets.wrapper.explosion;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutExplosion extends PacketExplosionWrapper {

    public WrappedPacketOutExplosion(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutExplosion",
                        networkProtocolGame + "PacketPlayOutExplosion"
                )
        );
    }
}

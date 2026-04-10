package com.falchus.lib.minecraft.spigot.packets.wrapper.flying;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketInFlying extends PacketFlyingWrapper {

    WrappedPacketInFlying(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInFlying",
                        networkProtocolGame + "PacketPlayInFlying"
                )
        );
    }
}

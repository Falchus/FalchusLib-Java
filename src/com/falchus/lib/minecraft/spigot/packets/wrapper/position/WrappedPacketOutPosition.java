package com.falchus.lib.minecraft.spigot.packets.wrapper.position;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutPosition extends PacketPositionWrapper {

    WrappedPacketOutPosition(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutPosition",
                        networkProtocolCommon + "PacketPlayOutPosition"
                )
        );
    }
}

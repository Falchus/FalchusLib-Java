package com.falchus.lib.minecraft.spigot.packets.wrapper.gamestatechange;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutGameStateChange extends PacketGameStateChangeWrapper {

    WrappedPacketOutGameStateChange(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutGameStateChange",
                        networkProtocolGame + "PacketPlayOutGameStateChange"
                )
        );
    }
}

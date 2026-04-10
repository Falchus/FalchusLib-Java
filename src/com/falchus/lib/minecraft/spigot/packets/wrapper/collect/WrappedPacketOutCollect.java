package com.falchus.lib.minecraft.spigot.packets.wrapper.collect;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutCollect extends PacketCollectWrapper {

    public WrappedPacketOutCollect(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutCollect",
                        networkProtocolGame + "PacketPlayOutCollect"
                )
        );
    }
}

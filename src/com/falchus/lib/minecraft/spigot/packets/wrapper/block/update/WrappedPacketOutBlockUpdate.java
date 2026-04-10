package com.falchus.lib.minecraft.spigot.packets.wrapper.block.update;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutBlockUpdate extends PacketBlockUpdateWrapper {

    public WrappedPacketOutBlockUpdate(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutBlockChange",
                        networkProtocolGame + "PacketPlayOutBlockChange"
                )
        );
    }
}

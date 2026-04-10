package com.falchus.lib.minecraft.spigot.packets.wrapper.multiblockchange;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutMultiBlockChange extends PacketMultiBlockChangeWrapper {

    public WrappedPacketOutMultiBlockChange(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutMultiBlockChange",
                        networkProtocolGame + "PacketPlayOutMultiBlockChange"
                )
        );
    }
}

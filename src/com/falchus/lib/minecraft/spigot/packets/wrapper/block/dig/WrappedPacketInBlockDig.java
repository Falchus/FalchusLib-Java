package com.falchus.lib.minecraft.spigot.packets.wrapper.block.dig;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketInBlockDig extends PacketBlockDigWrapper {

    public WrappedPacketInBlockDig(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInBlockDig",
                        networkProtocolGame + "PacketPlayInBlockDig"
                )
        );
    }
}

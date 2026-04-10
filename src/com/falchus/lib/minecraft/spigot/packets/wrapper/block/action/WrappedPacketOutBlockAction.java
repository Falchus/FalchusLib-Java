package com.falchus.lib.minecraft.spigot.packets.wrapper.block.action;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutBlockAction extends PacketBlockActionWrapper {

    public WrappedPacketOutBlockAction(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutBlockAction",
                        networkProtocolGame + "PacketPlayOutBlockAction"
                )
        );
    }
}

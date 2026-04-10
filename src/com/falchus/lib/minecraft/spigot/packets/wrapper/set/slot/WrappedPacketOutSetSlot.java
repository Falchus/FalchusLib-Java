package com.falchus.lib.minecraft.spigot.packets.wrapper.set.slot;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutSetSlot extends PacketSetSlotWrapper {

    public WrappedPacketOutSetSlot(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutSetSlot",
                        networkProtocolGame + "PacketPlayOutSetSlot"
                )
        );
    }
}

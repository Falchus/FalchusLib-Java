package com.falchus.lib.minecraft.spigot.packets.wrapper.helditemslot;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutHeldItemSlot extends PacketHeldItemSlotWrapper {

    public WrappedPacketOutHeldItemSlot(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutHeldItemSlot",
                        networkProtocolGame + "PacketPlayOutHeldItemSlot"
                )
        );
    }
}

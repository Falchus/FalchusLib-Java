package com.falchus.lib.minecraft.spigot.packets.wrapper.helditemslot;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketInHeldItemSlot extends PacketHeldItemSlotWrapper {

    public WrappedPacketInHeldItemSlot(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInHeldItemSlot",
                        networkProtocolGame + "PacketPlayInHeldItemSlot"
                )
        );
    }
}

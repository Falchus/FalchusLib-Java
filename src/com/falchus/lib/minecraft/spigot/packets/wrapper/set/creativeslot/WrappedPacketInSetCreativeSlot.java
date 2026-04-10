package com.falchus.lib.minecraft.spigot.packets.wrapper.set.creativeslot;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketInSetCreativeSlot extends PacketSetCreativeSlotWrapper {

    public WrappedPacketInSetCreativeSlot(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInSetCreativeSlot",
                        networkProtocolGame + "PacketPlayInSetCreativeSlot"
                )
        );
    }
}

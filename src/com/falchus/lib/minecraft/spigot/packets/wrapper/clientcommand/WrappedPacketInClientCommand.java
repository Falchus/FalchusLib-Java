package com.falchus.lib.minecraft.spigot.packets.wrapper.clientcommand;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInClientCommand extends PacketClientCommandWrapper {

    public WrappedPacketInClientCommand(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInClientCommand",
                        networkProtocolGame + "PacketPlayInClientCommand"
                )
        );
    }
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.use;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketInUseEntity extends PacketUseEntityWrapper {

    public WrappedPacketInUseEntity(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInUseEntity",
                        networkProtocolGame + "PacketPlayInUseEntity"
                )
        );
    }
}

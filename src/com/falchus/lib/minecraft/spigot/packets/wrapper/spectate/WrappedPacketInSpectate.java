package com.falchus.lib.minecraft.spigot.packets.wrapper.spectate;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketInSpectate extends PacketSpectateWrapper {

    public WrappedPacketInSpectate(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInSpectate",
                        networkProtocolGame + "PacketPlayInSpectate"
                )
        );
    }
}

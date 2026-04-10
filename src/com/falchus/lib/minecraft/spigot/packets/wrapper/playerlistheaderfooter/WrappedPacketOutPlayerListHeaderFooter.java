package com.falchus.lib.minecraft.spigot.packets.wrapper.playerlistheaderfooter;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutPlayerListHeaderFooter extends PacketPlayerListHeaderFooterWrapper {

    public WrappedPacketOutPlayerListHeaderFooter(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutPlayerListHeaderFooter",
                        networkProtocolGame + "PacketPlayOutPlayerListHeaderFooter"
                )
        );
    }
}

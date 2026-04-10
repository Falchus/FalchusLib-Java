package com.falchus.lib.minecraft.spigot.packets.wrapper.resourcepackstatus;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketInResourcePackStatus extends PacketResourcePackStatusWrapper {

    WrappedPacketInResourcePackStatus(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInResourcePackStatus",
                        networkProtocolCommon + "ServerboundResourcePackPacket"
                )
        );
    }
}

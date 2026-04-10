package com.falchus.lib.minecraft.spigot.packets.wrapper.kickdisconnect;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutKickDisconnect extends PacketKickDisconnectWrapper {

    WrappedPacketOutKickDisconnect(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutKickDisconnect",
                        networkProtocolCommon + "ClientboundDisconnectPacket"
                )
        );
    }
}

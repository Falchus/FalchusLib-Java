package com.falchus.lib.minecraft.spigot.packets.wrapper.attachentity;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutAttachEntity extends PacketAttachEntityWrapper {

    public WrappedPacketOutAttachEntity(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutAttachEntity",
                        networkProtocolGame + "PacketPlayOutAttachEntity"
                )
        );
    }
}

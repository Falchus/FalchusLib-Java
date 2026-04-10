package com.falchus.lib.minecraft.spigot.packets.wrapper.update.attributes;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutUpdateAttributes extends PacketUpdateAttributesWrapper {

    public WrappedPacketOutUpdateAttributes(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutUpdateAttributes",
                        networkProtocolGame + "PacketPlayOutUpdateAttributes"
                )
        );
    }
}

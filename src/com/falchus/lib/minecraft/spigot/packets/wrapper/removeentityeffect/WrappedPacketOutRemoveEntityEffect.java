package com.falchus.lib.minecraft.spigot.packets.wrapper.removeentityeffect;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutRemoveEntityEffect extends PacketRemoveEntityEffectWrapper {

    WrappedPacketOutRemoveEntityEffect(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutRemoveEntityEffect",
                        networkProtocolCommon + "PacketPlayOutRemoveEntityEffect"
                )
        );
    }
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.animation;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutAnimation extends PacketAnimationWrapper {

    public WrappedPacketOutAnimation(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutAnimation",
                        networkProtocolGame + "PacketPlayOutAnimation"
                )
        );
    }
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.block.breakanimation;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutBlockBreakAnimation extends PacketBlockBreakAnimationWrapper {

    public WrappedPacketOutBlockBreakAnimation(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutBlockBreakAnimation",
                        networkProtocolGame + "PacketPlayOutBlockBreakAnimation"
                )
        );
    }
}

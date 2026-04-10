package com.falchus.lib.minecraft.spigot.packets.wrapper.armanimation;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketInArmAnimation extends PacketArmAnimationWrapper {

    public WrappedPacketInArmAnimation(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInArmAnimation",
                        networkProtocolGame + "PacketPlayInArmAnimation"
                )
        );
    }
}

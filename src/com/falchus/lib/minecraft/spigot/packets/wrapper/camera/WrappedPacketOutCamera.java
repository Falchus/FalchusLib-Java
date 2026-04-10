package com.falchus.lib.minecraft.spigot.packets.wrapper.camera;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutCamera extends PacketCameraWrapper {

    public WrappedPacketOutCamera(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutCamera",
                        networkProtocolGame + "PacketPlayOutCamera"
                )
        );
    }
}

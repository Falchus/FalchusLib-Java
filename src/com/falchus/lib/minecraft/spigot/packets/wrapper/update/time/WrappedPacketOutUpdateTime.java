package com.falchus.lib.minecraft.spigot.packets.wrapper.update.time;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutUpdateTime extends PacketUpdateTimeWrapper {

    public WrappedPacketOutUpdateTime(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutUpdateTime",
                        networkProtocolGame + "PacketPlayOutUpdateTime"
                )
        );
    }
}

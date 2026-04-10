package com.falchus.lib.minecraft.spigot.packets.wrapper.statistic;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutStatistic extends PacketStatisticWrapper {

    public WrappedPacketOutStatistic(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutStatistic",
                        networkProtocolGame + "PacketPlayOutStatistic"
                )
        );
    }
}

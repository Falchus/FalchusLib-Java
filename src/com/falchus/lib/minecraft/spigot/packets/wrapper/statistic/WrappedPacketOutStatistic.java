package com.falchus.lib.minecraft.spigot.packets.wrapper.statistic;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutStatistic extends PacketStatisticWrapper {

	public WrappedPacketOutStatistic(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutStatistic",
				networkProtocolGame + "PacketPlayOutStatistic"
			)
		);
	}
}

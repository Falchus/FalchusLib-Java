package com.falchus.lib.minecraft.spigot.packets.wrapper.update.time;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutUpdateTime extends PacketUpdateTimeWrapper {

	public WrappedPacketOutUpdateTime(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutUpdateTime",
				networkProtocolGame + "PacketPlayOutUpdateTime"
			)
		);
	}
}

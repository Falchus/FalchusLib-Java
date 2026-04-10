package com.falchus.lib.minecraft.spigot.packets.wrapper.update.health;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutUpdateHealth extends PacketUpdateHealthWrapper {

	public WrappedPacketOutUpdateHealth(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutUpdateHealth",
				networkProtocolGame + "PacketPlayOutUpdateHealth"
			)
		);
	}
}

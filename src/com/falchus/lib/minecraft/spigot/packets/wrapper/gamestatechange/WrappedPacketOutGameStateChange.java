package com.falchus.lib.minecraft.spigot.packets.wrapper.gamestatechange;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutGameStateChange extends PacketGameStateChangeWrapper {

	private WrappedPacketOutGameStateChange(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutGameStateChange",
				networkProtocolGame + "PacketPlayOutGameStateChange"
			)
		);
	}
}

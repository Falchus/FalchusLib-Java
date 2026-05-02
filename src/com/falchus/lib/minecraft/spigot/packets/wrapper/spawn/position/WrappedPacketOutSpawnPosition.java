package com.falchus.lib.minecraft.spigot.packets.wrapper.spawn.position;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutSpawnPosition extends PacketSpawnPositionWrapper {

	private WrappedPacketOutSpawnPosition(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutSpawnPosition",
				networkProtocolGame + "PacketPlayOutSpawnPosition"
			)
		);
	}
}

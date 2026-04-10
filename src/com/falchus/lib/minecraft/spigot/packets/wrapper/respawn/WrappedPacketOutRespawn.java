package com.falchus.lib.minecraft.spigot.packets.wrapper.respawn;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutRespawn extends PacketRespawnWrapper {

	WrappedPacketOutRespawn(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutRespawn",
				networkProtocolGame + "PacketPlayOutRespawn"
			)
		);
	}
}

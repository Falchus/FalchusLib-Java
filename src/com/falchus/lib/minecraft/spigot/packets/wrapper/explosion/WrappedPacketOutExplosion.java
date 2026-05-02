package com.falchus.lib.minecraft.spigot.packets.wrapper.explosion;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutExplosion extends PacketExplosionWrapper {

	private WrappedPacketOutExplosion(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutExplosion",
				networkProtocolGame + "PacketPlayOutExplosion"
			)
		);
	}
}

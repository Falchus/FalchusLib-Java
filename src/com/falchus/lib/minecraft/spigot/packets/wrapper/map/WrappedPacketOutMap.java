package com.falchus.lib.minecraft.spigot.packets.wrapper.map;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutMap extends PacketMapWrapper {

	private WrappedPacketOutMap(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutMap",
				networkProtocolGame + "PacketPlayOutMap"
			)
		);
	}
}

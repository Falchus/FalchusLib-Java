package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutEntityMetadata extends PacketEntityWrapper {
	
	WrappedPacketOutEntityMetadata(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutEntityMetadata",
				networkProtocolGame + "PacketPlayOutEntityMetadata"
			)
		);
	}
}

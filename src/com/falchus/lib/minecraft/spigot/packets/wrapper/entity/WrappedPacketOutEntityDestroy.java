package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutEntityDestroy extends PacketEntityWrapper {
	
	WrappedPacketOutEntityDestroy(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutEntityDestroy",
				networkProtocolGame + "PacketPlayOutEntityDestroy"
			)
		);
	}
}

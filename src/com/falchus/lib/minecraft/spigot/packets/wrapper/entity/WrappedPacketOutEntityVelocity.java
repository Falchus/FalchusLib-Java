package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutEntityVelocity extends PacketEntityWrapper {
	
	WrappedPacketOutEntityVelocity(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutEntityVelocity",
				networkProtocolGame + "PacketPlayOutEntityVelocity"
			)
		);
	}
}

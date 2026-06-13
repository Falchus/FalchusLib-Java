package com.falchus.lib.minecraft.spigot.packets.wrapper.world.event;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutWorldEvent extends PacketWorldEventWrapper {

	private WrappedPacketOutWorldEvent(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutWorldEvent",
			networkProtocolGame + "PacketPlayOutWorldEvent"
		));
	}
}

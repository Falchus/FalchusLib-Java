package com.falchus.lib.minecraft.spigot.packets.wrapper.update.window;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutWindowItems extends PacketUpdateWindowItems {

	public WrappedPacketOutWindowItems(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutWindowItems",
				networkProtocolGame + "PacketPlayOutWindowItems"
			)
		);
	}
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.window.items;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutWindowItems extends PacketWindowItemsWrapper {

	public WrappedPacketOutWindowItems(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutWindowItems",
				networkProtocolGame + "PacketPlayOutWindowItems"
			)
		);
	}
}

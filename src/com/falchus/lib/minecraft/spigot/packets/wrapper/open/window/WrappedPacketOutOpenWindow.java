package com.falchus.lib.minecraft.spigot.packets.wrapper.open.window;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutOpenWindow extends PacketOpenWindowWrapper {

	private WrappedPacketOutOpenWindow(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutOpenWindow",
				networkProtocolGame + "PacketPlayOutOpenWindow"
			)
		);
	}
}

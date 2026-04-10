package com.falchus.lib.minecraft.spigot.packets.wrapper.window.data;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutWindowData extends PacketWindowDataWrapper {

	public WrappedPacketOutWindowData(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutWindowData",
				networkProtocolGame + "PacketPlayOutWindowData"
			)
		);
	}
}

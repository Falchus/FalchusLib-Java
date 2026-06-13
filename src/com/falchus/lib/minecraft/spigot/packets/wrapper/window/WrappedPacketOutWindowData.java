package com.falchus.lib.minecraft.spigot.packets.wrapper.window;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutWindowData extends PacketUpdateWindowData {

	private WrappedPacketOutWindowData(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutWindowData",
			networkProtocolGame + "PacketPlayOutWindowData"
		));
	}
}

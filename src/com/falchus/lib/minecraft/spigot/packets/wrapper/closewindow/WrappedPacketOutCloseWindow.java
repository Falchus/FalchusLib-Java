package com.falchus.lib.minecraft.spigot.packets.wrapper.closewindow;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutCloseWindow extends PacketCloseWindowWrapper {

	private WrappedPacketOutCloseWindow(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutCloseWindow",
			networkProtocolGame + "PacketPlayOutCloseWindow"
		));
	}
}

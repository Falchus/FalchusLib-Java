package com.falchus.lib.minecraft.spigot.packets.wrapper.server.info;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutServerInfo extends PacketServerInfoWrapper {

	private WrappedPacketOutServerInfo(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketStatusOutServerInfo",
			networkProtocolStatus + "PacketPlayOutServerInfo"
		));
	}
}

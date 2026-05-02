package com.falchus.lib.minecraft.spigot.packets.wrapper.login;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutLogin extends PacketLoginWrapper {

	private WrappedPacketOutLogin(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutLogin",
				networkProtocolGame + "PacketPlayOutLogin"
			)
		);
	}
}

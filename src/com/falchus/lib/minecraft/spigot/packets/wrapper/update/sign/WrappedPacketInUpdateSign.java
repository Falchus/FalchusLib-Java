package com.falchus.lib.minecraft.spigot.packets.wrapper.update.sign;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInUpdateSign extends PacketUpdateSignWrapper {

	private WrappedPacketInUpdateSign(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayInUpdateSign",
			networkProtocolGame + "PacketPlayInUpdateSign"
		));
	}
}

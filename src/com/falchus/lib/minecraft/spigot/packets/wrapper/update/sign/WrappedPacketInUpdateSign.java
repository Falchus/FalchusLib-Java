package com.falchus.lib.minecraft.spigot.packets.wrapper.update.sign;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInUpdateSign extends PacketUpdateSignWrapper {

	public WrappedPacketInUpdateSign(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayInUpdateSign",
				networkProtocolGame + "PacketPlayInUpdateSign"
			)
		);
	}
}

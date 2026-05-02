package com.falchus.lib.minecraft.spigot.packets.wrapper.spectate;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInSpectate extends PacketSpectateWrapper {

	private WrappedPacketInSpectate(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayInSpectate",
				networkProtocolGame + "PacketPlayInSpectate"
			)
		);
	}
}

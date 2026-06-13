package com.falchus.lib.minecraft.spigot.packets.wrapper.playerlistheaderfooter;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutPlayerListHeaderFooter extends PacketPlayerListHeaderFooterWrapper {

	private WrappedPacketOutPlayerListHeaderFooter(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutPlayerListHeaderFooter",
			networkProtocolGame + "PacketPlayOutPlayerListHeaderFooter"
		));
	}
}

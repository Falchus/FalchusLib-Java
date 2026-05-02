package com.falchus.lib.minecraft.spigot.packets.wrapper.update.attributes;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutUpdateAttributes extends PacketUpdateAttributesWrapper {

	private WrappedPacketOutUpdateAttributes(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutUpdateAttributes",
				networkProtocolGame + "PacketPlayOutUpdateAttributes"
			)
		);
	}
}

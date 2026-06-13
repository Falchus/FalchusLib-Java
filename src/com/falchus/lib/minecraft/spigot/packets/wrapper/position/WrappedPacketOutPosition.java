package com.falchus.lib.minecraft.spigot.packets.wrapper.position;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutPosition extends PacketPositionWrapper {

	private WrappedPacketOutPosition(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutPosition",
			networkProtocolCommon + "PacketPlayOutPosition"
		));
	}
}

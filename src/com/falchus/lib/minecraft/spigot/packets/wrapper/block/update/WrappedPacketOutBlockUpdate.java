package com.falchus.lib.minecraft.spigot.packets.wrapper.block.update;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutBlockUpdate extends PacketBlockUpdateWrapper {

	private WrappedPacketOutBlockUpdate(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutBlockChange",
			networkProtocolGame + "PacketPlayOutBlockChange"
		));
	}
}

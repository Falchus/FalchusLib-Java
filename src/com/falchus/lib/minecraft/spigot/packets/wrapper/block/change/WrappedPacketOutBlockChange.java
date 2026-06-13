package com.falchus.lib.minecraft.spigot.packets.wrapper.block.change;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutBlockChange extends PacketBlockChangeWrapper {

	private WrappedPacketOutBlockChange(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutBlockChange",
			networkProtocolGame + "PacketPlayOutBlockChange"
		));
	}
}

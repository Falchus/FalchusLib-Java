package com.falchus.lib.minecraft.spigot.packets.wrapper.multiblockchange;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutMultiBlockChange extends PacketMultiBlockChangeWrapper {

	private WrappedPacketOutMultiBlockChange(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutMultiBlockChange",
			networkProtocolGame + "PacketPlayOutMultiBlockChange"
		));
	}
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.block.dig;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInBlockDig extends PacketBlockDigWrapper {

	private WrappedPacketInBlockDig(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayInBlockDig",
			networkProtocolGame + "PacketPlayInBlockDig"
		));
	}
}

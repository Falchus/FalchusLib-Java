package com.falchus.lib.minecraft.spigot.packets.wrapper.block.place;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInBlockPlace extends PacketBlockPlaceWrapper {

	public WrappedPacketInBlockPlace(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayInBlockPlace",
				networkProtocolGame + "PacketPlayInBlockPlace"
			)
		);
	}
}

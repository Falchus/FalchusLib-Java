package com.falchus.lib.minecraft.spigot.packets.wrapper.set.slot;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutSetSlot extends PacketSetSlotWrapper {

	private WrappedPacketOutSetSlot(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutSetSlot",
			networkProtocolGame + "PacketPlayOutSetSlot"
		));
	}
}

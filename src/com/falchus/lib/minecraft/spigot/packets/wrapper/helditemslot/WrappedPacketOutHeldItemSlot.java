package com.falchus.lib.minecraft.spigot.packets.wrapper.helditemslot;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutHeldItemSlot extends PacketHeldItemSlotWrapper {

	private WrappedPacketOutHeldItemSlot(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutHeldItemSlot",
				networkProtocolGame + "PacketPlayOutHeldItemSlot"
			)
		);
	}
}

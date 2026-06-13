package com.falchus.lib.minecraft.spigot.packets.wrapper.helditemslot;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInHeldItemSlot extends PacketHeldItemSlotWrapper {

	private WrappedPacketInHeldItemSlot(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayInHeldItemSlot",
			networkProtocolGame + "PacketPlayInHeldItemSlot"
		));
	}
}

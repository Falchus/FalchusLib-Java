package com.falchus.lib.minecraft.spigot.packets.wrapper.set.creativeslot;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInSetCreativeSlot extends PacketSetCreativeSlotWrapper {

	private WrappedPacketInSetCreativeSlot(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayInSetCreativeSlot",
				networkProtocolGame + "PacketPlayInSetCreativeSlot"
			)
		);
	}
}

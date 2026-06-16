package com.falchus.lib.minecraft.spigot.packets.wrapper.helditemslot;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutHeldItemSlot extends PacketHeldItemSlotWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutHeldItemSlot",
		networkProtocolGame + "PacketPlayOutHeldItemSlot"
	);

	private WrappedPacketOutHeldItemSlot(@NonNull Object handle) {
		super(handle, names);
	}
	
	public WrappedPacketOutHeldItemSlot(int slot) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				int.class,
				slot
			)
		).build());
	}
}

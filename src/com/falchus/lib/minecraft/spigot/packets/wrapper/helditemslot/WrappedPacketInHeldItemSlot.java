package com.falchus.lib.minecraft.spigot.packets.wrapper.helditemslot;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketInHeldItemSlot extends PacketHeldItemSlotWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayInHeldItemSlot",
		networkProtocolGame + "PacketPlayInHeldItemSlot"
	);

	private WrappedPacketInHeldItemSlot(@NonNull Object handle) {
		super(handle, names);
	}
	
	public WrappedPacketInHeldItemSlot(int selectedSlot) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				int.class,
				selectedSlot
			)
		).build());
	}
}

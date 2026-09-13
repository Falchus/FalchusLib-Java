package com.falchus.lib.minecraft.spigot.packets.wrapper.set.creativeslot;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.world.item.ItemStack;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketInSetCreativeSlot extends PacketSetCreativeSlotWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayInSetCreativeSlot",
		networkProtocolGame + "PacketPlayInSetCreativeSlot"
	);

	private WrappedPacketInSetCreativeSlot(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
	}
	
	public WrappedPacketInSetCreativeSlot(int slot, @NonNull ItemStack stack) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				int.class,
				slot
			),
			Map.of(
				stack.getHandle().getClass(),
				stack.getHandle()
			)
		).build());
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayInSetCreativeSlot",
			networkProtocolGame + "PacketPlayInSetCreativeSlot"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}

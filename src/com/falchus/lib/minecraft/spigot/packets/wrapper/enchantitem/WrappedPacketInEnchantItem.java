package com.falchus.lib.minecraft.spigot.packets.wrapper.enchantitem;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketInEnchantItem extends PacketEnchantItemWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayInEnchantItem",
		networkProtocolGame + "PacketPlayInEnchantItem"
	);

	private WrappedPacketInEnchantItem(@NonNull Object handle) {
		super(handle, names);
	}
	
	public WrappedPacketInEnchantItem(int syncId, int buttonId) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				int.class,
				syncId
			),
			Map.of(
				int.class,
				buttonId
			)
		).build());
	}
}

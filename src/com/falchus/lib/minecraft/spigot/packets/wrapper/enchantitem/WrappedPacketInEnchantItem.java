package com.falchus.lib.minecraft.spigot.packets.wrapper.enchantitem;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInEnchantItem extends PacketEnchantItemWrapper {

	public WrappedPacketInEnchantItem(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayInEnchantItem",
				networkProtocolGame + "PacketPlayInEnchantItem"
			)
		);
	}
}

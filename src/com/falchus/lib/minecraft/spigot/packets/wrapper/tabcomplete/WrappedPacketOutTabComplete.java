package com.falchus.lib.minecraft.spigot.packets.wrapper.tabcomplete;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutTabComplete extends PacketTabCompleteWrapper {
	
	public WrappedPacketOutTabComplete(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutTabComplete",
				networkProtocolGame + "PacketPlayOutTabComplete"
			)
		);
	}
}

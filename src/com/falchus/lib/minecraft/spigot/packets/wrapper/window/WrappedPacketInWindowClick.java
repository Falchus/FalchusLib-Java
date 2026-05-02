package com.falchus.lib.minecraft.spigot.packets.wrapper.window;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInWindowClick extends PacketWindowClickWrapper {

	private WrappedPacketInWindowClick(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayInWindowClick",
				networkProtocolGame + "PacketPlayInWindowClick"
			)
		);
	}
}

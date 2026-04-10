package com.falchus.lib.minecraft.spigot.packets.wrapper.window.click;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInWindowClick extends PacketWindowClickWrapper {

	public WrappedPacketInWindowClick(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayInWindowClick",
				networkProtocolGame + "PacketPlayInWindowClick"
			)
		);
	}
}

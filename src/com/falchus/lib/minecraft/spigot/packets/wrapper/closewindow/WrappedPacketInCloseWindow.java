package com.falchus.lib.minecraft.spigot.packets.wrapper.closewindow;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInCloseWindow extends PacketCloseWindowWrapper {

	public WrappedPacketInCloseWindow(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayInCloseWindow",
				networkProtocolGame + "PacketPlayInCloseWindow"
			)
		);
	}
}

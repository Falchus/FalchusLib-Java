package com.falchus.lib.minecraft.spigot.packets.wrapper.use;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInUseEntity extends PacketUseEntityWrapper {

	public WrappedPacketInUseEntity(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayInUseEntity",
				networkProtocolGame + "PacketPlayInUseEntity"
			)
		);
	}
}

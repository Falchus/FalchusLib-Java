package com.falchus.lib.minecraft.spigot.packets.wrapper.camera;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutCamera extends PacketCameraWrapper {

	public WrappedPacketOutCamera(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutCamera",
				networkProtocolGame + "PacketPlayOutCamera"
			)
		);
	}
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.animation;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutAnimation extends PacketAnimationWrapper {

	private WrappedPacketOutAnimation(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutAnimation",
				networkProtocolGame + "PacketPlayOutAnimation"
			)
		);
	}
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.block.breakanimation;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutBlockBreakAnimation extends PacketBlockBreakAnimationWrapper {

	private WrappedPacketOutBlockBreakAnimation(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutBlockBreakAnimation",
				networkProtocolGame + "PacketPlayOutBlockBreakAnimation"
			)
		);
	}
}

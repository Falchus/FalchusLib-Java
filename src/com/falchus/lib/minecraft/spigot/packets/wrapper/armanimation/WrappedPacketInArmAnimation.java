package com.falchus.lib.minecraft.spigot.packets.wrapper.armanimation;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInArmAnimation extends PacketArmAnimationWrapper {

	private WrappedPacketInArmAnimation(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayInArmAnimation",
			networkProtocolGame + "PacketPlayInArmAnimation"
		));
	}
}

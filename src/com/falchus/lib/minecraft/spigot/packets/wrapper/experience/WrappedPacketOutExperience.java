package com.falchus.lib.minecraft.spigot.packets.wrapper.experience;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutExperience extends PacketExperienceWrapper {

	public WrappedPacketOutExperience(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutExperience",
				networkProtocolGame + "PacketPlayOutExperience"
			)
		);
	}
}

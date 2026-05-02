package com.falchus.lib.minecraft.spigot.packets.wrapper.abilities;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInAbilities extends PacketAbilitiesWrapper {

	private WrappedPacketInAbilities(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayInAbilities",
				networkProtocolGame + "PacketPlayInAbilities"
			)
		);
	}
}

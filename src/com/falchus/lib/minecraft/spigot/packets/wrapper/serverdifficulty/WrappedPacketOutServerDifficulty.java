package com.falchus.lib.minecraft.spigot.packets.wrapper.serverdifficulty;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutServerDifficulty extends PacketServerDifficultyWrapper {

	private WrappedPacketOutServerDifficulty(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutServerDifficulty",
			networkProtocolGame + "PacketPlayOutServerDifficulty"
		));
	}
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.score;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutScoreboardScore extends PacketScoreboardScoreWrapper {

	WrappedPacketOutScoreboardScore(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutScoreboardScore",
				networkProtocolGame + "PacketPlayOutScoreboardScore"
			)
		);
	}
}

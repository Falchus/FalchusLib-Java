package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.objective;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutScoreboardObjective extends PacketScoreboardObjectiveWrapper {

	private WrappedPacketOutScoreboardObjective(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutScoreboardObjective",
				networkProtocolGame + "PacketPlayOutScoreboardObjective"
			)
		);
	}
}

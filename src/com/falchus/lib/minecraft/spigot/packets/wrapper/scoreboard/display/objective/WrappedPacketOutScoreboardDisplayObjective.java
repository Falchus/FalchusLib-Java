package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.display.objective;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutScoreboardDisplayObjective extends PacketScoreboardDisplayObjectiveWrapper {

	private WrappedPacketOutScoreboardDisplayObjective(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutScoreboardDisplayObjective",
			networkProtocolGame + "PacketPlayOutScoreboardDisplayObjective"
		));
	}
}

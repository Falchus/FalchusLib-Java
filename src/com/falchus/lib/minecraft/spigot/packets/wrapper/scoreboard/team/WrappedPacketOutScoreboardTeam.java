package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.team;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutScoreboardTeam extends PacketScoreboardTeamWrapper {

	WrappedPacketOutScoreboardTeam(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutScoreboardTeam",
				networkProtocolGame + "PacketPlayOutScoreboardTeam"
			)
		);
	}
}

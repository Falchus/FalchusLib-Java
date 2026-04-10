package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.team;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutScoreboardTeam extends PacketScoreboardTeamWrapper {

    WrappedPacketOutScoreboardTeam(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutScoreboardTeam",
                        networkProtocolGame + "PacketPlayOutScoreboardTeam"
                )
        );
    }
}

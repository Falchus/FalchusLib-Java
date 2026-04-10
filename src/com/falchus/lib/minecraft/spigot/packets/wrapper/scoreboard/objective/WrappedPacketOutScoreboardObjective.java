package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.objective;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutScoreboardObjective extends PacketScoreboardObjectiveWrapper {

    WrappedPacketOutScoreboardObjective(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutScoreboardObjective",
                        networkProtocolGame + "PacketPlayOutScoreboardObjective"
                )
        );
    }
}

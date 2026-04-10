package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.display.objective;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutScoreboardDisplayObjective extends PacketScoreboardDisplayObjectiveWrapper {

    WrappedPacketOutScoreboardDisplayObjective(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutScoreboardDisplayObjective",
                        networkProtocolGame + "PacketPlayOutScoreboardDisplayObjective"
                )
        );
    }
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.score;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutScoreboardScore extends PacketScoreboardScoreWrapper {

    WrappedPacketOutScoreboardScore(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutScoreboardScore",
                        networkProtocolGame + "PacketPlayOutScoreboardScore"
                )
        );
    }
}

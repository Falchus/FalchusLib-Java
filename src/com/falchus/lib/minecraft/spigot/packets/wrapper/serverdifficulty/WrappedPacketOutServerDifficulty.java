package com.falchus.lib.minecraft.spigot.packets.wrapper.serverdifficulty;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutServerDifficulty extends PacketServerDifficultyWrapper {

    WrappedPacketOutServerDifficulty(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutServerDifficulty",
                        networkProtocolGame + "PacketPlayOutServerDifficulty"
                )
        );
    }
}

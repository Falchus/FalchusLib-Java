package com.falchus.lib.minecraft.spigot.packets.wrapper.abilities;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketInAbilities extends PacketAbilitiesWrapper {

    public WrappedPacketInAbilities(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInAbilities",
                        networkProtocolGame + "PacketPlayInAbilities"
                )
        );
    }
}

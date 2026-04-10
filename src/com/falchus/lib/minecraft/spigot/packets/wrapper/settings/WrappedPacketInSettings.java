package com.falchus.lib.minecraft.spigot.packets.wrapper.settings;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketInSettings extends PacketSettingsWrapper {

    public WrappedPacketInSettings(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInSettings",
                        networkProtocolCommon + "ServerboundClientInformationPacket"
                )
        );
    }
}

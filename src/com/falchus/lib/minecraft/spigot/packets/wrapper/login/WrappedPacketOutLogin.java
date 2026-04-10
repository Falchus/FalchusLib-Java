package com.falchus.lib.minecraft.spigot.packets.wrapper.login;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutLogin extends PacketLoginWrapper {

    WrappedPacketOutLogin(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutLogin",
                        networkProtocolGame + "PacketPlayOutLogin"
                )
        );
    }
}

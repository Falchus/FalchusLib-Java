package com.falchus.lib.minecraft.spigot.packets.wrapper.window.click;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketInWindowClick extends PacketWindowClickWrapper {

    public WrappedPacketInWindowClick(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInWindowClick",
                        networkProtocolGame + "PacketPlayInWindowClick"
                )
        );
    }
}

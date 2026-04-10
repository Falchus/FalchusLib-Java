package com.falchus.lib.minecraft.spigot.packets.wrapper.open.signeditor;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutOpenSignEditor extends PacketOpenSignEditorWrapper {

    public WrappedPacketOutOpenSignEditor(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutOpenSignEditor",
                        networkProtocolGame + "PacketPlayOutOpenSignEditor"
                )
        );
    }
}

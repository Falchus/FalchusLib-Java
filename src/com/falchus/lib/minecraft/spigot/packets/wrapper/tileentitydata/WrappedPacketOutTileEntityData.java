package com.falchus.lib.minecraft.spigot.packets.wrapper.tileentitydata;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketOutTileEntityData extends PacketTileEntityDataWrapper {

    public WrappedPacketOutTileEntityData(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayOutTileEntityData",
                        networkProtocolGame + "PacketPlayOutTileEntityData"
                )
        );
    }
}

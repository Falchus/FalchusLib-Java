package com.falchus.lib.minecraft.spigot.packets.wrapper.tileentitydata;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutTileEntityData extends PacketTileEntityDataWrapper {

	private WrappedPacketOutTileEntityData(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutTileEntityData",
				networkProtocolGame + "PacketPlayOutTileEntityData"
			)
		);
	}
}

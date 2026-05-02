package com.falchus.lib.minecraft.spigot.packets.wrapper.settings;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInSettings extends PacketSettingsWrapper {

	private WrappedPacketInSettings(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayInSettings",
				networkProtocolCommon + "ServerboundClientInformationPacket"
			)
		);
	}
}

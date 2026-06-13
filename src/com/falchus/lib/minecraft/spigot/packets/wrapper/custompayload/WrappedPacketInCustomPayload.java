package com.falchus.lib.minecraft.spigot.packets.wrapper.custompayload;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInCustomPayload extends PacketCustomPayloadWrapper {

	private WrappedPacketInCustomPayload(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayInCustomPayload",
			networkProtocolCommon + "ServerboundCustomPayloadPacket"
		));
	}
}

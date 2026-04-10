package com.falchus.lib.minecraft.spigot.packets.wrapper.custompayload;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutCustomPayload extends PacketCustomPayloadWrapper {

	public WrappedPacketOutCustomPayload(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutCustomPayload",
				networkProtocolCommon + "ClientboundCustomPayloadPacket"
			)
		);
	}
}

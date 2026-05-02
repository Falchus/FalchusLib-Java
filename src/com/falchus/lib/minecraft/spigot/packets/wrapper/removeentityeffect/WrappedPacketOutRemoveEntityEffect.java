package com.falchus.lib.minecraft.spigot.packets.wrapper.removeentityeffect;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutRemoveEntityEffect extends PacketRemoveEntityEffectWrapper {

	private WrappedPacketOutRemoveEntityEffect(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutRemoveEntityEffect",
				networkProtocolCommon + "PacketPlayOutRemoveEntityEffect"
			)
		);
	}
}

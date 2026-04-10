package com.falchus.lib.minecraft.spigot.packets.wrapper.namedsoundeffect;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutNamedSoundEffect extends PacketNamedSoundEffectWrapper {

	public WrappedPacketOutNamedSoundEffect(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutNamedSoundEffect",
				networkProtocolGame + "PacketPlayOutNamedSoundEffect"
			)
		);
	}
}

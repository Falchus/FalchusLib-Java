package com.falchus.lib.minecraft.spigot.packets.wrapper.gamestatechange;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutGameStateChange extends PacketGameStateChangeWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutGameStateChange",
		networkProtocolGame + "PacketPlayOutGameStateChange"
	);

	private WrappedPacketOutGameStateChange(@NonNull Object handle) {
		super(handle, names);
	}
	
	// TODO: support 1.16.1+
	public WrappedPacketOutGameStateChange(int reason, float param) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				int.class,
				reason
			),
			Map.of(
				float.class,
				param
			)
		).build());
	}
}

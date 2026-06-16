package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.network.syncher.DataWatcher;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutEntityMetadata extends PacketEntityWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutEntityMetadata",
		networkProtocolGame + "PacketPlayOutEntityMetadata"
	);
	
	private WrappedPacketOutEntityMetadata(@NonNull Object handle) {
		super(handle, names);
	}
	
	// TODO: support 1.19.3+
	public WrappedPacketOutEntityMetadata(int id, @NonNull DataWatcher tracker, boolean forceUpdateAll) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				int.class,
				id
			),
			Map.of(
				tracker.getHandle().getClass(),
				tracker.getHandle()
			),
			Map.of(
				boolean.class,
				forceUpdateAll
			)
		).build());
	}
}

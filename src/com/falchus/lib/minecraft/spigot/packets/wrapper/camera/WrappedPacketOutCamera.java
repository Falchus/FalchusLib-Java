package com.falchus.lib.minecraft.spigot.packets.wrapper.camera;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.world.entity.Entity;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutCamera extends PacketCameraWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutCamera",
		networkProtocolGame + "PacketPlayOutCamera"
	);

	private WrappedPacketOutCamera(@NonNull Object handle) {
		super(handle, names);
	}
	
	public WrappedPacketOutCamera(@NonNull Entity entity) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				VersionProvider.get().getEntity(),
				entity.getHandle()
			)
		).build());
	}
}

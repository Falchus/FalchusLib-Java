package com.falchus.lib.minecraft.spigot.packets.wrapper.animation;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.world.entity.Entity;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutAnimation extends PacketAnimationWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutAnimation",
		networkProtocolGame + "PacketPlayOutAnimation"
	);

	private WrappedPacketOutAnimation(@NonNull Object handle) {
		super(handle, names);
	}
	
	public WrappedPacketOutAnimation(@NonNull Entity entity, int animationId) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				VersionProvider.get().getEntity(),
				entity.getHandle()
			),
			Map.of(
				int.class,
				animationId
			)
		).build());
	}
}

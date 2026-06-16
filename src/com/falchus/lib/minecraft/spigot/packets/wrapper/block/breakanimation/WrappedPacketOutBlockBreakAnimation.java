package com.falchus.lib.minecraft.spigot.packets.wrapper.block.breakanimation;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition.BlockPosition;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutBlockBreakAnimation extends PacketBlockBreakAnimationWrapper {

	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutBlockBreakAnimation",
		networkProtocolGame + "PacketPlayOutBlockBreakAnimation"
	);
	
	private WrappedPacketOutBlockBreakAnimation(@NonNull Object handle) {
		super(handle, names);
	}
	
	public WrappedPacketOutBlockBreakAnimation(int entityId, @NonNull BlockPosition pos, int progress) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				int.class,
				entityId
			),
			Map.of(
				pos.getHandle().getClass(),
				pos.getHandle()
			),
			Map.of(
				int.class,
				progress
			)
		).build());
	}
}

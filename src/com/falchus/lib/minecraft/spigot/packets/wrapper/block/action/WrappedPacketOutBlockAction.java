package com.falchus.lib.minecraft.spigot.packets.wrapper.block.action;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition.BlockPosition;
import com.falchus.lib.minecraft.spigot.wrapper.world.level.block.Block;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutBlockAction extends PacketBlockActionWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutBlockAction",
		networkProtocolGame + "PacketPlayOutBlockAction"
	);

	private WrappedPacketOutBlockAction(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
	}
	
	public WrappedPacketOutBlockAction(@NonNull BlockPosition pos, @NonNull Block block, int type, int data) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				pos.getHandle().getClass(),
				pos.getHandle()
			),
			Map.of(
				block.getHandle().getClass(),
				block.getHandle()
			),
			Map.of(
				int.class,
				type
			),
			Map.of(
				int.class,
				data
			)
		).build());
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutBlockAction",
			networkProtocolGame + "PacketPlayOutBlockAction"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}

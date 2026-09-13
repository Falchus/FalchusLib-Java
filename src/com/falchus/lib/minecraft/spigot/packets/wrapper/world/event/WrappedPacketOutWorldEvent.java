package com.falchus.lib.minecraft.spigot.packets.wrapper.world.event;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.core.baseblockposition.BlockPosition;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutWorldEvent extends PacketWorldEventWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutWorldEvent",
		networkProtocolGame + "PacketPlayOutWorldEvent"
	);

	private WrappedPacketOutWorldEvent(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
	}
	
	public WrappedPacketOutWorldEvent(int eventId, @NonNull BlockPosition pos, int data, boolean global) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				int.class,
				eventId
			),
			Map.of(
				pos.getHandle().getClass(),
				pos.getHandle()
			),
			Map.of(
				int.class,
				data
			),
			Map.of(
				boolean.class,
				global
			)
		).build());
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutWorldEvent",
			networkProtocolGame + "PacketPlayOutWorldEvent"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}

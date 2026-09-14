package com.falchus.lib.minecraft.spigot.packets.wrapper.window;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutWindowData extends PacketWindowDataWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutWindowData",
		networkProtocolGame + "PacketPlayOutWindowData"
	);

	private WrappedPacketOutWindowData(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
	}
	
	public WrappedPacketOutWindowData(int syncId, int propertyId, int value) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				int.class,
				syncId
			),
			Map.of(
				int.class,
				propertyId
			),
			Map.of(
				int.class,
				value
			)
		).build());
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutWindowData",
			networkProtocolGame + "PacketPlayOutWindowData"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}

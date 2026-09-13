package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutEntityDestroy extends PacketEntityWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutEntityDestroy",
		networkProtocolGame + "PacketPlayOutEntityDestroy"
	);
	
	private WrappedPacketOutEntityDestroy(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
	}
	
	public WrappedPacketOutEntityDestroy(int... entityIds) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				int[].class,
				entityIds
			)
		).build());
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutEntityDestroy",
			networkProtocolGame + "PacketPlayOutEntityDestroy"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}

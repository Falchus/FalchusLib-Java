package com.falchus.lib.minecraft.spigot.packets.wrapper.closewindow;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutCloseWindow extends PacketCloseWindowWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutCloseWindow",
		networkProtocolGame + "PacketPlayOutCloseWindow"
	);

	private WrappedPacketOutCloseWindow(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
	}
	
	public WrappedPacketOutCloseWindow(int syncId) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				int.class,
				syncId
			)
		).build());
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutCloseWindow",
			networkProtocolGame + "PacketPlayOutCloseWindow"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}

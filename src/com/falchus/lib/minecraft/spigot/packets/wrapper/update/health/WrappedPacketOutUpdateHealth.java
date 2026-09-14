package com.falchus.lib.minecraft.spigot.packets.wrapper.update.health;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutUpdateHealth extends PacketUpdateHealthWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutUpdateHealth",
		networkProtocolGame + "PacketPlayOutUpdateHealth"
	);

	private WrappedPacketOutUpdateHealth(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
	}
	
	public WrappedPacketOutUpdateHealth(float health, int food, float saturation) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				float.class,
				health
			),
			Map.of(
				int.class,
				food
			),
			Map.of(
				float.class,
				saturation
			)
		).build());
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutUpdateHealth",
			networkProtocolGame + "PacketPlayOutUpdateHealth"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}

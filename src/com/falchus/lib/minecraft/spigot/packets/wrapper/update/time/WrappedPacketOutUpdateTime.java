package com.falchus.lib.minecraft.spigot.packets.wrapper.update.time;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutUpdateTime extends PacketUpdateTimeWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutUpdateTime",
		networkProtocolGame + "PacketPlayOutUpdateTime"
	);

	private WrappedPacketOutUpdateTime(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
	}
	
	public WrappedPacketOutUpdateTime(long time, long timeOfDay, boolean tickDayTime) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				long.class,
				time
			),
			Map.of(
				long.class,
				timeOfDay
			),
			Map.of(
				boolean.class,
				tickDayTime
			)
		).build());
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutUpdateTime",
			networkProtocolGame + "PacketPlayOutUpdateTime"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
}

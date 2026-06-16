package com.falchus.lib.minecraft.spigot.packets.wrapper.experience;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutExperience extends PacketExperienceWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutExperience",
		networkProtocolGame + "PacketPlayOutExperience"
	);

	private WrappedPacketOutExperience(@NonNull Object handle) {
		super(handle, names);
	}
	
	public WrappedPacketOutExperience(float barProgress, int experienceLevel, int experience) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				float.class,
				barProgress
			),
			Map.of(
				int.class,
				experienceLevel
			),
			Map.of(
				int.class,
				experience
			)
		).build());
	}
}

package com.falchus.lib.minecraft.spigot.packets.wrapper.spawn.entity;

import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.world.entity.EntityLiving;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

public class WrappedPacketOutSpawnEntityLiving extends PacketSpawnEntityLivingWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutSpawnEntityLiving",
		networkProtocolGame + "PacketPlayOutSpawnEntityLiving"
	);

	private WrappedPacketOutSpawnEntityLiving(@NonNull Object handle) {
		super(handle, names);
	}
	
	// TODO: support 1.19+
	public WrappedPacketOutSpawnEntityLiving(@NonNull EntityLiving entity) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				VersionProvider.get().getEntityLiving(),
				entity.getHandle()
			)
		).build());
	}
}

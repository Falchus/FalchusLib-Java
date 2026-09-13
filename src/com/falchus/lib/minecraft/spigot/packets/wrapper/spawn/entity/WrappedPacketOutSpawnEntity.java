package com.falchus.lib.minecraft.spigot.packets.wrapper.spawn.entity;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.world.entity.Entity;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;

// TODO: add all entity spawn packets
public class WrappedPacketOutSpawnEntity extends PacketSpawnEntityWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutSpawnEntity",
		networkProtocolGame + "PacketPlayOutSpawnEntity"
	);
	
	Field data;

	private WrappedPacketOutSpawnEntity(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
		
		data = getFirstField(
			"data",
			"k"
		);
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutSpawnEntity",
			networkProtocolGame + "PacketPlayOutSpawnEntity"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	}
	
	// TODO: support 1.21+
	public WrappedPacketOutSpawnEntity(@NonNull Entity entity, int entityData) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				VersionProvider.get().getEntity(),
				entity.getHandle()
			),
			Map.of(
				int.class,
				entityData
			)
		).build());
	}
	
	public int getData() {
		return getFieldValue(data);
	}

	public void setData(int data) {
		setField(this.data, data);
	}
}

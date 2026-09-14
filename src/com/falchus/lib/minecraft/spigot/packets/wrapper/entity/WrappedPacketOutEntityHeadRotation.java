package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.minecraft.spigot.wrapper.world.entity.Entity;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedPacketOutEntityHeadRotation extends PacketEntityWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutEntityHeadRotation",
		networkProtocolGame + "PacketPlayOutEntityHeadRotation"
	);
	
	Field yHeadRot;
	
	private WrappedPacketOutEntityHeadRotation(@NonNull Object handle) {
<<<<<<< HEAD
		super(handle, names);
=======
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutEntityHeadRotation",
			networkProtocolGame + "PacketPlayOutEntityHeadRotation"
		));
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
		
		yHeadRot = getFirstField(
			"yHeadRot",
			"b"
		);
	}
	
	public WrappedPacketOutEntityHeadRotation(@NonNull Entity entity, byte headYaw) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				VersionProvider.get().getEntity(),
				entity.getHandle()
			),
			Map.of(
				byte.class,
				headYaw
			)
		).build());
	}

	public byte getYHeadRot() {
		return getFieldValue(yHeadRot);
	}
	
	public void setYHeadRot(byte yHeadRot) {
		setField(this.yHeadRot, yHeadRot);
	}
}

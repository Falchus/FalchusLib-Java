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
public class WrappedPacketOutEntityStatus extends PacketEntityWrapper {
	
	private static final Set<String> names = Set.of(
		version.getPackageNms() + "PacketPlayOutEntityStatus",
		networkProtocolGame + "PacketPlayOutEntityStatus"
	);

	Field eventId;
	
	private WrappedPacketOutEntityStatus(@NonNull Object handle) {
		super(handle, names);
		
		eventId = getFirstField(
			"eventId",
			"b"
		);
	}
	
	public WrappedPacketOutEntityStatus(@NonNull Entity entity, byte status) {
		this(new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				VersionProvider.get().getEntity(),
				entity.getHandle()
			),
			Map.of(
				byte.class,
				status
			)
		).build());
	}

	public byte getEventId() {
		return getFieldValue(eventId);
	}
	
	public void setEventId(byte eventId) {
		setField(this.eventId, eventId);
	}
}

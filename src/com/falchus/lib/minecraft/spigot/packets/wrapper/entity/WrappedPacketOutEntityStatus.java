package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import java.lang.reflect.Field;
import java.util.Set;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedPacketOutEntityStatus extends PacketEntityWrapper {

	Field eventId;
	
	private WrappedPacketOutEntityStatus(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutEntityStatus",
				networkProtocolGame + "PacketPlayOutEntityStatus"
			)
		);
		
		eventId = getFirstField(
			"eventId",
			"b"
		);
	}

	public byte getEventId() {
		return getFieldValue(eventId);
	}
	
	public void setEventId(byte eventId) {
		setField(this.eventId, eventId);
	}
}

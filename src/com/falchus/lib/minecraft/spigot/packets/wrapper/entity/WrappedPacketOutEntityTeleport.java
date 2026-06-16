package com.falchus.lib.minecraft.spigot.packets.wrapper.entity;

import java.lang.reflect.Field;
import java.util.Set;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
public class WrappedPacketOutEntityTeleport extends PacketEntityWrapper {

	Field onGround;
	
	private WrappedPacketOutEntityTeleport(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutEntityTeleport",
			networkProtocolGame + "PacketPlayOutEntityTeleport"
		));
		
		onGround = getFirstField(
			"onGround",
			"g"
		);
	}

	public boolean isOnGround() {
		return getFieldValue(onGround);
	}
	
	public void setOnGround(boolean onGround) {
		setField(this.onGround, onGround);
	}
}

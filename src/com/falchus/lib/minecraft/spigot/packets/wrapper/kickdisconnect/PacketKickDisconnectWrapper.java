package com.falchus.lib.minecraft.spigot.packets.wrapper.kickdisconnect;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.Component;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketKickDisconnectWrapper extends PacketWrapper implements PacketKickDisconnect {
	
	Field reason;

	PacketKickDisconnectWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		reason = getFirstField(
			"reason",
			"a"
		);
	}

	@Override
	public Component getReason() {
		return SpigotWrapper.wrap(getFieldValue(reason));
	}
	
	@Override
	public void setReason(Component reason) {
		setField(this.reason, reason.getHandle());
	}
}

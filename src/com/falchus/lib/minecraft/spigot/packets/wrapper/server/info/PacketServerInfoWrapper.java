package com.falchus.lib.minecraft.spigot.packets.wrapper.server.info;

import java.lang.reflect.Field;
import java.util.Set;

import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.protocol.status.ServerPing;

import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(makeFinal = true)
class PacketServerInfoWrapper extends PacketWrapper implements PacketServerInfo {
	
	Field ping;

	PacketServerInfoWrapper(@NonNull Object handle, @NonNull Set<String> names) {
		super(handle, names);
		
		ping = getFirstField(
			"status",
			"b"
		);
	}

	@Override
	public ServerPing getPing() {
		return SpigotWrapper.wrap(getFieldValue(ping));
	}
	
	@Override
	public void setPing(ServerPing ping) {
		setField(this.ping, ping.getHandle());
	}
}

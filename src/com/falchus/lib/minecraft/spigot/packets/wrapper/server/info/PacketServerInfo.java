package com.falchus.lib.minecraft.spigot.packets.wrapper.server.info;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.protocol.status.ServerPing;

public interface PacketServerInfo extends IPacketWrapper {

	ServerPing getPing();
	void setPing(ServerPing ping);
}

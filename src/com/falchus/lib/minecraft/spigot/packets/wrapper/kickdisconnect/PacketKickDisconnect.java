package com.falchus.lib.minecraft.spigot.packets.wrapper.kickdisconnect;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.Component;

public interface PacketKickDisconnect extends IPacketWrapper {

	Component getReason();
	void setReason(Component reason);
}

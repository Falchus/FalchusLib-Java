package com.falchus.lib.minecraft.spigot.packets.wrapper.chat;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.Component;

public interface PacketChat extends IPacketWrapper {

	/**
	 * In:	{@link String}
	 * Out:	{@link Component}
	 */
	Object getMessage();
	void setMessage(String message);
}

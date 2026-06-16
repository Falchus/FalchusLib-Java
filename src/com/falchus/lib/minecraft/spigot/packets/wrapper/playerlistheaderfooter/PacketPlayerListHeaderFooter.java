package com.falchus.lib.minecraft.spigot.packets.wrapper.playerlistheaderfooter;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.Component;

import lombok.NonNull;

public interface PacketPlayerListHeaderFooter extends IPacketWrapper {

	Component getHeader();
	void setHeader(@NonNull String header);
	
	Component getFooter();
	void setFooter(@NonNull String footer);
}

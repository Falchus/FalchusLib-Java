package com.falchus.lib.minecraft.spigot.packets.wrapper.chat;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketOutChat extends PacketChatWrapper {

	public WrappedPacketOutChat(@NonNull Object handle) {
		super(handle, Set.of(
				version.getPackageNms() + "PacketPlayOutChat",
				networkProtocolGame + "ClientboundSystemChatPacket",
				networkProtocolGame + "ClientboundPlayerChatPacket",
				networkProtocolGame + "ClientboundDisguisedChatPacket"
			)
		);
	}
	
	@Override
	public void setMessage(String message) {
		setField(this.message, version.createChatComponentText(message));
	}
}

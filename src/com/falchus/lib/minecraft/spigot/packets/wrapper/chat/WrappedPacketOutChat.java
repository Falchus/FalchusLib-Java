package com.falchus.lib.minecraft.spigot.packets.wrapper.chat;

import java.util.Set;

import com.falchus.lib.minecraft.spigot.wrapper.network.chat.WrappedComponent;

import lombok.NonNull;

public class WrappedPacketOutChat extends PacketChatWrapper {

	private WrappedPacketOutChat(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayOutChat",
			networkProtocolGame + "ClientboundSystemChatPacket",
			networkProtocolGame + "ClientboundPlayerChatPacket",
			networkProtocolGame + "ClientboundDisguisedChatPacket"
		));
	}
	
	@Override
	public void setMessage(String message) {
		setField(this.message, new WrappedComponent(message).getHandle());
	}
}

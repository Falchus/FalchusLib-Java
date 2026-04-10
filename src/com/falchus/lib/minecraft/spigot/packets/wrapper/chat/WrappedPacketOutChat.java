package com.falchus.lib.minecraft.spigot.packets.wrapper.chat;

import lombok.NonNull;

import java.util.Set;

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

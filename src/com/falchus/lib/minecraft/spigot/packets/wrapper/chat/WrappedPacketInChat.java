package com.falchus.lib.minecraft.spigot.packets.wrapper.chat;

import lombok.NonNull;

import java.util.Set;

public class WrappedPacketInChat extends PacketChatWrapper {

    public WrappedPacketInChat(@NonNull Object handle) {
        super(handle, Set.of(
                        version.getPackageNms() + "PacketPlayInChat",
                        networkProtocolGame + "ServerboundChatPacket"
                )
        );
    }

    @Override
    public String getMessage() {
        return (String) super.getMessage();
    }

    @Override
    public void setMessage(String message) {
        setField(this.message, message);
    }
}

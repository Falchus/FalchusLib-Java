package com.falchus.lib.minecraft.spigot.packets.wrapper.chat;

import java.util.Set;

import lombok.NonNull;

public class WrappedPacketInChat extends PacketChatWrapper {

	private WrappedPacketInChat(@NonNull Object handle) {
		super(handle, Set.of(
			version.getPackageNms() + "PacketPlayInChat",
<<<<<<< HEAD
			networkProtocolGame + "PacketPlayInChat"
=======
			networkProtocolGame + "ServerboundChatPacket"
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
		));
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

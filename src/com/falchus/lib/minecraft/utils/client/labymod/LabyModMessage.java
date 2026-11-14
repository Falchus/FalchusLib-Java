package com.falchus.lib.minecraft.utils.client.labymod;

import java.util.UUID;

import com.falchus.lib.manager.EventManager;
import com.falchus.lib.minecraft.enums.Client;
import com.falchus.lib.minecraft.events.player.PlayerClientJoinEvent;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.NonNull;

public class LabyModMessage {
	
	public static final String labymod3Main = "labymod3:main";
	public static final String damageindicator = "DAMAGEINDICATOR";
	
	public static void handle(@NonNull UUID uuid, byte[] message) {
		ByteBuf buf = Unpooled.wrappedBuffer(message);
		String key = LabyModProtocol.readString(buf, Short.MAX_VALUE);
		
		// LabyMod3 user joins the server
		if (key.equals("INFO")) {
			EventManager.callEvent(new PlayerClientJoinEvent(uuid, Client.LABYMOD3));
		}
	}
}

package com.falchus.lib.minecraft.utils.client.lunar;

import java.util.UUID;

import com.falchus.lib.manager.EventManager;
import com.falchus.lib.minecraft.enums.Client;
import com.falchus.lib.minecraft.events.player.PlayerClientJoinEvent;
import com.google.gson.JsonObject;

import lombok.NonNull;

public class LunarMessage {

	public static final String lunarApollo = "lunar:apollo";
	public static final String apolloJson = "apollo:json";
	
	public static void onRegister(@NonNull UUID uuid, @NonNull String world) {
		// Sending the player's world name to the client is required for some modules
		updateWorld(uuid, world);
		
		EventManager.callEvent(new PlayerClientJoinEvent(uuid, Client.LUNAR));
	}
	
    public static void updateWorld(@NonNull UUID uuid, String world) {
    	if (world == null) return;
        JsonObject message = new JsonObject();
        message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.player.v1.UpdatePlayerWorldMessage");
        message.addProperty("world", world);
        
		LunarJsonPacketUtil.sendMessage(uuid, message);
    }
}

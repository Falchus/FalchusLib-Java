package com.falchus.lib.minecraft.spigot.utils.version.v1_9_R1;

import java.lang.reflect.Method;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.utils.version.VersionAdapter;
import com.falchus.lib.utils.ReflectionUtils;

import lombok.NonNull;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public class VersionAdapter_v1_9_R1 extends VersionAdapter {
	
	private Method player$Spigot_sendMessage() {
		return ReflectionUtils.getMethod(player$Spigot, "sendMessage", ChatMessageType.class, BaseComponent[].class);
	}
	
    @Override
    public void sendActionbar(@NonNull Player player, @NonNull String message) {
		try {
			Object spigot = getPlayerSpigot(player);
			Object chatMessage = TextComponent.fromLegacyText(message);
			
			player$Spigot_sendMessage().invoke(spigot, ChatMessageType.ACTION_BAR, chatMessage);
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
    }
}

package com.falchus.lib.minecraft.spigot.wrapper.network.protocol.status;

import java.util.List;

import com.falchus.lib.minecraft.spigot.wrapper.ISpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.Component;
import com.mojang.authlib.GameProfile;

public interface ServerPing extends ISpigotWrapper {
	
	Component getDescription();
	
	Players getPlayers();
	
	interface Players extends ISpigotWrapper {
		int getMaxPlayers();
		int getOnlinePlayers();
		List<GameProfile> getSample();
	}
}

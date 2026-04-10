package com.falchus.lib.minecraft.spigot.packets;

import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.entity.Player;

import com.falchus.lib.FalchusLib;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;

import io.netty.channel.Channel;

public class PacketInjector {
	
	private static final Set<UUID> injected = ConcurrentHashMap.newKeySet();

	public static void inject(Player player) {
		UUID uuid = player.getUniqueId();
		if (!injected.add(uuid)) return;
		
		try {
			Object entityPlayer = PlayerUtils.getEntityPlayer(player);
			
			Object connection = VersionProvider.get().getEntityPlayer_playerConnection().get(entityPlayer);
			Object networkManager = VersionProvider.get().getPlayerConnection_networkManager().get(connection);
			Object ch = VersionProvider.get().getNetworkManager_channel().get(networkManager);
			
			Channel channel = (Channel) ch;
			if (channel.pipeline().get(FalchusLib.nameFull + "_" + uuid) != null) return;
			
			channel.pipeline().addBefore("packet_handler", FalchusLib.nameFull + "_" + uuid, new PacketChannelHandler(player));
		} catch (Exception e) {
			injected.remove(uuid);
			throw new RuntimeException(e);
		}
	}
	
	public static void uninject(Player player) {
		UUID uuid = player.getUniqueId();
		if (!injected.remove(uuid)) return;
		
		try {
			Object entityPlayer = PlayerUtils.getEntityPlayer(player);
			
			Object connection = VersionProvider.get().getEntityPlayer_playerConnection().get(entityPlayer);
			Object networkManager = VersionProvider.get().getPlayerConnection_networkManager().get(connection);
			Object ch = VersionProvider.get().getNetworkManager_channel().get(networkManager);
			
			Channel channel = (Channel) ch;
			if (channel.pipeline().get(FalchusLib.nameFull + "_" + uuid) == null) return;
			
			channel.eventLoop().submit(() -> {
				channel.pipeline().remove(FalchusLib.nameFull + "_" + uuid);
				return null;
			});
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

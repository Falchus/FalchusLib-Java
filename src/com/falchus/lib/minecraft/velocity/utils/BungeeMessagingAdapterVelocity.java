package com.falchus.lib.minecraft.velocity.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import com.falchus.lib.minecraft.utils.messaging.BungeeMessaging;
import com.falchus.lib.minecraft.utils.messaging.BungeeMessagingAdapter;
import com.falchus.lib.minecraft.velocity.FalchusLibMinecraftVelocity;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.PluginMessageEvent;
import com.velocitypowered.api.event.connection.PluginMessageEvent.ForwardResult;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ServerConnection;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;

import lombok.NonNull;

public class BungeeMessagingAdapterVelocity implements BungeeMessagingAdapter {
	
	private static final FalchusLibMinecraftVelocity plugin = FalchusLibMinecraftVelocity.getInstance();
	
	private static final MinecraftChannelIdentifier channel = MinecraftChannelIdentifier.from(BungeeMessaging.channel);
	
	public BungeeMessagingAdapterVelocity() {
		plugin.getProxy().getChannelRegistrar().register(channel);
		plugin.getProxy().getEventManager().register(plugin, this);
	}

	@Subscribe
	public void onPluginMessage(PluginMessageEvent event) {
		if (!channel.equals(event.getIdentifier())) return;
		if (!(event.getSource() instanceof ServerConnection)) return;
		
		event.setResult(ForwardResult.handled());
		
		try (DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()))) {
			switch (in.readUTF()) {
				case "Ping": {
					ByteArrayOutputStream bytes = new ByteArrayOutputStream();
					DataOutputStream out = new DataOutputStream(bytes);
					out.writeUTF("Pong");
					((ServerConnection) event.getSource()).sendPluginMessage(channel, bytes.toByteArray());
					break;
				}
				case "Execute":
					execute(in.readUTF());
					break;
					
				default:
					break;
			}
		} catch (Exception e) {}
	}
	
	@Override
	public boolean isAvailable() {
		return true;
	}
	
	@Override
	public void execute(@NonNull String command) {
		plugin.getProxy().getCommandManager().executeAsync(plugin.getProxy().getConsoleCommandSource(), command);
	}
	
	@Override
	public void connect(@NonNull String name, @NonNull String server) {
		Player player = plugin.getProxy().getPlayer(name).orElse(null);
		if (player == null) return;
		
		plugin.getProxy().getServer(server).ifPresent(s -> player.createConnectionRequest(s).fireAndForget());
	}
}

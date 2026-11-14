package com.falchus.lib.minecraft.velocity;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import com.falchus.lib.minecraft.FalchusLibMinecraft;
import com.falchus.lib.minecraft.IFalchusLibMinecraft;
import com.falchus.lib.minecraft.utils.messaging.BungeeMessaging;
import com.falchus.lib.minecraft.velocity.listeners.*;
import com.falchus.lib.minecraft.velocity.listeners.message.*;
import com.falchus.lib.minecraft.velocity.utils.*;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.messages.ChannelIdentifier;
import com.velocitypowered.api.proxy.messages.LegacyChannelIdentifier;
import com.velocitypowered.api.proxy.messages.MinecraftChannelIdentifier;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import net.labymod.serverapi.server.velocity.LabyModProtocolService;

@Plugin(id = "falchuslib",
		name = "FalchusLib",
//		version = "0.0.0",
		description = "A library designed to simplify & speed up software development.",
		authors = {"Falchus"},
		url = "https://lib-java.falchus.com")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FalchusLibMinecraftVelocity implements IFalchusLibMinecraft {

	final ProxyServer proxy;
	final Logger logger;
	final File dataFolder;
	final File file;
	final Metrics.Factory metricsFactory;
	
	@Getter static FalchusLibMinecraftVelocity instance;
	
	LabyModMessageListener labyModMessageListener;
	LunarMessageListener lunarMessageListener;
	JoinQuitListener joinQuitListener;
	
	@Inject
	public FalchusLibMinecraftVelocity(ProxyServer proxy, Logger logger, @DataDirectory Path dataFolder, Metrics.Factory metricsFactory) {
		this.proxy = proxy;
		this.logger = logger;
		this.dataFolder = new File(dataFolder.toFile().getParentFile(), this.getClass().getAnnotation(Plugin.class).name());
		this.metricsFactory = metricsFactory;
		
		try {
			file = new File(getClass().getProtectionDomain().getCodeSource().getLocation().toURI());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Subscribe
	public void onProxyInitialize(ProxyInitializeEvent event) {
		instance = this;
		metricsFactory.make(this, 28136);
		
		FalchusLibMinecraft.set(this);
		
		BungeeMessaging.setAdapter(new BungeeMessagingAdapterVelocity());
		
		LabyModProtocolService.initialize(this, proxy, logger);
		
		getProxy().getScheduler().buildTask(this, () -> {
			labyModMessageListener = new LabyModMessageListener();
			lunarMessageListener = new LunarMessageListener();
			joinQuitListener = new JoinQuitListener();
		}).delay(1, TimeUnit.MILLISECONDS).schedule();
	}
	
	@Override
	public void sendMessage(@NonNull UUID uuid, @NonNull String channel, byte[] data) {
		proxy.getPlayer(uuid).ifPresent(player -> {
			ChannelIdentifier identifier = channel.indexOf(':') >= 0
					? MinecraftChannelIdentifier.from(channel)
					: new LegacyChannelIdentifier(channel);
			
			player.sendPluginMessage(identifier, data);
		});
	}
}

package com.falchus.lib.minecraft.velocity;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;

import org.slf4j.Logger;

import com.falchus.lib.minecraft.velocity.utils.Metrics;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;

import lombok.Getter;

@Plugin(id = "falchuslib",
		name = "FalchusLib",
//		version = "0.0.0",
		description = "A simple library.",
		authors = {"Falchus"},
		url = "https://lib.falchus.com")
@Getter
public class FalchusLibMinecraftVelocity {

	private final ProxyServer server;
	private final Logger logger;
	private final File dataFolder;
	private final File file;
	private final Metrics.Factory metricsFactory;
	
	private static FalchusLibMinecraftVelocity instance;
	
	@Inject
	public FalchusLibMinecraftVelocity(ProxyServer server, Logger logger, @DataDirectory Path dataFolder, Metrics.Factory metricsFactory) {
		this.server = server;
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
	}
	
	public static FalchusLibMinecraftVelocity getInstance() {
		return instance;
	}
}

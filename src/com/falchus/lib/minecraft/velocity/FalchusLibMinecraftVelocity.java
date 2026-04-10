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

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Plugin(id = "falchuslib",
		name = "FalchusLib",
//		version = "0.0.0",
		description = "A library designed to simplify & speed up software development.",
		authors = {"Falchus"},
		url = "https://lib-java.falchus.com")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FalchusLibMinecraftVelocity {

	final ProxyServer server;
	final Logger logger;
	final File dataFolder;
	final File file;
	final Metrics.Factory metricsFactory;

	@Getter static FalchusLibMinecraftVelocity instance;

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
}

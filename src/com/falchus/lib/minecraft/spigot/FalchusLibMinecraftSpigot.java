package com.falchus.lib.minecraft.spigot;

import org.bukkit.plugin.java.JavaPlugin;

import com.falchus.lib.minecraft.spigot.utils.Metrics;

public class FalchusLibMinecraftSpigot extends JavaPlugin {

	private static FalchusLibMinecraftSpigot instance;
	private Contexts contexts;
	
	@Override
	public void onEnable() {
		instance = this;
		contexts = new Contexts();
		
		new Metrics(this, 28050);
	}
	
	public static FalchusLibMinecraftSpigot getInstance() {
		return instance;
	}
	
	public Contexts getContexts() {
		return contexts;
	}
}

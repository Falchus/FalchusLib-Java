package com.falchus.lib.minecraft.bungee;

import com.falchus.lib.minecraft.bungee.utils.Metrics;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

@Getter
public class FalchusLibMinecraftBungee extends Plugin {

	private static FalchusLibMinecraftBungee instance;
	private Contexts contexts;
	
	@Override
	public void onEnable() {
		instance = this;
		contexts = new Contexts();
		
		new Metrics(this, 28051);
	}
	
	public static FalchusLibMinecraftBungee getInstance() {
		return instance;
	}
}

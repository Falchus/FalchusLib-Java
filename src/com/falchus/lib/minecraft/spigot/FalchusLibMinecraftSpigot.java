package com.falchus.lib.minecraft.spigot;

import java.util.Set;

import org.bukkit.plugin.java.JavaPlugin;

import com.falchus.lib.minecraft.spigot.listeners.*;
import com.falchus.lib.minecraft.spigot.listeners.message.*;
import com.falchus.lib.minecraft.spigot.manager.*;
import com.falchus.lib.minecraft.spigot.utils.*;
import com.falchus.lib.minecraft.spigot.wrapper.*;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FalchusLibMinecraftSpigot extends JavaPlugin {

	@Getter static FalchusLibMinecraftSpigot instance;
	
	LabyModMessageListener labyModMessageListener;
	LunarMessageListener lunarMessageListener;
	EntityPlayerListener entityPlayerListener;
	FreezeListener freezeListener;
	ItemListener itemListener;
	JoinQuitListener joinQuitListener;
	LobbyCancelListener lobbyCancelListener;
	ClientManager clientManager;
	
	@Override
	public void onEnable() {
		instance = this;
		new Metrics(this, 28050);
		
		new SpigotWrapper(this, Set.of(
			getClass().getPackageName()
		));
		
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		labyModMessageListener = new LabyModMessageListener();
		lunarMessageListener = new LunarMessageListener();
		entityPlayerListener = new EntityPlayerListener();
		freezeListener = new FreezeListener();
		itemListener = new ItemListener();
		joinQuitListener = new JoinQuitListener();
		lobbyCancelListener = new LobbyCancelListener();
		clientManager = new ClientManager();
	}
}

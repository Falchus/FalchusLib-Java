package com.falchus.lib.minecraft.spigot;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.falchus.lib.minecraft.FalchusLibMinecraft;
import com.falchus.lib.minecraft.IFalchusLibMinecraft;
import com.falchus.lib.minecraft.spigot.listeners.*;
import com.falchus.lib.minecraft.spigot.listeners.message.*;
import com.falchus.lib.minecraft.spigot.task.SpigotTask;
import com.falchus.lib.minecraft.spigot.utils.*;
import com.falchus.lib.minecraft.utils.messaging.BungeeMessaging;
import com.falchus.lib.task.impl.TaskImpl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;
import net.labymod.serverapi.server.bukkit.LabyModProtocolService;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FalchusLibMinecraftSpigot extends JavaPlugin implements IFalchusLibMinecraft {

	@Getter static FalchusLibMinecraftSpigot instance;
	
	LabyModMessageListener labyModMessageListener;
	LunarMessageListener lunarMessageListener;
	EntityPlayerListener entityPlayerListener;
	FreezeListener freezeListener;
	ItemListener itemListener;
	JoinQuitListener joinQuitListener;
	LobbyCancelListener lobbyCancelListener;
	
	@Override
	public void onEnable() {
		instance = this;
		new Metrics(this, 28050);
		
		FalchusLibMinecraft.set(this);
		
		BungeeMessaging.setAdapter(new BungeeMessagingAdapterSpigot());
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getServer().getMessenger().registerOutgoingPluginChannel(this, BungeeMessaging.channel);
		
		TaskImpl.setDefaultTask(SpigotTask::of);
		
		LabyModProtocolService.initialize(this);
		
		labyModMessageListener = new LabyModMessageListener();
		lunarMessageListener = new LunarMessageListener();
		entityPlayerListener = new EntityPlayerListener();
		freezeListener = new FreezeListener();
		itemListener = new ItemListener();
		joinQuitListener = new JoinQuitListener();
		lobbyCancelListener = new LobbyCancelListener();
	}
	
	@Override
	public void sendMessage(@NonNull UUID uuid, @NonNull String channel, byte[] data) {
		Player player = Bukkit.getPlayer(uuid);
		if (player == null) return;
		
		player.sendPluginMessage(this, channel, data);
	}
}

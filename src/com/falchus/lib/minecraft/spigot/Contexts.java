package com.falchus.lib.minecraft.spigot;

import com.falchus.lib.minecraft.spigot.listeners.*;
import com.falchus.lib.minecraft.spigot.listeners.message.*;
import com.falchus.lib.minecraft.spigot.manager.*;
import com.falchus.lib.minecraft.spigot.utils.nms.NmsAdapter;
import com.falchus.lib.minecraft.spigot.utils.nms.NmsProvider;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contexts {
	
	LabyModMessageListener labyModMessageListener;
	LunarMessageListener lunarMessageListener;
	EntityPlayerListener entityPlayerListener;
	FreezeListener freezeListener;
	ItemListener itemListener;
	LobbyCancelListener lobbyCancelListener;
	VanishListener vanishListener;
	ClientManager clientManager;
	NmsAdapter nmsAdapter;
	
	public Contexts() {
		init();
	}
	
	public void init() {
		labyModMessageListener = new LabyModMessageListener();
		lunarMessageListener = new LunarMessageListener();
		entityPlayerListener = new EntityPlayerListener();
		freezeListener = new FreezeListener();
		itemListener = new ItemListener();
		lobbyCancelListener = new LobbyCancelListener();
		vanishListener = new VanishListener();
		clientManager = new ClientManager();
		nmsAdapter = NmsProvider.get();
	}
}

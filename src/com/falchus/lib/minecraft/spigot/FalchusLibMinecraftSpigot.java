package com.falchus.lib.minecraft.spigot;

import com.falchus.lib.minecraft.FalchusLibMinecraft;
import com.falchus.lib.minecraft.spigot.listeners.*;
import com.falchus.lib.minecraft.spigot.listeners.message.LabyModMessageListener;
import com.falchus.lib.minecraft.spigot.listeners.message.LunarMessageListener;
import com.falchus.lib.minecraft.spigot.manager.ClientManager;
import com.falchus.lib.minecraft.spigot.utils.Metrics;
import com.falchus.lib.minecraft.spigot.utils.SchedulerUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FalchusLibMinecraftSpigot extends JavaPlugin {

    @Getter
    static FalchusLibMinecraftSpigot instance;

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

        if (FalchusLibMinecraft.isFolia()) {
            getLogger().info("Folia detected — using Folia-compatible scheduler.");
        }

        new Metrics(this, 28050);

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
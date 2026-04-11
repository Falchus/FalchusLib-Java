package com.falchus.lib.minecraft.spigot.utils;

import java.lang.reflect.Method;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import com.falchus.lib.minecraft.FalchusLibMinecraft;
import com.falchus.lib.minecraft.enums.Software;
import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.utils.reflection.ReflectionUtils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SchedulerUtils {

    private static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();

    public static void runTask(@NonNull Runnable runnable) {
        if (FalchusLibMinecraft.getSoftware() == Software.FOLIA) {
        	try {
	        	Method bukkit_getGlobalRegionScheduler = ReflectionUtils.getMethod(Bukkit.class, "getGlobalRegionScheduler");
	        	Object globalRegionSchedulerINST = bukkit_getGlobalRegionScheduler.invoke(null);
	        	
	        	Class<?> globalRegionScheduler = ReflectionUtils.getClass("io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler");
	        	Method globalRegionScheduler_run = ReflectionUtils.getMethod(globalRegionScheduler, "run",
	        		Plugin.class,
	        		Consumer.class
	        	);
	        	
	        	Consumer<Object> consumer = task -> runnable.run();
	        	globalRegionScheduler_run.invoke(globalRegionSchedulerINST,
	        		plugin,
	        		consumer
	        	);
        	} catch (Exception e) {
        		throw new RuntimeException(e);
        	}
        } else {
            Bukkit.getScheduler().runTask(plugin, runnable);
        }
    }
}

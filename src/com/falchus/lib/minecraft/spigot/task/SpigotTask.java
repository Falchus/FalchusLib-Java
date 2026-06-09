package com.falchus.lib.minecraft.spigot.task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.task.Task;

import lombok.NonNull;

public class SpigotTask extends Task {
	
	private final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	
	private static final Map<Integer, BukkitTask> tasks = new ConcurrentHashMap<>();

	@SuppressWarnings("unchecked")
	@Override
	protected final <T extends Task> T execute(@NonNull Runnable runnable) {
		if (Bukkit.isPrimaryThread()) {
			super.execute(runnable);
		} else {
			tasks.put(getId(), Bukkit.getScheduler().runTask(plugin, runnable));
		}
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected final <T extends Task> T executeAsync(@NonNull Runnable runnable) {
		if (!Bukkit.isPrimaryThread()) {
			super.executeAsync(runnable);
		} else {
			tasks.put(getId(), Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable));
		}
		return (T) this;
	}
	
	@Override
	public final void end() {
		super.end();
		BukkitTask task = tasks.remove(getId());
		if (task != null) {
			task.cancel();
		}
	}
}

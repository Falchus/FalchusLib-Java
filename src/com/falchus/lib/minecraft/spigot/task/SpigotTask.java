package com.falchus.lib.minecraft.spigot.task;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitTask;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.task.Task;

import lombok.NonNull;

public class SpigotTask extends Task {
	
	private static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	
	private static final Map<Integer, BukkitTask> tasks = new ConcurrentHashMap<>();
	
	public static SpigotTask of(@NonNull Runnable runnable) {
		return new SpigotTask() {
			@Override
			protected void onRun(int tick) {
				execute(runnable);
			}
		};
	}
	
	private static long toTicks(long time, @NonNull TimeUnit unit) {
		return Math.max(0, unit.toMillis(time) / 50);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected final <T extends Task> T execute(@NonNull Runnable runnable) {
		if (Bukkit.isPrimaryThread()) {
			super.execute(runnable);
		} else {
			Bukkit.getScheduler().runTask(plugin, runnable);
		}
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected final <T extends Task> T executeAsync(@NonNull Runnable runnable) {
		if (!Bukkit.isPrimaryThread()) {
			super.executeAsync(runnable);
		} else {
			Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
		}
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Task> T runTimer(long delay, long period, @NonNull TimeUnit unit) {
		long d = toTicks(delay, unit);
		long p = Math.max(1, toTicks(period, unit));
		tasks.put(getId(), Bukkit.getScheduler().runTaskTimer(plugin, this, d, p));
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Task> T runTimerAsync(long delay, long period, @NonNull TimeUnit unit) {
		long d = toTicks(delay, unit);
		long p = Math.max(1, toTicks(period, unit));
		tasks.put(getId(), Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this, d, p));
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Task> T runLater(long delay, @NonNull TimeUnit unit) {
		tasks.put(getId(), Bukkit.getScheduler().runTaskLater(plugin, () -> {
			run();
			end();
		}, toTicks(delay, unit)));
		return (T) this;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Task> T runLaterAsync(long delay, @NonNull TimeUnit unit) {
		tasks.put(getId(), Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, () -> {
			run();
			end();
		}, toTicks(delay, unit)));
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

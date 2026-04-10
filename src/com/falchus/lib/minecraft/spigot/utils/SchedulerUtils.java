package com.falchus.lib.minecraft.spigot.utils;

import com.falchus.lib.minecraft.FalchusLibMinecraft;
import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

/**
 * Abstraction layer over Bukkit's and Folia's scheduler APIs.
 * <p>
 * Folia distributes world regions across threads, so synchronous tasks
 * must target a specific region. Use the appropriate method based on context:
 * <ul>
 *     <li>{@link #runTask} — global main thread (Bukkit) / global region (Folia)</li>
 *     <li>{@link #runTaskAsync} — async on both platforms</li>
 *     <li>{@link #runTaskForEntity} — entity's own region (Folia) / main thread (Bukkit)</li>
 * </ul>
 */
@UtilityClass
public class SchedulerUtils {

    /**
     * Runs a task on the main thread (Bukkit) or the global region thread (Folia).
     */
    public static void runTask(@NonNull Runnable runnable) {
        FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
        if (FalchusLibMinecraft.isFolia()) {
            Bukkit.getGlobalRegionScheduler().run(plugin, task -> runnable.run());
        } else {
            Bukkit.getScheduler().runTask(plugin, runnable);
        }
    }

    /**
     * Runs a task asynchronously on both Bukkit and Folia.
     */
    public static void runTaskAsync(@NonNull Runnable runnable) {
        FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
        if (FalchusLibMinecraft.isFolia()) {
            Bukkit.getAsyncScheduler().runNow(plugin, task -> runnable.run());
        } else {
            Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
        }
    }

    /**
     * Runs a task on the thread that owns the entity's region (Folia)
     * or the main thread (Bukkit).
     * <p>
     * On Folia, {@code EntityScheduler#run} returns {@code null} when the entity
     * has already been removed from the world — in that case the task is silently
     * dropped, which is the correct behaviour (nothing to update for a dead entity).
     *
     * @param entity   the entity whose region thread to target
     * @param runnable the task to execute
     */
    public static void runTaskForEntity(@NonNull Entity entity, @NonNull Runnable runnable) {
        FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
        if (FalchusLibMinecraft.isFolia()) {
            // Returns null if the entity is no longer valid — safe to ignore.
            entity.getScheduler().run(plugin, task -> runnable.run(), null);
        } else {
            Bukkit.getScheduler().runTask(plugin, runnable);
        }
    }

    /**
     * Runs a delayed task.
     *
     * @param runnable   the task
     * @param delayTicks delay in server ticks (1 tick ≈ 50 ms)
     */
    public static void runTaskLater(@NonNull Runnable runnable, long delayTicks) {
        FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
        if (FalchusLibMinecraft.isFolia()) {
            Bukkit.getGlobalRegionScheduler().runDelayed(plugin, task -> runnable.run(), Math.max(1, delayTicks));
        } else {
            Bukkit.getScheduler().runTaskLater(plugin, runnable, Math.max(1, delayTicks));
        }
    }
}
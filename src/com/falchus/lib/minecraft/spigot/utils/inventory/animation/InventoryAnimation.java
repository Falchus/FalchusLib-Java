package com.falchus.lib.minecraft.spigot.utils.inventory.animation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.task.Task;

import lombok.Getter;

@Getter
public abstract class InventoryAnimation {

    protected final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
    protected int delayTicks = 2;
    protected List<ItemStack> excludedItems = new ArrayList<>();
	
	/**
	 * Called to implement animation logic.
	 * This will be scheduled automatically after delayTicks.
	 */
	protected abstract void animate(Player player, Inventory inventory, ItemStack[] items, int tick);
	
	/**
	 * Plays the animation with automatic scheduling using delayTicks (in {@link TimeUnit#MILLISECONDS}).
	 */
	public final void play(Player player, Inventory inventory) {
		ItemStack[] items = inventory.getContents();
		for (ItemStack item : items) {
			if (!excludedItems.contains(item)) {
				inventory.removeItem(item);
			}
		}
		
		new Task() {
			@Override
			public void onRun(int tick) {
				if (!player.getOpenInventory().getTopInventory().equals(inventory)) {
					end();
					return;
				}
				
				if (tick >= items.length) {
					end();
					return;
				}
				
				ItemStack item = items[tick];
				if (excludedItems.contains(item)) {
					run();
					return;
				}
				
				animate(player, inventory, items, tick);
			}
		}.runTimer(delayTicks, TimeUnit.MILLISECONDS);
    }
}

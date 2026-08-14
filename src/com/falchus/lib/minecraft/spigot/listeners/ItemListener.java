package com.falchus.lib.minecraft.spigot.listeners;

import java.util.UUID;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.falchus.lib.interfaces.consumer.TriConsumer;
import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.spigot.utils.ItemUtils;

public class ItemListener implements Listener {

	private static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	
	public ItemListener() {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
    	ItemStack item = event.getItem();
    	if (item == null) return;
    	
        UUID uuid = ItemUtils.getUUID(item);
        if (uuid != null) {
            Consumer<Player> action = ItemUtils.itemActions.get(uuid);
            if (action != null) {
                event.setCancelled(true);
                action.accept(event.getPlayer());
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player player)) return;

        Inventory top = event.getView().getTopInventory();
        ItemStack item = event.getCurrentItem();
        TriConsumer<Player, ItemStack, InventoryClickEvent> callback = ItemUtils.inventoryCallbacks.get(top);
        boolean click = event.getRawSlot() < top.getSize();
        if (click && item != null) {
            UUID uuid = ItemUtils.getUUID(item);
            if (uuid != null) {
                TriConsumer<Player, ItemStack, InventoryClickEvent> action = ItemUtils.itemActionsInventory.get(uuid);
                if (action != null) {
                    event.setCancelled(true);
                    action.accept(player, item, event);
                    return;
                }
            }
        }

        if (callback != null) {
            event.setCancelled(true);
            if (click && item != null) {
            	callback.accept(player, item, event);
            }
        }
    }
    
    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
    	ItemUtils.inventoryCallbacks.remove(event.getInventory());
    }
}

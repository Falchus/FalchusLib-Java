package com.falchus.lib.minecraft.spigot.listeners;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;

public class FreezeListener implements Listener {

	private final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	public final Set<UUID> players = new HashSet<>();

	public FreezeListener() {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if (!players.contains(event.getPlayer().getUniqueId())) return;

		Location from = event.getFrom();
		Location to = event.getTo();
		if (from.getBlockX() != to.getBlockX() || from.getBlockZ() != to.getBlockZ()) {
			event.setTo(from);
		}
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerInteract(PlayerInteractEvent event) {
		if (!players.contains(event.getPlayer().getUniqueId())) return;
		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
		if (!players.contains(event.getPlayer().getUniqueId())) return;
		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (!players.contains(event.getPlayer().getUniqueId())) return;
		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onInventoryClick(InventoryClickEvent event) {
		if (!players.contains(event.getWhoClicked().getUniqueId())) return;
		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onEntityDamage(EntityDamageEvent event) {
		if (!players.contains(event.getEntity().getUniqueId())) return;
		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getEntity() instanceof Player player) {
			if (players.contains(player.getUniqueId())) {
				event.setCancelled(true);
			}

			if (event.getDamager() instanceof Player damager) {
				if (players.contains(damager.getUniqueId())) {
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler(ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent event) {
		if (!players.contains(event.getPlayer().getUniqueId())) return;
		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onBlockPlace(BlockPlaceEvent event) {
		if (!players.contains(event.getPlayer().getUniqueId())) return;
		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		if (!players.contains(event.getPlayer().getUniqueId())) return;
		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		if (!players.contains(event.getPlayer().getUniqueId())) return;
		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		if (!players.contains(event.getEntity().getUniqueId())) return;
		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onHangingBreak(HangingBreakEvent event) {
		if (!players.contains(event.getEntity().getUniqueId())) return;
		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
		if (!players.contains(event.getPlayer().getUniqueId())) return;
		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerBucketFill(PlayerBucketFillEvent event) {
		if (!players.contains(event.getPlayer().getUniqueId())) return;
		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerShearEntity(PlayerShearEntityEvent event) {
		if (!players.contains(event.getPlayer().getUniqueId())) return;
		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent event) {
		if (!players.contains(event.getPlayer().getUniqueId())) return;
		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		if (!players.contains(event.getPlayer().getUniqueId())) return;
		event.setCancelled(true);
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		if (!players.contains(event.getPlayer().getUniqueId())) return;
		event.setCancelled(true);
	}
}

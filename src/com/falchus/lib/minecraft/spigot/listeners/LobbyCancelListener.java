package com.falchus.lib.minecraft.spigot.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.hanging.HangingBreakEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;
import org.bukkit.event.vehicle.VehicleDamageEvent;
import org.bukkit.event.vehicle.VehicleDestroyEvent;
import org.bukkit.event.vehicle.VehicleEntityCollisionEvent;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.spigot.events.LobbyCancelEvent;

public class LobbyCancelListener implements Listener {

	private final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	
	public LobbyCancelListener() {
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onPlayerInteract(PlayerInteractEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onInventoryClick(InventoryClickEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onEntityDamage(EntityDamageEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onEntityExplode(EntityExplodeEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onBlockBreak(BlockBreakEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onBlockPlace(BlockPlaceEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onBlockPhysics(BlockPhysicsEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onBlockFade(BlockFadeEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onBlockForm(BlockFormEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onPlayerPickupItem(PlayerPickupItemEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onFoodLevelChange(FoodLevelChangeEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onHangingBreak(HangingBreakEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onItemSpawn(ItemSpawnEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onPlayerBucketFill(PlayerBucketFillEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onPlayerShearEntity(PlayerShearEntityEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onPlayerArmorStandManipulate(PlayerArmorStandManipulateEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onEntityTarget(EntityTargetEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onVehicleDamage(VehicleDamageEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onVehicleDestroy(VehicleDestroyEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onVehicleEntityCollision(VehicleEntityCollisionEvent event) {
		LobbyCancelEvent e = new LobbyCancelEvent();
		Bukkit.getPluginManager().callEvent(e);
		event.setCancelled(e.isCancelled());
	}
}

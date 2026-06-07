package com.falchus.lib.minecraft.spigot.listeners;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.event.vehicle.*;
import org.bukkit.event.weather.*;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.spigot.events.LobbyCancelEvent;

public class LobbyCancelListener implements Listener {

	private final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	
	public LobbyCancelListener() {
		List<Class<? extends Event>> events = List.of(
			BlockBreakEvent.class,
			BlockBurnEvent.class,
			BlockDispenseEvent.class,
			BlockExplodeEvent.class,
			BlockFadeEvent.class,
			BlockFromToEvent.class,
			BlockGrowEvent.class,
			BlockIgniteEvent.class,
			BlockPhysicsEvent.class,
			BlockPistonExtendEvent.class,
			BlockPistonRetractEvent.class,
			BlockPlaceEvent.class,
			LeavesDecayEvent.class,
			
			EntityChangeBlockEvent.class,
			EntityCombustEvent.class,
			EntityDamageEvent.class,
			EntityExplodeEvent.class,
			EntityInteractEvent.class,
			EntitySpawnEvent.class,
			EntityTargetEvent.class,
			EntityTeleportEvent.class,
			FoodLevelChangeEvent.class,
			PotionSplashEvent.class,
			
			InventoryClickEvent.class,
			InventoryDragEvent.class,
			InventoryMoveItemEvent.class,
			InventoryPickupItemEvent.class,
			
			PlayerAchievementAwardedEvent.class,
			PlayerDropItemEvent.class,
			PlayerEditBookEvent.class,
			PlayerInteractEntityEvent.class,
			PlayerInteractEvent.class,
			PlayerItemConsumeEvent.class,
			PlayerItemDamageEvent.class,
			PlayerPickupItemEvent.class,
			PlayerPortalEvent.class,
			
			VehicleDamageEvent.class,
			VehicleEnterEvent.class,
			VehicleEntityCollisionEvent.class,
			
			WeatherChangeEvent.class
		);
		
		for (Class<? extends Event> clazz : events) {
			Bukkit.getPluginManager().registerEvent(
				clazz,
				this,
				EventPriority.LOW,
				(listener, event) -> cancel(event),
				plugin,
				true
			);
		}
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	private void cancel(Event event) {
		if (!(event instanceof Cancellable cancellable)) return;
		
		LobbyCancelEvent e = new LobbyCancelEvent(event);
		Bukkit.getPluginManager().callEvent(e);
		cancellable.setCancelled(e.isCancelled());
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void onEntitySpawn(EntitySpawnEvent event) {
		if (event.getEntityType() == EntityType.ARMOR_STAND) return;
		cancel(event);
	}
}

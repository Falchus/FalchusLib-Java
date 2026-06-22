package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.function.Supplier;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;
import com.falchus.lib.minecraft.spigot.packets.wrapper.entity.WrappedPacketOutEntityDestroy;
import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;

import lombok.NonNull;

public class Tag extends PlayerElement {
	
	private Supplier<String> messageSupplier;
	
	private ArmorStand entity;
	
	private Tag(@NonNull Player player) {
		super(player);
	}

	public void send(@NonNull Supplier<String> message) {
		messageSupplier = message;
		
		updateRunnable = () -> {
			String newMessage = messageSupplier.get();
			
			Location location = player.getLocation();
			double y = location.getY() + 2.15;
			if (player.isSneaking()) {
				y -= 0.3;
			}
			location.setY(y);
			
			if (entity == null) {
				entity = player.getWorld().spawn(location, ArmorStand.class);
				entity.setCustomName(newMessage);
				entity.setCustomNameVisible(true);
				entity.setGravity(false);
				entity.setVisible(false);
				entity.setMarker(true);
				
				IPacketWrapper destroyPacket = new WrappedPacketOutEntityDestroy(entity.getEntityId());
				PlayerUtils.sendPacket(player, destroyPacket);
			} else {
				entity.teleport(location);
				entity.setCustomName(newMessage);
			}
		};
		update();
	}
	
	public void sendUpdating(long intervalTicks, @NonNull Supplier<String> message) {
		super.sendUpdating(intervalTicks, () ->
			send(
				message
			)
		);
	}
	
	public void remove() {
		super.remove();
		
		if (entity != null) {
			entity.remove();
		}
	}
	
	public void setMessage(String message) {
		send(
			() -> message
		);
	}
}
package com.falchus.lib.minecraft.spigot.player.elements.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Supplier;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.CraftWorld;
import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.player.elements.PlayerElement;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;
import com.falchus.lib.minecraft.spigot.utils.builder.NmsPacketBuilder;
import com.falchus.lib.minecraft.spigot.utils.nms.NmsAdapter;
import com.falchus.lib.minecraft.spigot.utils.nms.NmsProvider;

import lombok.NonNull;
import net.minecraft.server.v1_8_R3.EntityWither;
import net.minecraft.server.v1_8_R3.WorldServer;

/**
 * Represents a Bossbar.
 */
public class Bossbar extends PlayerElement {

	private final NmsAdapter nms = NmsProvider.get();
    private final Map<UUID, EntityWither> withers = new HashMap<>();
    private final Map<UUID, Location> lastLocations = new HashMap<>();
    private final Map<UUID, String> lastMessages = new HashMap<>();
    private final Map<UUID, Float> lastProgresses = new HashMap<>();
    
	/**
	 * Constructs a new Bossbar.
	 */
	private Bossbar(@NonNull Player player) {
    	super(player);
    }
	
	/**
	 * Sends a one-time Bossbar message.
	 */
	public void send(@NonNull String message) {
	    UUID uuid = player.getUniqueId();
        Location eye = player.getEyeLocation().clone();
        Location location = eye.add(eye.getDirection().multiply(45));

        float yaw = eye.getYaw();
        float pitch = Math.max(-15, Math.min(15, eye.getPitch()));

	    EntityWither wither = withers.get(uuid);
	    Location lastLocation = lastLocations.get(uuid);
	    
	    if (wither == null || (lastLocation != null && !lastLocation.getWorld().equals(location.getWorld()))) {
	        if (wither != null) {
	        	Object destroyPacket = new NmsPacketBuilder(nms.getPackageNms() + "PacketPlayOutEntityDestroy")
	        			.withArgs(wither.getId())
	        			.build();
	            PlayerUtils.sendPacket(player, destroyPacket);
	        }
	        
            WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
            wither = new EntityWither(world);
            wither.setInvisible(true);
            wither.setCustomName(message);
            wither.setCustomNameVisible(true);
            wither.setHealth(wither.getMaxHealth());
            withers.put(uuid, wither);
            
            wither.setLocation(location.getX(), location.getY(), location.getZ(), yaw, pitch);
            
            Object spawnPacket = new NmsPacketBuilder(nms.getPackageNms() + "PacketPlayOutSpawnEntityLiving")
            		.withArgs(wither)
            		.build();
            PlayerUtils.sendPacket(player, spawnPacket);
            
            Object metadataPacket = new NmsPacketBuilder(nms.getPackageNms() + "PacketPlayOutEntityMetadata")
            		.withArgs(wither.getId(), wither.getDataWatcher(), true)
            		.build();
            PlayerUtils.sendPacket(player, metadataPacket);
	        
            Float lastProgress = lastProgresses.get(uuid);
            if (lastProgress != null) {
                wither.setHealth(lastProgress);
                
                metadataPacket = new NmsPacketBuilder(nms.getPackageNms() + "PacketPlayOutEntityMetadata")
                		.withArgs(wither.getId(), wither.getDataWatcher(), true)
                		.build();
                PlayerUtils.sendPacket(player, metadataPacket);
            }
	    } else {
            boolean moved = lastLocation == null || location.distanceSquared(lastLocation) > 4;
            boolean rotated = lastLocation != null && (Math.abs(yaw - lastLocation.getYaw()) > 2 || Math.abs(pitch - lastLocation.getPitch()) > 2);

            if (moved || rotated) {
                wither.setLocation(location.getX(), location.getY(), location.getZ(), yaw, pitch);
                
                Object teleportPacket = new NmsPacketBuilder(nms.getPackageNms() + "PacketPlayOutEntityTeleport")
                		.withArgs(wither)
                		.build();
                PlayerUtils.sendPacket(player, teleportPacket);
            }
	    }

        String lastMessage = lastMessages.get(uuid);
        if (!message.equals(lastMessage)) {
            wither.setCustomName(message);
            wither.setCustomNameVisible(true);
            
            Object metadataPacket = new NmsPacketBuilder(nms.getPackageNms() + "PacketPlayOutEntityMetadata")
            		.withArgs(wither.getId(), wither.getDataWatcher(), true)
            		.build();
            PlayerUtils.sendPacket(player, metadataPacket);
        }

        lastLocations.put(uuid, location);
        lastMessages.put(uuid, message);
	}
	
	/**
	 * Sends a Bossbar message repeatedly at a fixed interval.
	 */
	public void sendUpdating(long intervalTicks, @NonNull Supplier<String> messageSupplier) {
		super.sendUpdating(intervalTicks, () -> {
			String message = messageSupplier.get();
			send(message);
		});
	}
	
	/**
	 * Removes the Bossbar, cancelling any ongoing update tasks.
	 */
	@Override
	public void remove() {
		super.remove();
		
        UUID uuid = player.getUniqueId();
        EntityWither wither = withers.remove(uuid);
        lastLocations.remove(uuid);
        lastMessages.remove(uuid);
        lastProgresses.remove(uuid);
        if (wither != null) {
        	Object destroyPacket = new NmsPacketBuilder(nms.getPackageNms() + "PacketPlayOutEntityDestroy")
        			.withArgs(wither.getId())
        			.build();
            PlayerUtils.sendPacket(player, destroyPacket);
        }
	}
	
	/**
	 * Sets the health/progress of the Bossbar.
	 */
	public void setProgress(@NonNull Double progress) {
        EntityWither wither = withers.get(player.getUniqueId());
        if (wither == null) return;

        float max = wither.getMaxHealth();
        float newHealth = (float) Math.max(1, Math.min(max, progress * max));
        Float last = lastProgresses.get(player.getUniqueId());
        if (last == null || Math.abs(last - newHealth) > 0.01f) {
            wither.setHealth(newHealth);
            
            Object metadataPacket = new NmsPacketBuilder(nms.getPackageNms() + "PacketPlayOutEntityMetadata")
            		.withArgs(wither.getId(), wither.getDataWatcher(), true)
            		.build();
            PlayerUtils.sendPacket(player, metadataPacket);
            
            lastProgresses.put(player.getUniqueId(), newHealth);
        }
	}
}

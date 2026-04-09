package com.falchus.lib.minecraft.spigot.utils.builder;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.spigot.utils.EntityUtils;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.utils.WorldUtils;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.task.Task;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class EntityPlayerBuilder {
	
	private final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	
	private String name = "";
	private UUID uuid = UUID.randomUUID();
	private String skinValue;
	private String skinSignature;
	private boolean invisible = false;
	private Location location;
	private boolean lookAtPlayer = false;
	
	public EntityPlayerBuilder setName(@NonNull String name) {
		this.name = name;
		return this;
	}
	
	public EntityPlayerBuilder setUUID(@NonNull UUID uuid) {
		this.uuid = uuid;
		return this;
	}
	
	public EntityPlayerBuilder setSkin(@NonNull String skinValue, @NonNull String skinSignature) {
		this.skinValue = skinValue;
		this.skinSignature = skinSignature;
		return this;
	}
	
	public EntityPlayerBuilder setInvisible(boolean invisible) {
		this.invisible = invisible;
		return this;
	}
	
	public EntityPlayerBuilder setLocation(@NonNull Location location) {
		this.location = location;
		return this;
	}
	
	/**
	 * Registers a callback to be executed when a player interacts with this EntityPlayer.
	 */
	public EntityPlayerBuilder withInteractListener(@NonNull Consumer<Player> onPlayerInteract) {
		plugin.getEntityPlayerListener().actions.put(uuid, onPlayerInteract);
		return this;
	}
	
	/**
	 * Makes the EntityPlayer look at the player.
	 */
	public EntityPlayerBuilder lookAtPlayer(boolean lookAtPlayer) {
		this.lookAtPlayer = lookAtPlayer;
		return this;
	}
	
	/**
	 * Builds and returns the final EntityPlayer.
	 */
	public Object build() {
		try {
			Object server = ServerUtils.getMcServer();
			
			Object world = WorldUtils.getWorldServer(location != null ? location.getWorld() : Bukkit.getWorlds().get(0));
			
			GameProfile profile = new GameProfile(uuid, name);
			if (skinValue != null && skinSignature != null) {
				profile.getProperties().put("textures", new Property("textures", skinValue, skinSignature));
			}

			Object playerInteractManager = new ClassInstanceBuilder(
				VersionProvider.get().getPlayerInteractManager()
			).withParams(
				Map.of(
					VersionProvider.get().getWorld(),
					world
				)
			).build();

			Object entityPlayer = new ClassInstanceBuilder(
				VersionProvider.get().getEntityPlayer()
			).withParams(
				Map.of(
					VersionProvider.get().getMinecraftServer(),
					server
				),
				Map.of(
					VersionProvider.get().getWorldServer(),
					world
				),
				Map.of(
					GameProfile.class,
					profile
				),
				Map.of(
					VersionProvider.get().getPlayerInteractManager(),
					playerInteractManager
				)
			).build();
			
			if (location != null) {
				VersionProvider.get().getEntity_setLocation().invoke(entityPlayer,
					location.getX(),
					location.getY(),
					location.getZ(),
					location.getYaw(),
					location.getPitch()
				);
			}
			VersionProvider.get().getEntity_setInvisible().invoke(entityPlayer,
				invisible
			);
			
			plugin.getEntityPlayerListener().players.put(uuid, entityPlayer);
			
			if (lookAtPlayer) {
				new Task() {
					@Override
					public void onRun(int tick) {
						Entity entity = EntityUtils.getBukkitEntity(entityPlayer);
						if (entity == null || !entity.isValid()) {
							end();
							return;
						}
						
						Player nearest = null;
						double nearestDist = Double.MAX_VALUE;
						
						for (Player player : entity.getWorld().getPlayers()) {
						    double dist = player.getLocation().distanceSquared(entity.getLocation());
						    if (dist < nearestDist) {
						        nearestDist = dist;
						        nearest = player;
						    }
						}
						
						if (nearest != null) {
							Location from = entity.getLocation().add(0, 1.6, 0);
							Location to = nearest.getLocation().add(0, 1.6, 0);
							
							double dx = to.getX() - from.getX();
							double dy = to.getY() - from.getY();
							double dz = to.getZ() - from.getZ();
							
							double distanceXZ = Math.sqrt(dx * dx + dz * dz);
							
							float yaw = (float) Math.toDegrees(Math.atan2(-dx, dz));
							float pitch = (float) -Math.toDegrees(Math.atan2(dy, distanceXZ));
							EntityUtils.setYawPitch(entityPlayer, yaw, pitch);
						}
					}
				}.runTimer(100, TimeUnit.MILLISECONDS);
			}
			return entityPlayer;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

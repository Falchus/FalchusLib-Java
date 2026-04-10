package com.falchus.lib.minecraft.spigot.utils.builder;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.spigot.utils.EntityUtils;
import com.falchus.lib.minecraft.spigot.utils.SchedulerUtils;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.utils.WorldUtils;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.falchus.lib.task.Task;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.Getter;
import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

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
     * Makes the EntityPlayer continuously face the nearest player.
     * <p>
     * On Folia the per-tick update runs through the entity's own region scheduler
     * (via {@link SchedulerUtils#runTaskForEntity}) so it is always on the correct
     * thread. On plain Bukkit/Spigot the same code path falls back to the main thread.
     */
    public EntityPlayerBuilder lookAtPlayer(boolean lookAtPlayer) {
        this.lookAtPlayer = lookAtPlayer;
        return this;
    }

    /**
     * Builds and returns the final EntityPlayer NMS object.
     */
    public Object build() {
        try {
            Object server = ServerUtils.getMcServer();
            Object world = WorldUtils.getWorldServer(
                    location != null ? location.getWorld() : Bukkit.getWorlds().get(0)
            );

            GameProfile profile = new GameProfile(uuid, name);
            if (skinValue != null && skinSignature != null) {
                profile.getProperties().put("textures", new Property("textures", skinValue, skinSignature));
            }

            Object playerInteractManager = new ClassInstanceBuilder(
                    VersionProvider.get().getPlayerInteractManager()
            ).withParams(
                    Map.of(VersionProvider.get().getWorld(), world)
            ).build();

            Object entityPlayer = new ClassInstanceBuilder(
                    VersionProvider.get().getEntityPlayer()
            ).withParams(
                    Map.of(VersionProvider.get().getMinecraftServer(), server),
                    Map.of(VersionProvider.get().getWorldServer(), world),
                    Map.of(GameProfile.class, profile),
                    Map.of(VersionProvider.get().getPlayerInteractManager(), playerInteractManager)
            ).build();

            if (location != null) {
                VersionProvider.get().getEntity_setLocation().invoke(entityPlayer,
                        location.getX(), location.getY(), location.getZ(),
                        location.getYaw(), location.getPitch()
                );
            }
            VersionProvider.get().getEntity_setInvisible().invoke(entityPlayer, invisible);

            plugin.getEntityPlayerListener().players.put(uuid, entityPlayer);

            if (lookAtPlayer) {
                scheduleLookAtPlayer(entityPlayer);
            }

            return entityPlayer;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Schedules a repeating task that rotates the EntityPlayer toward the nearest
     * online player.
     * <p>
     * The task itself runs on {@link Task}'s own {@link java.util.concurrent.ScheduledExecutorService},
     * which is off the main/region thread. All <em>world reads</em> inside the task
     * ({@code entity.getLocation()}, {@code entity.getWorld().getPlayers()}) are
     * performed within a {@link SchedulerUtils#runTaskForEntity} call, so they land
     * on the entity's owning region thread on Folia — or the main thread on Bukkit.
     * The actual yaw/pitch write ({@link EntityUtils#setYawPitch}) is done inside
     * that same callback, keeping everything thread-safe.
     */
    private void scheduleLookAtPlayer(Object entityPlayer) {
        new Task() {
            @Override
            public void onRun(int tick) {
                Entity entity = EntityUtils.getBukkitEntity(entityPlayer);
                if (entity == null || !entity.isValid()) {
                    end();
                    return;
                }

                SchedulerUtils.runTaskForEntity(entity, () -> {
                    if (!entity.isValid()) return;

                    Player nearest = null;
                    double nearestDist = Double.MAX_VALUE;

                    for (Player player : entity.getWorld().getPlayers()) {
                        double dist = player.getLocation().distanceSquared(entity.getLocation());
                        if (dist < nearestDist) {
                            nearestDist = dist;
                            nearest = player;
                        }
                    }

                    if (nearest == null) return;

                    Location from = entity.getLocation().add(0, 1.6, 0);
                    Location to = nearest.getLocation().add(0, 1.6, 0);

                    double dx = to.getX() - from.getX();
                    double dy = to.getY() - from.getY();
                    double dz = to.getZ() - from.getZ();
                    double distXZ = Math.sqrt(dx * dx + dz * dz);

                    float yaw = (float) Math.toDegrees(Math.atan2(-dx, dz));
                    float pitch = (float) -Math.toDegrees(Math.atan2(dy, distXZ));
                    EntityUtils.setYawPitch(entityPlayer, yaw, pitch);
                });
            }
        }.runTaskTimer(100, TimeUnit.MILLISECONDS);
    }
}
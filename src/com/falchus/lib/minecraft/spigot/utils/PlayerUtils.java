package com.falchus.lib.minecraft.spigot.utils;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.spigot.enums.Sound;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.*;

@UtilityClass
public class PlayerUtils {

    public static final Set<UUID> vanished = new HashSet<>();
    public static final Map<UUID, Property> skins = new HashMap<>();
    public static final Map<UUID, String> names = new HashMap<>();
    private static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();

    /**
     * Sends a raw NMS packet to a player.
     */
    public static void sendPacket(@NonNull Player player, @NonNull Object packet) {
        VersionProvider.get().sendPacket(player, packet);
    }

    /**
     * Sends a title and/or subtitle to a player.
     */
    public static void sendTitle(@NonNull Player player, String title, String subtitle) {
        VersionProvider.get().sendTitle(player, title, subtitle);
    }

    /**
     * Sends a tablist header/footer and display name to a player.
     */
    public static void sendTablist(@NonNull Player player, List<String> header, List<String> footer, String name) {
        VersionProvider.get().sendTablist(player, header, footer, name);
    }

    /**
     * Sends a bossbar to a player.
     */
    public static void sendBossbar(@NonNull Player player, @NonNull String title, double progress) {
        VersionProvider.get().sendBossbar(player, title, progress);
    }

    /**
     * Removes the bossbar from a player.
     */
    public static void removeBossbar(@NonNull Player player) {
        VersionProvider.get().removeBossbar(player);
    }

    /**
     * Sends an action-bar message to a player.
     */
    public static void sendActionbar(@NonNull Player player, @NonNull String message) {
        VersionProvider.get().sendActionbar(player, message);
    }

    /**
     * Sends a nametag (prefix + suffix) for a player visible to all online players.
     */
    public static void sendNametag(@NonNull Player player, @NonNull String prefix, @NonNull String suffix) {
        VersionProvider.get().sendNametag(player, prefix, suffix);
    }

    /**
     * Removes the nametag of a player for all online players.
     */
    public static void removeNametag(@NonNull Player player) {
        VersionProvider.get().removeNametag(player);
    }

    /**
     * Plays a sound to a player at the given location.
     */
    public static void playSound(@NonNull Player player, @NonNull Location location,
                                 @NonNull Sound sound, float volume, float pitch) {
        VersionProvider.get().playSound(player, location, sound, volume, pitch);
    }

    /**
     * Freezes a player (prevents movement and interaction).
     */
    public static void freeze(@NonNull Player player) {
        plugin.getFreezeListener().players.add(player.getUniqueId());
    }

    /**
     * Unfreezes a player.
     */
    public static void unfreeze(@NonNull Player player) {
        plugin.getFreezeListener().players.remove(player.getUniqueId());
    }

    /**
     * Returns the LuckPerms rank prefix of a player, or an empty string.
     */
    public static @NotNull String getLuckPermsRankPrefix(@NonNull Player player) {
        if (!Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) return "";

        LuckPerms lp = LuckPermsProvider.get();
        User user = lp.getPlayerAdapter(Player.class).getUser(player);
        String prefix = user.getCachedData().getMetaData().getPrefix();
        return prefix != null ? prefix : "";
    }

    /**
     * Vanishes a player from all online players.
     * <p>
     * Uses {@code hidePlayer(Plugin, Player)} — the non-deprecated overload
     * available since Paper 1.18 and the only one in Paper 26.x.
     */
    public static void vanish(@NonNull Player player) {
        if (vanished.add(player.getUniqueId())) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.hidePlayer(plugin, player);
            }
        }
    }

    /**
     * Un-vanishes a player so all online players can see them again.
     * <p>
     * Uses {@code showPlayer(Plugin, Player)} — consistent with the
     * {@link #vanish} counterpart.
     */
    public static void unvanish(@NonNull Player player) {
        if (vanished.remove(player.getUniqueId())) {
            for (Player online : Bukkit.getOnlinePlayers()) {
                online.showPlayer(plugin, player);
            }
        }
    }

    /**
     * @return the NMS {@code EntityPlayer} for the given Bukkit {@link Player}.
     */
    public static Object getEntityPlayer(@NonNull Player player) {
        return VersionProvider.get().getEntityPlayer(player);
    }

    /**
     * @return the {@link GameProfile} from an NMS {@code EntityPlayer}.
     */
    public static GameProfile getProfile(@NonNull Object entityPlayer) {
        return VersionProvider.get().getProfile(entityPlayer);
    }

    /**
     * @return the current ping (ms) of a player.
     */
    public static int getPing(@NonNull Player player) {
        return VersionProvider.get().getPing(player);
    }

    /**
     * Sets a custom skin on a player (by UUID of the skin owner).
     */
    public static void setSkin(@NonNull Player player, @NonNull UUID uuid) {
        VersionProvider.get().setSkin(player, uuid);
    }

    /**
     * Resets a player's skin to their original one.
     */
    public static void resetSkin(@NonNull Player player) {
        VersionProvider.get().resetSkin(player);
    }

    /**
     * Sets a custom display name on a player.
     */
    public static void setName(@NonNull Player player, @NonNull String name) {
        VersionProvider.get().setName(player, name);
    }

    /**
     * Resets a player's display name to their original username.
     */
    public static void resetName(@NonNull Player player) {
        VersionProvider.get().resetName(player);
    }

    /**
     * Forces all clients to reload the player's {@link GameProfile}.
     */
    public static void refresh(@NonNull Player player) {
        VersionProvider.get().refresh(player);
    }

    /**
     * Adds a fake {@code EntityPlayer} to a player's player-info list.
     */
    public static void addEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
        VersionProvider.get().addEntityPlayer(player, entityPlayer);
    }

    /**
     * Removes a fake {@code EntityPlayer} from a player's player-info list.
     */
    public static void removeEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
        VersionProvider.get().removeEntityPlayer(player, entityPlayer);
    }

    /**
     * Spawns a fake {@code EntityPlayer} entity for a player.
     */
    public static void spawnEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
        VersionProvider.get().spawnEntityPlayer(player, entityPlayer);
    }

    /**
     * Connects the player to a proxy server via BungeeCord plugin messaging.
     */
    @SneakyThrows
    public static void connectToServer(@NonNull Player player, @NonNull String server) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
    }
}
package com.falchus.lib.minecraft.spigot.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.spigot.enums.Sound;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PlayerUtils {
	
	private static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	public static final Map<UUID, Property> skins = new HashMap<>();
	public static final Map<UUID, String> names = new HashMap<>();

	/**
	 * Sends a raw NMS packet to a player.
	 */
	public static void sendPacket(@NonNull Player player, @NonNull Object packet) {
		plugin.getNmsAdapter().sendPacket(player, packet);
	}
	
	/**
	 * Creates an instance of a packet.
	 */
	public static Object createPacket(@NonNull Class<?> packetClass, Object... constructorArgs) {
		return plugin.getNmsAdapter().createPacket(packetClass, constructorArgs);
	}
	
	/**
	 * Sends a title and/or subtitle to a player.
	 */
	public static void sendTitle(@NonNull Player player, String title, String subtitle) {
		plugin.getNmsAdapter().sendTitle(player, title, subtitle);
	}
	
	/**
	 * Sends a tablist to a player.
	 */
	public static void sendTablist(@NonNull Player player, List<String> header, List<String> footer, String name) {
		plugin.getNmsAdapter().sendTablist(player, header, footer, name);
	}
	
	/**
	 * Sends a bossbar to a player.
	 */
	public static void sendBossbar(@NonNull Player player, @NonNull String title, double progress) {
		plugin.getNmsAdapter().sendBossbar(player, title, progress);
	}
	
	/**
	 * Removes a bossbar from a player.
	 */
	public static void removeBossbar(@NonNull Player player) {
		plugin.getNmsAdapter().removeBossbar(player);
	}
	
	/**
	 * Plays a sound to a player.
	 */
	public static void playSound(@NonNull Player player, @NonNull Location location, @NonNull Sound sound, float volume, float pitch) {
		plugin.getNmsAdapter().playSound(player, location, sound, volume, pitch);
	}
	
	/**
	 * Freezes a player.
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
	 * Retrieves the LuckPerms rank prefix of a player.
	 */
	public static String getLuckPermsRankPrefix(@NonNull Player player) {
		if (!Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) return "";
		
		net.luckperms.api.LuckPerms luckPerms = net.luckperms.api.LuckPermsProvider.get();
		net.luckperms.api.model.user.User user = luckPerms.getPlayerAdapter(Player.class).getUser(player);
		net.luckperms.api.cacheddata.CachedMetaData metaData = user.getCachedData().getMetaData();
		String prefix = metaData.getPrefix();
		return prefix != null ? prefix : "";
	}
	
	/**
	 * Sends the end credits screen to a player.
	 */
	public static void sendEndCredits(@NonNull Player player) {
		plugin.getNmsAdapter().sendEndCredits(player);
	}
	
	/**
	 * Vanishes a player.
	 */
	public static void vanish(@NonNull Player player) {
		if (plugin.getVanishListener().players.add(player.getUniqueId())) {
			for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
				onlinePlayer.hidePlayer(player);
			}
		}
	}
	
	/**
	 * Unvanishes a player.
	 */
	public static void unvanish(@NonNull Player player) {
		if (plugin.getVanishListener().players.remove(player.getUniqueId())) {
			for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
				onlinePlayer.showPlayer(player);
			}
		}
	}
	
	/**
	 * @return EntityPlayer from Player
	 */
	public Object getEntityPlayer(@NonNull Player player) {
		return plugin.getNmsAdapter().getEntityPlayer(player);
	}
	
	/**
	 * @return GameProfile from EntityPlayer
	 */
	public GameProfile getProfile(@NonNull Object entityPlayer) {
		return plugin.getNmsAdapter().getProfile(entityPlayer);
	}
	
	/**
	 * Sets a custom skin.
	 */
	public static void setSkin(@NonNull Player player, @NonNull UUID uuid) {
		plugin.getNmsAdapter().setSkin(player, uuid);
	}
	
	/**
	 * Resets the skin back to the original.
	 */
	public static void resetSkin(@NonNull Player player) {
		plugin.getNmsAdapter().resetSkin(player);
	}
	
	/**
	 * Sets a custom name.
	 */
	public static void setName(@NonNull Player player, @NonNull String name) {
		plugin.getNmsAdapter().setName(player, name);
	}
	
	/**
	 * Resets the name back to the original.
	 */
	public static void resetName(@NonNull Player player) {
		plugin.getNmsAdapter().resetName(player);
	}
	
	/**
	 * Forces clients to reload the player's GameProfile.
	 */
	public static void refresh(@NonNull Player player) {
		plugin.getNmsAdapter().refresh(player);
	}
	
	/**
	 * Adds a EntityPlayer.
	 */
	public static void addEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
		plugin.getNmsAdapter().addEntityPlayer(player, entityPlayer);
	}
	
	/**
	 * Removes a EntityPlayer.
	 */
	public static void removeEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
		plugin.getNmsAdapter().removeEntityPlayer(player, entityPlayer);
	}
	
	/**
	 * Spawns a EntityPlayer.
	 */
	public static void spawnEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
		plugin.getNmsAdapter().spawnEntityPlayer(player, entityPlayer);
	}
	
	/**
	 * Connects the player to a proxy server.
	 * via BungeeCord messaging
	 */
	@SneakyThrows
	public static void connectToServer(@NonNull Player player, @NonNull String server) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		
		out.writeUTF("Connect");
		out.writeUTF(server);
		
		player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
	}
}
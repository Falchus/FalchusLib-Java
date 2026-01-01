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
import com.falchus.lib.minecraft.spigot.utils.nms.NmsAdapter;
import com.falchus.lib.minecraft.spigot.utils.nms.NmsProvider;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * Utility class for players.
 */
@UtilityClass
public class PlayerUtils {
	
	private static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	private static final NmsAdapter nms = NmsProvider.get();
	public static final Map<UUID, Property> skins = new HashMap<>();
	public static final Map<UUID, String> names = new HashMap<>();

	/**
	 * Sends a raw NMS packet to a player.
	 */
	public static void sendPacket(@NonNull Player player, @NonNull Object packet) {
		nms.sendPacket(player, packet);
	}
	
	/**
	 * Creates an instance of a packet.
	 */
	public static Object createPacket(@NonNull Class<?> packetClass, Object... constructorArgs) {
		return nms.createPacket(packetClass, constructorArgs);
	}
	
	/**
	 * Sends a title and/or subtitle to a player.
	 */
	public static void sendTitle(@NonNull Player player, String title, String subtitle) {
        nms.sendTitle(player, title, subtitle);
	}
	
	/**
	 * Sends a tablist to a player.
	 */
	public static void sendTablist(@NonNull Player player, List<String> header, List<String> footer, String name) {
		nms.sendTablist(player, header, footer, name);
	}
	
	/**
	 * Plays a sound to a player.
	 */
	public static void playSound(@NonNull Player player, @NonNull Location location, @NonNull Sound sound, float volume, float pitch) {
		nms.playSound(player, location, sound, volume, pitch);
	}
	
	/**
	 * Freezes a player.
	 */
	public static void freeze(@NonNull Player player) {
		plugin.getContexts().getFreezeListener().players.add(player.getUniqueId());
	}
	
	/**
	 * Unfreezes a player.
	 */
	public static void unfreeze(@NonNull Player player) {
		plugin.getContexts().getFreezeListener().players.remove(player.getUniqueId());
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
        nms.sendEndCredits(player);
	}
	
	/**
	 * Vanishes a player.
	 */
	public static void vanish(@NonNull Player player) {
		if (plugin.getContexts().getVanishListener().players.add(player.getUniqueId())) {
			for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
				onlinePlayer.hidePlayer(player);
			}
		}
	}
	
	/**
	 * Unvanishes a player.
	 */
	public static void unvanish(@NonNull Player player) {
		if (plugin.getContexts().getVanishListener().players.remove(player.getUniqueId())) {
			for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
				onlinePlayer.showPlayer(player);
			}
		}
	}
	
	/**
	 * @return EntityPlayer from Player
	 */
	public Object getEntityPlayer(@NonNull Player player) {
		return nms.getEntityPlayer(player);
	}
	
	/**
	 * @return GameProfile from EntityPlayer
	 */
	public GameProfile getProfile(@NonNull Object entityPlayer) {
		return nms.getProfile(entityPlayer);
	}
	
	/**
	 * Sets a custom skin.
	 */
	public static void setSkin(@NonNull Player player, @NonNull UUID uuid) {
		nms.setSkin(player, uuid);
	}
	
	/**
	 * Resets the skin back to the original.
	 */
	public static void resetSkin(@NonNull Player player) {
		nms.resetSkin(player);
	}
	
	/**
	 * Sets a custom name.
	 */
	public static void setName(@NonNull Player player, @NonNull String name) {
		nms.setName(player, name);
	}
	
	/**
	 * Resets the name back to the original.
	 */
	public static void resetName(@NonNull Player player) {
		nms.resetName(player);
	}
	
	/**
	 * Forces clients to reload the player's GameProfile.
	 */
	public static void refresh(@NonNull Player player) {
		nms.refresh(player);
	}
	
	/**
	 * Adds a EntityPlayer.
	 */
	public static void addEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
		nms.addEntityPlayer(player, entityPlayer);
	}
	
	/**
	 * Removes a EntityPlayer.
	 */
	public static void removeEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
		nms.removeEntityPlayer(player, entityPlayer);
	}
	
	/**
	 * Spawns a EntityPlayer.
	 */
	public static void spawnEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
		nms.spawnEntityPlayer(player, entityPlayer);
	}
}
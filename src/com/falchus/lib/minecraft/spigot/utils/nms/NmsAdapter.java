package com.falchus.lib.minecraft.spigot.utils.nms;

import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.falchus.lib.minecraft.spigot.enums.Property;
import com.falchus.lib.minecraft.spigot.enums.Sound;
import com.falchus.lib.minecraft.spigot.utils.builder.NmsPacketBuilder;
import com.mojang.authlib.GameProfile;

import lombok.NonNull;

public interface NmsAdapter {

	String getPackageOb();
	String getPackageObc();
	String getPackageNm();
	String getPackageNms();
	
	Class<?> getChatComponentText();
	
    /**
     * Sets a UUID on the given item via NBT.
     */
	ItemStack setUUID(@NonNull ItemStack item, UUID uuid);
	
    /**
     * Retrieves the UUID stores on the given item.
     */
	UUID getUUID(@NonNull ItemStack item);
	
    /**
     * Removes all NBT tags from the item.
     */
	ItemStack clearNBT(@NonNull ItemStack item);
	
	/**
	 * Sends a raw NMS packet to a player.
	 */
	void sendPacket(@NonNull Player player, @NonNull Object packet);
	
	/**
	 * Creates an instance of a packet.
	 * Use {@link NmsPacketBuilder}
	 */
	Object createPacket(@NonNull Class<?> packetClass, Object... constructorArgs);
	
	/**
	 * Sends a title and/or subtitle to a player.
	 */
	void sendTitle(@NonNull Player player, String title, String subtitle);
	
	/**
	 * Sends a tablist to a player.
	 */
	void sendTablist(@NonNull Player player, List<String> header, List<String> footer, String name);

	/**
	 * Plays a sound to a player.
	 */
	void playSound(@NonNull Player player, @NonNull Location location, @NonNull Sound sound, float volume, float pitch);
	
	/**
	 * Sends the end credits screen to a player.
	 */
	void sendEndCredits(@NonNull Player player);
	
	/**
	 * @return CraftPlayer from Player
	 */
	Object getCraftPlayer(@NonNull Player player);
	
	/**
	 * @return EntityPlayer from Player
	 */
	Object getEntityPlayer(@NonNull Player player);
	
	/**
	 * @return {@link GameProfile} from EntityPlayer
	 */
	GameProfile getProfile(@NonNull Object entityPlayer);
	
	/**
	 * Sets a custom skin.
	 */
	void setSkin(@NonNull Player player, @NonNull UUID uuid);
	
	/**
	 * Resets the skin back to the original.
	 */
	void resetSkin(@NonNull Player player);
	
	/**
	 * Sets a custom name.
	 */
	void setName(@NonNull Player player, @NonNull String name);
	
	/**
	 * Resets the name back to the original.
	 */
	void resetName(@NonNull Player player);
	
	/**
	 * Forces clients to reload the player's GameProfile.
	 */
	void refresh(@NonNull Player player);
	
	/**
	 * Adds a EntityPlayer.
	 */
	void addEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer);
	
	/**
	 * Removes a EntityPlayer.
	 */
	void removeEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer);
	
	/**
	 * Spawns a EntityPlayer.
	 */
	void spawnEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer);
	
	/**
	 * @return PropertyManager
	 */
	Object getPropertyManager();
	
	/**
	 * Saves the properties.
	 */
	void saveProperties();
	
	/**
	 * Set a property value.
	 */
	void setProperty(@NonNull Property property, @NonNull Object value);

	/**
	 * @return MinecraftServer
	 */
	Object getMinecraftServer();
	
	/**
	 * @return e.g. "1.8.8"
	 */
	String getVersion();
	
	/**
	 * @return BiomeBase[] from a World
	 */
	Object[] getWorldBiomes(@NonNull World world);
	
	/**
	 * @return BiomeBase from a Biome
	 */
	Object getNmsBiome(Biome biome);
	
	/**
	 * @return id from a Biome
	 */
	int getBiomeId(Biome biome);
}

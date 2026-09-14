package com.falchus.lib.minecraft.spigot.utils.version;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.falchus.lib.minecraft.spigot.enums.GameRule;
import com.falchus.lib.minecraft.spigot.enums.Sound;
<<<<<<< HEAD
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.Component;
import com.falchus.lib.minecraft.spigot.wrapper.world.AxisAlignedBB;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.ScoreboardTeam;
=======
import com.falchus.lib.minecraft.spigot.wrapper.world.WrappedAxisAlignedBB;
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
import com.mojang.authlib.GameProfile;

import lombok.NonNull;

public interface IVersionAdapter {

	String getPackageOb();
	String getPackageObc();
	String getPackageNm();
	String getPackageNms();
	
	Class<?> getEntityPlayer();
	Field getEntityPlayer_playerConnection();
	Field getPlayerConnection_networkManager();
	Field getNetworkManager_channel();
	Class<?> getPlayerInteractManager();
	Class<?> getEntity();
	Class<?> getEntityLiving();
	Class<?> getWorld();
	Class<?> getMinecraftServer();
	Class<?> getWorldServer();
	Class<?> getIScoreboardCriteria$enumScoreboardHealthDisplay();
	
	Method entity_setCustomName();
	Method scoreboard_registerObjective();
	Method scoreboard_unregisterObjective();
	
	Object createChatComponentText(@NonNull String text);
	Object createPacketOutScoreboardTeam(@NonNull Set<String> names, @NonNull ScoreboardTeam team, int mode, String playerName);
	Object createPacketOutPlayerListHeaderFooter(@NonNull Set<String> names, @NonNull String header, @NonNull String footer);
	Object createClientboundSetTitleTextPacket(@NonNull Set<String> names, @NonNull Component text);
	Object createClientboundSetSubtitleTextPacket(@NonNull Set<String> names, @NonNull Component text);
	
	Object getEntity(@NonNull Entity entity);
	
<<<<<<< HEAD
	Object getEntityLiving(@NonNull LivingEntity entity);
=======
	/**
	 * @return {@link Entity}
	 */
	Entity getBukkitEntity(@NonNull Object entity);
	
	/**
	 * @return {@link WrappedAxisAlignedBB}
	 */
	WrappedAxisAlignedBB getBoundingBox(@NonNull Entity entity);
	
	/**
	 * @return {@link WrappedAxisAlignedBB}
	 */
	WrappedAxisAlignedBB modifyBoundingBox(@NonNull WrappedAxisAlignedBB axisAlignedBB, double minX, double minY, double minZ, double maxX, double maxY, double maxZ);
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	
	/**
	 * @return absorption from a {@link Damageable} entity.
	 */
	double getAbsorption(@NonNull Damageable entity);
	
	/**
	 * Sets absorption.
	 */
	void setAbsorption(@NonNull Damageable entity, double absorption);
	
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
	 * Sends a tablist to a player.
	 */
	void sendTablist(@NonNull Player player, List<String> header, List<String> footer, String name);
	
	/**
	 * Sends a bossbar to a player.
	 */
	void sendBossbar(@NonNull Player player, @NonNull String title, double progress);
	
	/**
	 * Removes a bossbar from a player.
	 */
	void removeBossbar(@NonNull Player player);

	/**
	 * Sends a actionbar to a player.
	 */
	void sendActionbar(@NonNull Player player, @NonNull String message);
	
	/**
	 * Sends a nametag for a player.
	 */
	void sendNametag(@NonNull Player player, @NonNull String prefix, @NonNull String suffix, int sort);
	
	/**
	 * Removes a nametag from a player.
	 */
	void removeNametag(@NonNull Player player);
	
	/**
	 * Plays a sound to a player.
	 */
	void playSound(@NonNull Player player, @NonNull Location location, @NonNull Sound sound, float volume, float pitch);
	
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
	 * @return ping from a player.
	 */
	int getPing(@NonNull Player player);
	
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
	 * @return MinecraftServer
	 */
	Object getMcServer();
	
	/**
	 * @return {@link Server}
	 */
	Server getBukkitServer();
	
	/**
	 * @return e.g. "1.8.8"
	 */
	String getVersion();
	
	/**
	 * @return recent TPS
	 */
	double[] getRecentTps();
	
	/**
	 * Sets a game rule for the given world.
	 */
	void setGameRule(@NonNull World world, @NonNull GameRule gameRule, @NonNull String value);
	
	/**
	 * @return BiomeBase[]
	 */
	Object[] getBiomes();
	
	/**
	 * @return id from a Biome
	 */
	int getBiomeId(Biome biome);
	
	/**
	 * @return BiomeBase from a Biome
	 */
	Object getNmsBiome(Biome biome);
	
	/**
	 * @return World from a {@link World}
	 */
	Object getWorld(@NonNull World world);
	
	/**
<<<<<<< HEAD
	 * @return WorldServer from a {@link World}
=======
	 * @return {@link List}
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
	 */
<<<<<<< HEAD
	Object getWorldServer(@NonNull World world);
	
	/**
	 * @return {@link List}
	 */
	List<AxisAlignedBB> getCollidingBlocks(@NonNull World world, @NonNull AxisAlignedBB axisAlignedBB);
=======
	List<WrappedAxisAlignedBB> getCollidingBlocks(@NonNull World world, @NonNull WrappedAxisAlignedBB axisAlignedBB);
>>>>>>> branch 'master' of https://github.com/Falchus/FalchusLib-Java.git
}

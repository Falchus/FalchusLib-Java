package com.falchus.lib.minecraft.spigot.utils.version;

import com.falchus.lib.minecraft.spigot.enums.Sound;
import com.mojang.authlib.GameProfile;
import lombok.NonNull;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

public interface IVersionAdapter {

    String getPackageOb();

    String getPackageObc();

    String getPackageNm();

    String getPackageNms();

    Class<?> getBlockPosition();

    Class<?> getEntityPlayer();

    Field getEntityPlayer_playerConnection();

    Field getPlayerConnection_networkManager();

    Field getNetworkManager_channel();

    Class<?> getPlayerInteractManager();

    Method getEntity_setLocation();

    Method getEntity_setInvisible();

    Class<?> getWorld();

    Class<?> getMinecraftServer();

    Class<?> getWorldServer();

    Object createChatComponentText(@NonNull String text);

    /**
     * @return {@link Entity}
     */
    Entity getBukkitEntity(@NonNull Object entity);

    /**
     * Sets yaw and pitch.
     */
    void setYawPitch(@NonNull Object entity, float yaw, float pitch);

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
     * Sends a title and/or subtitle to a player.
     */
    void sendTitle(@NonNull Player player, String title, String subtitle);

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
    void sendNametag(@NonNull Player player, @NonNull String prefix, @NonNull String suffix);

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
     * @return Player.Spigot from Player
     */
    Object getPlayerSpigot(@NonNull Player player);

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
    Object getBukkitServer();

    /**
     * @return e.g. "1.8.8"
     */
    String getVersion();

    /**
     * @return recent TPS
     */
    double[] getRecentTps();

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

    /**
     * @return WorldServer from a World
     */
    Object getWorldServer(World world);
}

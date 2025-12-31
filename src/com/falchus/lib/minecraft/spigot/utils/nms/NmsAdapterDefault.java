package com.falchus.lib.minecraft.spigot.utils.nms;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;
import com.falchus.lib.minecraft.spigot.utils.builder.GameProfileBuilder;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import lombok.Getter;
import lombok.NonNull;

/**
 * Default adapter for all versions using reflection. (tested with 1.8.8)
 * We override methods in newer versions only if needed.
 */
@Getter
public class NmsAdapterDefault extends AbstractNmsAdapter {
	
    private Class<?> craftItemStack;
    private Class<?> nbtTagCompound;
    private Method craftItemStack_asNMSCopy;
    private Method craftItemStack_asBukkitCopy;
    private Method nmsItemStack_getTag;
    private Method nmsItemStack_setTag;
    private Method nmsItemStack_hasTag;
    private Method nbtTagCompound_setString;
    private Method nbtTagCompound_remove;
    private Method nbtTagCompound_hasKey;
    private Method nbtTagCompound_getString;
	
    private Class<?> packet;
    private Class<?> entityPlayer;
    private Field entityPlayer_playerConnection;
    private Class<?> playerConnection;
    private Method playerConnection_sendPacket;
    private Class<?> craftPlayer;
    private Method craftPlayer_getHandle;
    private Class<?> entityHuman;
    private Field entityPlayer_profile;
    private Class<?> packetPlayOutPlayerInfo;
    private Class<?> packetPlayOutNamedEntitySpawn;
    private Class<?> packetPlayOutEntityTeleport;
    private Class<?> enumPlayerInfoActionClass;
    private Object enumPlayerInfoAction_UPDATE_DISPLAY_NAME;
    private Object enumPlayerInfoAction_ADD_PLAYER;
    private Object enumPlayerInfoAction_REMOVE_PLAYER;
    private Class<?> chatComponentText;
    private Class<?> packetPlayOutTitle;
    private Class<?> enumTitleActionClass;
    private Object enumTitleAction_TITLE;
    private Object enumTitleAction_SUBTITLE;

	private Class<?> dedicatedServer;
	private Field dedicatedServer_propertyManager;
    private Class<?> propertyManager;
    private Method propertyManager_saveProperties;
    private Method propertyManager_setProperty;
	
    private Class<?> craftServer;
    private Method craftServer_getServer;
    private Class<?> minecraftServer;
    private Method minecraftServer_getVersion;
	
    private Class<?> biomeBase;
    private Field biomeBase_biomes;
    private Method biomeBase_getBiome;
    
    public NmsAdapterDefault() {
    	try {
            craftItemStack = Class.forName(packageObc + "inventory.CraftItemStack");
            nmsItemStack = Class.forName(packageNms + "ItemStack");
            nbtTagCompound = Class.forName(packageNms + "NBTTagCompound");
            craftItemStack_asNMSCopy = craftItemStack.getMethod("asNMSCopy", ItemStack.class);
            craftItemStack_asBukkitCopy = craftItemStack.getMethod("asBukkitCopy", nmsItemStack);
            nmsItemStack_getTag = nmsItemStack.getMethod("getTag");
            nmsItemStack_setTag = nmsItemStack.getMethod("setTag", nbtTagCompound);
            nmsItemStack_hasTag = nmsItemStack.getMethod("hasTag");
            nbtTagCompound_setString = nbtTagCompound.getMethod("setString", String.class, String.class);
            nbtTagCompound_remove = nbtTagCompound.getMethod("remove", String.class);
            nbtTagCompound_hasKey = nbtTagCompound.getMethod("hasKey", String.class);
            nbtTagCompound_getString = nbtTagCompound.getMethod("getString", String.class);
    		
            packet = Class.forName(packageNms + "Packet");
            entityPlayer = Class.forName(packageNms + "EntityPlayer");
            entityPlayer_playerConnection = entityPlayer.getField("playerConnection");
            playerConnection = Class.forName(packageNms + "PlayerConnection");
            playerConnection_sendPacket = playerConnection.getMethod("sendPacket", packet);
            craftPlayer = Class.forName(packageObc + "entity.CraftPlayer");
            craftPlayer_getHandle = craftPlayer.getMethod("getHandle");
            entityHuman = entityPlayer.getSuperclass();
        	entityPlayer_profile = entityHuman.getDeclaredField("bH");
            entityPlayer_profile.setAccessible(true);
            packetPlayOutPlayerInfo = Class.forName(packageNms + "PacketPlayOutPlayerInfo");
            packetPlayOutNamedEntitySpawn = Class.forName(packageNms + "PacketPlayOutNamedEntitySpawn");
            packetPlayOutEntityTeleport = Class.forName(packageNms + "PacketPlayOutEntityTeleport");
            enumPlayerInfoActionClass = Class.forName(packageNms + "PacketPlayOutPlayerInfo$EnumPlayerInfoAction");
            enumPlayerInfoAction_UPDATE_DISPLAY_NAME = enumPlayerInfoActionClass.getField("UPDATE_DISPLAY_NAME").get(null);
            enumPlayerInfoAction_ADD_PLAYER = enumPlayerInfoActionClass.getField("ADD_PLAYER").get(null);
            enumPlayerInfoAction_REMOVE_PLAYER = enumPlayerInfoActionClass.getField("REMOVE_PLAYER").get(null);
            chatComponentText = Class.forName(packageNms + "ChatComponentText");
            packetPlayOutTitle = Class.forName(packageNms + "PacketPlayOutTitle");
            enumTitleActionClass = Class.forName(packageNms + "PacketPlayOutTitle$EnumTitleAction");
            enumTitleAction_TITLE = enumTitleActionClass.getField("TITLE").get(null);
            enumTitleAction_SUBTITLE = enumTitleActionClass.getField("SUBTITLE").get(null);
    		
            dedicatedServer = Class.forName(packageNms + "DedicatedServer");
            dedicatedServer_propertyManager = dedicatedServer.getDeclaredField("propertyManager");
            dedicatedServer_propertyManager.setAccessible(true);
            propertyManager = Class.forName(packageNms + "PropertyManager");
            propertyManager_saveProperties = propertyManager.getMethod("savePropertiesFile");
            propertyManager_setProperty = propertyManager.getMethod("setProperty", String.class, Object.class);
    		
            craftServer = Class.forName(packageObc + "CraftServer");
            craftServer_getServer = craftServer.getMethod("getServer");
            minecraftServer = Class.forName(packageNms + "MinecraftServer");
            minecraftServer_getVersion = minecraftServer.getMethod("getVersion");
    		
    		biomeBase = Class.forName(packageNms + "BiomeBase");
            biomeBase_biomes = biomeBase.getDeclaredField("biomes");
            biomeBase_biomes.setAccessible(true);
            biomeBase_getBiome = biomeBase.getMethod("getBiome", int.class);
    	} catch (Exception e) {
    		throw new IllegalStateException("Failed to initialize " + getClass().getSimpleName(), e);
    	}
    }
    
    @Override
    public ItemStack setUUID(@NonNull ItemStack item, UUID uuid) {
    	try {
    		Object nmsItem = craftItemStack_asNMSCopy.invoke(null, item);
    		if (nmsItem == null) return item;
    		
    		Object tag = (boolean) nmsItemStack_hasTag.invoke(nmsItem) ? nmsItemStack_getTag.invoke(nmsItem) : nbtTagCompound.getConstructor().newInstance();
    		if (uuid == null) {
    			nbtTagCompound_remove.invoke(tag, "UUID");
    		} else {
    			nbtTagCompound_setString.invoke(tag, "UUID", uuid.toString());
    		}
            nmsItemStack_setTag.invoke(nmsItem, tag);
            return (ItemStack) craftItemStack_asBukkitCopy.invoke(null, nmsItem);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public UUID getUUID(@NonNull ItemStack item) {
    	try {
    		Object nmsItem = craftItemStack_asNMSCopy.invoke(null, item);
    		if (nmsItem == null) return null;
    		
    		if ((boolean) nmsItemStack_hasTag.invoke(nmsItem)) {
    			Object tag = nmsItemStack_getTag.invoke(nmsItem);
    			if ((boolean) nbtTagCompound_hasKey.invoke(tag, "UUID")) {
    				return UUID.fromString((String) nbtTagCompound_getString.invoke(tag, "UUID"));
    			}
    		}
    		return null;
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public ItemStack clearNBT(@NonNull ItemStack item) {
    	try {
    		Object nmsItem = craftItemStack_asNMSCopy.invoke(null, item);
    		if (nmsItem == null) return item;
    		
    		Object tag = nbtTagCompound.getConstructor().newInstance();
    		nmsItemStack_setTag.invoke(nmsItem, tag);
    		
    		return (ItemStack) craftItemStack_asBukkitCopy.invoke(null, nmsItem);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void sendPacket(@NonNull Player player, @NonNull Object packet) {
    	try {
    		Object entityPlayer = getEntityPlayer(player);
    		Object connection = entityPlayer_playerConnection.get(entityPlayer);
    		playerConnection_sendPacket.invoke(connection, packet);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public Object createPacket(@NonNull Class<?> packetClass, Object... constructorArgs) {
    	try {
    		for (Constructor<?> constructor : packetClass.getConstructors()) {
    			if (constructor.getParameterCount() == constructorArgs.length) {
    				return constructor.newInstance(constructorArgs);
    			}
    		}
    		throw new IllegalArgumentException("No matching constructor found for packet: " + packetClass.getName());
    	} catch (Exception e) {
            throw new RuntimeException("Failed to create packet for class " + packetClass.getName(), e);
        }
    }

    @Override
    public void sendTitle(@NonNull Player player, String title, String subtitle) {
    	try {
    		if (title != null && !title.isEmpty()) {
    			Object component = chatComponentText.getConstructor(String.class).newInstance(title);
    			Object titlePacket = packetPlayOutTitle
    					.getConstructor(enumTitleAction_TITLE.getClass(), component.getClass())
    					.newInstance(enumTitleAction_TITLE, component);
    			sendPacket(player, titlePacket);
    		}
    		
    		if (subtitle != null && !subtitle.isEmpty()) {
    			Object component = chatComponentText.getConstructor(String.class).newInstance(subtitle);
    			Object subtitlePacket = packetPlayOutTitle
    					.getConstructor(enumTitleAction_SUBTITLE.getClass(), component.getClass())
    					.newInstance(enumTitleAction_SUBTITLE, component);
    			sendPacket(player, subtitlePacket);
    		}
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void sendEndCredits(@NonNull Player player) {
    	try {
    		Object packet = Class.forName(packageNms + "PacketPlayOutGameStateChange")
    				.getConstructor(int.class, float.class).newInstance(4, 0.0F);
    		sendPacket(player, packet);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public Object getCraftPlayer(@NonNull Player player) {
    	return craftPlayer.cast(player);
    }
    
    @Override
    public Object getEntityPlayer(@NonNull Player player) {
    	try {
    		Object craftPlayer = getCraftPlayer(player);
    		return craftPlayer_getHandle.invoke(craftPlayer);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public GameProfile getProfile(@NonNull Object entityPlayer) {
    	try {
            return (GameProfile) entityPlayer_profile.get(entityPlayer);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void setSkin(@NonNull Player player, @NonNull UUID uuid) {
    	try {
    		Object entityPlayer = getEntityPlayer(player);
    		GameProfile profile = getProfile(entityPlayer);
    		
    		Collection<Property> textures = profile.getProperties().get("textures");
    		if (!PlayerUtils.skins.containsKey(player.getUniqueId()) && textures != null && !textures.isEmpty()) {
    		    PlayerUtils.skins.put(player.getUniqueId(), textures.iterator().next());
    		}
			
			GameProfile skinProfile = GameProfileBuilder.fetch(uuid);
			
			profile.getProperties().removeAll("textures");
			for (Property property : skinProfile.getProperties().get("textures")) {
				profile.getProperties().put("textures", property);
			}
			
			refresh(player);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void resetSkin(@NonNull Player player) {
    	try {
    		Object entityPlayer = getEntityPlayer(player);
    		GameProfile profile = getProfile(entityPlayer);

	        Property original = PlayerUtils.skins.get(player.getUniqueId());
	        if (original != null) {
	            profile.getProperties().removeAll("textures");
	            profile.getProperties().put("textures", original);

	            refresh(player);

	            PlayerUtils.skins.remove(player.getUniqueId());
	        }
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void setName(@NonNull Player player, @NonNull String name) {
    	try {
    		Object entityPlayer = getEntityPlayer(player);
    		GameProfile profile = getProfile(entityPlayer);
    		
    		PlayerUtils.names.put(player.getUniqueId(), profile.getName());

	        player.setCustomName(name);
	        player.setCustomNameVisible(true);
	        player.setDisplayName(name);
	        
	        Field nameField = GameProfile.class.getDeclaredField("name");
	        nameField.setAccessible(true);
	        nameField.set(profile, name);

	        refresh(player);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void resetName(@NonNull Player player) {
    	try {
	        String original = PlayerUtils.names.get(player.getUniqueId());
	        if (original == null) return;
	        
	        Object entityPlayer = getEntityPlayer(player);
    		GameProfile profile = getProfile(entityPlayer);
    		
	        player.setCustomName(original);
	        player.setCustomNameVisible(true);
	        player.setDisplayName(original);

	        Field nameField = GameProfile.class.getDeclaredField("name");
	        nameField.setAccessible(true);
	        nameField.set(profile, original);

	        refresh(player);

	        PlayerUtils.names.remove(player.getUniqueId());
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void refresh(@NonNull Player player) {
    	try {
    		Object entityPlayer = getEntityPlayer(player);
    		
    		Object update = enumPlayerInfoAction_UPDATE_DISPLAY_NAME;
    		Object packet = packetPlayOutPlayerInfo
    				.getConstructor(update.getClass(), Iterable.class)
    				.newInstance(update, Collections.singletonList(entityPlayer));
    	    for (Player online : Bukkit.getOnlinePlayers()) {
    	        sendPacket(online, packet);
    	    }
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void addEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
    	try {
    		Object add = enumPlayerInfoAction_ADD_PLAYER;
    		Object packet = packetPlayOutPlayerInfo
    				.getConstructor(add.getClass(), Iterable.class)
    				.newInstance(add, Collections.singletonList(entityPlayer));
            sendPacket(player, packet);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void removeEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
    	try {
    		Object remove = enumPlayerInfoAction_REMOVE_PLAYER;
    		Object packet = packetPlayOutPlayerInfo
    				.getConstructor(remove.getClass(), Iterable.class)
    				.newInstance(remove, Collections.singletonList(entityPlayer));
            sendPacket(player, packet);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void spawnEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
    	try {
    		addEntityPlayer(player, entityPlayer);
    		
    		Object spawn = packetPlayOutNamedEntitySpawn
    				.getConstructor(entityPlayer.getClass())
    				.newInstance(entityPlayer);
    		sendPacket(player, spawn);
    		
    		Object teleport = packetPlayOutEntityTeleport
    				.getConstructor(entityPlayer.getClass())
    				.newInstance(entityPlayer);
    		sendPacket(player, teleport);
    		
    		Bukkit.getScheduler().runTask(FalchusLibMinecraftSpigot.getInstance(), () -> removeEntityPlayer(player, entityPlayer));
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public Object getPropertyManager() {
    	try {
    		Object server = getMinecraftServer();
    		if (!dedicatedServer.isInstance(server)) {
    			throw new IllegalStateException("Server is not a DedicatedServer instance");
    		}
    		return dedicatedServer_propertyManager.get(server);
    	} catch (Exception e) {
    		throw new RuntimeException(e);
    	}
    }
    
    @Override
    public void saveProperties() {
        try {
            Object propertyManager = getPropertyManager();
            propertyManager_saveProperties.invoke(propertyManager);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void setProperty(@NonNull com.falchus.lib.minecraft.spigot.enums.Property property, @NonNull Object value) {
        try {
            Object propertyManager = getPropertyManager();
            propertyManager_setProperty.invoke(propertyManager, property.getKey(), value);
            saveProperties();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public Object getMinecraftServer() {
    	try {
    		Object server = craftServer.cast(Bukkit.getServer());
    		return craftServer_getServer.invoke(server);
    	} catch (Exception e) {
    		throw new RuntimeException(e);
    	}
    }
    
    @Override
    public String getVersion() {
    	try {
    		Object server = getMinecraftServer();
    		return (String) minecraftServer_getVersion.invoke(server);
    	} catch (Exception e) {
    		throw new RuntimeException(e);
    	}
    }
    
    @Override
    public Object[] getWorldBiomes(@NonNull World world) {
    	try {
            return (Object[]) biomeBase_biomes.get(null);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public Object getNmsBiome(Biome biome) {
    	try {
    		if (biome == null) return biomeBase_getBiome.invoke(null, 0);
    		return biomeBase_getBiome.invoke(null, biome.ordinal());
    	} catch (Exception e) {
    		throw new RuntimeException(e);
		}
    }
    
    @Override
    public int getBiomeId(Biome biome) {
        Object target = getNmsBiome(biome);
        try {
            Object[] biomes = (Object[]) biomeBase_biomes.get(null);
            for (int i = 0; i < biomes.length; i++) {
                if (biomes[i] == target) {
                	return i;
                }
            }
            return 1;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

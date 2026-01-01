package com.falchus.lib.minecraft.spigot.utils.nms;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
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
import com.falchus.lib.minecraft.spigot.utils.builder.NmsPacketBuilder;
import com.falchus.lib.utils.ReflectionUtils;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PROTECTED)
public abstract class AbstractNmsAdapter implements NmsAdapter {

	@Getter String packageOb = "org.bukkit.";
	@Getter String packageObc = packageOb + "craftbukkit.";
	@Getter String packageNm = "net.minecraft.";
	@Getter String packageNms = packageNm + "server.";
	
	Class<?> craftItemStack;
	Class<?> nmsItemStack;
	Class<?> nbtTagCompound;
	Method craftItemStack_asNMSCopy;
	Method craftItemStack_asBukkitCopy;
	Method nmsItemStack_getTag;
	Method nmsItemStack_setTag;
	Method nmsItemStack_hasTag;
	Method nbtTagCompound_setString;
	Method nbtTagCompound_remove;
	Method nbtTagCompound_hasKey;
	Method nbtTagCompound_getString;
	
	Class<?> packet;
    Class<?> entityPlayer;
    Field entityPlayer_playerConnection;
    Class<?> playerConnection;
    Method playerConnection_sendPacket;
    Class<?> craftPlayer;
    Method craftPlayer_getHandle;
    Class<?> entityHuman;
    Field entityPlayer_profile;
    Class<?> enumPlayerInfo$Action;
    Object enumPlayerInfo$Action_UPDATE_DISPLAY_NAME;
    Object enumPlayerInfo$Action_ADD_PLAYER;
    @Getter Class<?> chatComponentText;

    Class<?> dedicatedServer;
    Field dedicatedServer_propertyManager;
    Class<?> propertyManager;
    Method propertyManager_saveProperties;
    Method propertyManager_setProperty;
	
    Class<?> craftServer;
    Method craftServer_getServer;
    Class<?> minecraftServer;
    Method minecraftServer_getVersion;
	
    Class<?> biomeBase;
    Field biomeBase_biomes;
    Method biomeBase_getBiome;
	
	public AbstractNmsAdapter() {
		try {
    		String version;
            String packageName = Bukkit.getServer().getClass().getPackageName();
            String[] parts = packageName.split("\\.");
            version = parts.length >= 4 ? parts[3] : "Unknown";
            
    		packageObc = packageObc + (!version.equals("Unknown") ? version + "." : "");
    		packageNms = packageNms + (!version.equals("Unknown") ? version + "." : "");
    		
            craftItemStack = ReflectionUtils.getFirstClass(
            	packageObc + "inventory.CraftItemStack"
            );
            nmsItemStack = ReflectionUtils.getFirstClass(
            	packageNms + "ItemStack",
            	packageNm + "world.item.ItemStack"
            );
            nbtTagCompound = ReflectionUtils.getFirstClass(
            	packageNms + "NBTTagCompound",
            	packageNm + "nbt.CompoundTag"
            );
            craftItemStack_asNMSCopy = ReflectionUtils.getMethod(craftItemStack, "asNMSCopy", ItemStack.class);
            craftItemStack_asBukkitCopy = ReflectionUtils.getMethod(craftItemStack, "asBukkitCopy", nmsItemStack);
            nmsItemStack_getTag = ReflectionUtils.getMethod(nmsItemStack, "getTag");
            nmsItemStack_setTag = ReflectionUtils.getMethod(nmsItemStack, "setTag", nbtTagCompound);
            nmsItemStack_hasTag = ReflectionUtils.getMethod(nmsItemStack, "hasTag");
            nbtTagCompound_setString = ReflectionUtils.getMethod(nbtTagCompound, "setString", String.class, String.class);
            nbtTagCompound_remove = ReflectionUtils.getMethod(nbtTagCompound, "remove", String.class);
            nbtTagCompound_hasKey = ReflectionUtils.getFirstMethod(nbtTagCompound, Arrays.asList(String.class),
            	"hasKey",
            	"contains"
            );
            nbtTagCompound_getString = ReflectionUtils.getMethod(nbtTagCompound, "getString", String.class);
    		
            packet = ReflectionUtils.getFirstClass(
            	packageNms + "Packet",
            	packageNm + "network.protocol.Packet"
            );
            entityPlayer = ReflectionUtils.getFirstClass(
            	packageNms + "EntityPlayer",
            	packageNms + "level.EntityPlayer"
            );
            entityPlayer_playerConnection = ReflectionUtils.getFirstField(entityPlayer,
            	"playerConnection",
            	"connection"
            );
            playerConnection = ReflectionUtils.getFirstClass(
            	packageNms + "PlayerConnection",
            	packageNms + "network.ServerPlayerConnection"
            );
            playerConnection_sendPacket = ReflectionUtils.getFirstMethod(playerConnection, Arrays.asList(packet),
            	"sendPacket",
            	"send"
            );
            craftPlayer = ReflectionUtils.getClass(packageObc + "entity.CraftPlayer");
            craftPlayer_getHandle = ReflectionUtils.getMethod(craftPlayer, "getHandle");
            entityHuman = entityPlayer.getSuperclass();
            entityPlayer_profile = ReflectionUtils.getFirstField(entityHuman,
            	"bH",
            	"gameProfile"
            );
            enumPlayerInfo$Action = ReflectionUtils.getFirstClass(
            	packageNms + "PacketPlayOutPlayerInfo$EnumPlayerInfoAction",
            	packageNm + "network.protocol.game.ClientboundPlayerInfoUpdatePacket$Action"
            );
            enumPlayerInfo$Action_UPDATE_DISPLAY_NAME = ReflectionUtils.getField(enumPlayerInfo$Action, "UPDATE_DISPLAY_NAME").get(null);
            enumPlayerInfo$Action_ADD_PLAYER = ReflectionUtils.getField(enumPlayerInfo$Action, "ADD_PLAYER").get(null);
            chatComponentText = ReflectionUtils.getFirstClass(
            	packageNms + "ChatComponentText",
            	packageNm + "network.chat.IChatBaseComponent"
            );
    		
            dedicatedServer = ReflectionUtils.getFirstClass(
            	packageNms + "DedicatedServer",
            	packageNms + "dedicated.DedicatedServer"
            );
            dedicatedServer_propertyManager = ReflectionUtils.getField(dedicatedServer, "propertyManager");
            propertyManager = ReflectionUtils.getFirstClass(
            	packageNms + "PropertyManager",
            	packageNms + "dedicated.PropertyManager"
            );
            propertyManager_saveProperties = ReflectionUtils.getMethod(propertyManager, "savePropertiesFile");
            propertyManager_setProperty = ReflectionUtils.getMethod(propertyManager, "setProperty", String.class, Object.class);
    		
            craftServer = ReflectionUtils.getClass(packageObc + "CraftServer");
            craftServer_getServer = ReflectionUtils.getMethod(craftServer, "getServer");
            minecraftServer = ReflectionUtils.getClass(packageNms + "MinecraftServer");
            minecraftServer_getVersion = ReflectionUtils.getMethod(minecraftServer, "getVersion");
    		
    		biomeBase = ReflectionUtils.getFirstClass(
    			packageNms + "BiomeBase",
    			packageNm + "world.level.biome.BiomeBase"
    		);
            biomeBase_biomes = ReflectionUtils.getField(biomeBase, "biomes");
            biomeBase_getBiome = ReflectionUtils.getMethod(biomeBase, "getBiome", int.class);
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
    public void sendEndCredits(@NonNull Player player) {
    	try {
    		Object packet = new NmsPacketBuilder(
    			packageNms + "PacketPlayOutGameStateChange",
    			packageNm + "network.protocol.game.ClientboundGameEventPacket"
    		).withArgs(4, 0.0F).build();
    		sendPacket(player, packet);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void refresh(@NonNull Player player) {
    	try {
    		Object entityPlayer = getEntityPlayer(player);
    		
    		Object update = enumPlayerInfo$Action_UPDATE_DISPLAY_NAME;
    		Object packet = new NmsPacketBuilder(
				packageNms + "PacketPlayOutPlayerInfo",
				packageNm + "network.protocol.game.ClientboundPlayerInfoUpdatePacket"
			).withArgs(update, Collections.singletonList(entityPlayer)).build();
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
    		Object add = enumPlayerInfo$Action_ADD_PLAYER;
    		Object packet = new NmsPacketBuilder(
				packageNms + "PacketPlayOutPlayerInfo",
				packageNm + "network.protocol.game.ClientboundPlayerInfoUpdatePacket"
			).withArgs(add, Collections.singletonList(entityPlayer)).build();
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
	        
	        Field nameField = ReflectionUtils.getField(GameProfile.class, "name");
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

	        Field nameField = ReflectionUtils.getField(GameProfile.class, "name");
	        nameField.setAccessible(true);
	        nameField.set(profile, original);

	        refresh(player);

	        PlayerUtils.names.remove(player.getUniqueId());
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void spawnEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
    	try {
    		addEntityPlayer(player, entityPlayer);
    		
    		Object spawn = new NmsPacketBuilder(
    			packageNms + "PacketPlayOutNamedEntitySpawn",
    			packageNm + "network.protocol.game.ClientboundAddPlayerPacket"
    		).withArgs(entityPlayer).build();
    		sendPacket(player, spawn);
    		
    		Object teleport = new NmsPacketBuilder(
    			packageNms + "PacketPlayOutEntityTeleport",
    			packageNm + "network.protocol.game.ClientboundPlayerPositionPacket"
    		).withArgs(entityPlayer).build();
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
    		int id;
    		if (biome == null) {
    			id = 1;
    		} else {
    			switch (biome.name()) {
					case "BEACH": id = 16; break;
					
					case "BIRCH_FOREST":
					case "BIRCH_FOREST_MOUNTAINS":
						id = 27;
						break;
						
	    			case "BIRCH_FOREST_HILLS":
	    			case "BIRCH_FOREST_HILLS_MOUNTAINS":
	    				id = 28;
	    				break;
	    				
	    			case "COLD_BEACH": id = 26; break;
	    			case "COLD_TAIGA": id = 30; break;
	    			
	    			case "COLD_TAIGA_HILLS":
	    			case "COLD_TAIGA_MOUNTAINS":
	    				id = 31;
	    				break;
	    				
	    			case "DEEP_OCEAN": id = 24; break;
	    			case "DESERT": id = 2; break;
	    			
	    			case "DESERT_HILLS":
	    			case "DESERT_MOUNTAINS":
	    				id = 17;
	    				break;
	    				
	    			case "EXTREME_HILLS":
	    			case "EXTREME_HILLS_MOUNTAINS":
	    				id = 3;
	    				break;
	    				
	    			case "EXTREME_HILLS_PLUS":
	    			case "EXTREME_HILLS_PLUS_MOUNTAINS":
	    				id = 20;
	    				break;
	    				
	    			case "FOREST":
	    			case "FLOWER_FOREST":
	    				id = 4;
	    				break;
	    				
	    			case "FOREST_HILLS": id = 18; break;
	    			case "FROZEN_OCEAN": id = 10; break;
	    			case "FROZEN_RIVER": id = 11; break;
	    			case "HELL": id = 8; break;
	    			case "ICE_MOUNTAINS": id = 13; break;
	    			
	    			case "ICE_PLAINS":
	    			case "ICE_PLAINS_SPIKES":
	    				id = 12;
	    				break;
	    				
	    			case "JUNGLE": id = 21; break;
	    			
	    			case "JUNGLE_EDGE":
	    			case "JUNGLE_EDGE_MOUNTAINS":
	    				id = 23;
	    				break;
	    				
	    			case "JUNGLE_HILLS":
	    			case "JUNGLE_MOUNTAINS":
	    				id = 22;
	    				break;
	    				
	    			case "MEGA_TAIGA": id = 32; break;
	    			case "MEGA_TAIGA_HILLS": id = 33; break;
	    			
	    			case "MESA":
	    			case "MESA_BRYCE":
	    			case "MESA_PLATEAU":
	    			case "MESA_PLATEAU_FOREST":
	    			case "MESA_PLATEAU_FOREST_MOUNTAINS":
	    			case "MESA_PLATEAU_MOUNTAINS":
	    				id = 37;
	    				break;
	    				
	    			case "MUSHROOM_ISLAND": id = 14; break;
	    			case "MUSHROOM_SHORE": id = 15; break;
	    			case "OCEAN": id = 0; break;
	    			
	    			case "PLAINS":
	    			case "SUNFLOWER_PLAINS":
	    				id = 1;
	    				break;
	    				
	    			case "RIVER": id = 7; break;
	    			
	    			case "ROOFED_FOREST":
	    			case "ROOFED_FOREST_MOUNTAINS":
	    				id = 29;
	    				break;
	    				
	    			case "SAVANNA":
	    			case "SAVANNA_MOUNTAINS":
	    				id = 35;
	    				break;
	    				
	    			case "SAVANNA_PLATEAU":
	    			case "SAVANNA_PLATEAU_MOUNTAINS":
	    				id = 36;
	    				break;
	    				
	    			case "SKY": id = 9; break;
	    			case "SMALL_MOUNTAINS": id = 34; break;
	    			case "STONE_BEACH": id = 25; break;
	    			
	    			case "SWAMPLAND":
	    			case "SWAMPLAND_MOUNTAINS":
	    				id = 6; break;
	    				
	    			case "TAIGA":
	    			case "MEGA_SPRUCE_TAIGA":
	    			case "MEGA_SPRUCE_TAIGA_HILLS":
	    				id = 5; break;
	    				
	    			case "TAIGA_HILLS":
	    			case "TAIGA_MOUNTAINS":
	    				id = 19; break;
	    				
	    			default: id = 1; break;
    			}
    		}
    		return biomeBase_getBiome.invoke(null, id);
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

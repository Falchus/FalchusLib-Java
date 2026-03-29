package com.falchus.lib.minecraft.spigot.utils.version;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.spigot.enums.Sound;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;
import com.falchus.lib.minecraft.spigot.utils.builder.GameProfileBuilder;
import com.falchus.lib.utils.builder.ClassInstanceBuilder;
import com.falchus.lib.utils.reflection.ReflectionUtils;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;

/**
 * Adapter for all versions. (tested with 1.8.8)
 */
@FieldDefaults(level = AccessLevel.PROTECTED)
public class VersionAdapter implements IVersionAdapter {
	
	final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	
	final Map<Player, Object> bossBars = new HashMap<>();
	
	@Getter String packageOb = "org.bukkit.";
	@Getter String packageObc = packageOb + "craftbukkit.";
	@Getter String packageNm = "net.minecraft.";
	@Getter String packageNms = packageNm + "server.";
	
    @Getter Class<?> blockPosition;
    @Getter Class<?> entityPlayer;
    @Getter Field entityPlayer_playerConnection;
    Class<?> playerConnection;
    @Getter Field playerConnection_networkManager;
    Class<?> networkManager;
    @Getter Field networkManager_channel;
    @Getter Class<?> playerInteractManager;
    Class<?> entity;
    @Getter Method entity_setLocation;
    @Getter Method entity_setInvisible;
    @Getter Class<?> world;
    @Getter Class<?> minecraftServer;
    @Getter Class<?> worldServer;
    
    Class<?> chatComponentText;
    
    Method entity_getBukkitEntity;
    Method entity_setYawPitch;
	
	Class<?> craftItemStack;
	Class<?> nmsItemStack;
	Class<?> nbtTagCompound;
	Method craftItemStack_asNMSCopy;
	Method craftItemStack_asBukkitCopy;
	Method nbtTagCompound_setString;
	Method nbtTagCompound_remove;
	Method nbtTagCompound_hasKey;
	Method nbtTagCompound_getString;
	
	Class<?> packet;
    Method playerConnection_sendPacket;
    Class<?> iChatBaseComponent;
    Class<?> scoreboardTeam;
    Class<?> scoreboard;
    Object scoreboardINST;
    Class<?> packetPlayOutScoreboardTeam;
    Class<?> craftPlayer;
    Method craftPlayer_getHandle;
    Class<?> player$Spigot;
    Method player_spigot;
    Class<?> entityHuman;
    Field entityHuman_profile;
    Field entityPlayer_ping;
    Class<?> packetPlayOutPlayerInfo$enumPlayerInfoAction;
    Object packetPlayOutPlayerInfo$enumPlayerInfoAction_UPDATE_DISPLAY_NAME;
    Object packetPlayOutPlayerInfo$enumPlayerInfoAction_ADD_PLAYER;
	
    Class<?> craftServer;
    Method craftServer_getServer;
    Class<?> bukkitServer;
    Method minecraftServer_getVersion;
    Field minecraftServer_recentTps;
	
    Class<?> biomeBase;
    Field biomeBase_biomes;
    Method biomeBase_getBiome;
	
	private Method nmsItemStack_getTag() {
		return ReflectionUtils.getMethod(nmsItemStack, "getTag");
	}
	private Method nmsItemStack_setTag() {
		return ReflectionUtils.getMethod(nmsItemStack, "setTag",
			nbtTagCompound
		);
	}
	private Method nmsItemStack_hasTag() {
		return ReflectionUtils.getMethod(nmsItemStack, "hasTag");
	}
	
	@SneakyThrows
	private Object packetPlayOutPlayerInfo$enumPlayerInfoAction_REMOVE_PLAYER() {
		return ReflectionUtils.getField(packetPlayOutPlayerInfo$enumPlayerInfoAction, "REMOVE_PLAYER").get(null);
	}
	private Class<?> packetPlayOutTitle$enumTitleAction() {
		return ReflectionUtils.getClass(packageNms + "PacketPlayOutTitle$EnumTitleAction");
	}
	@SneakyThrows
	private Object packetPlayOutTitle$enumTitleAction_TITLE() {
		return ReflectionUtils.getField(packetPlayOutTitle$enumTitleAction(), "TITLE").get(null);
	}
	@SneakyThrows
	private Object packetPlayOutTitle$enumTitleAction_SUBTITLE() {
    	return ReflectionUtils.getField(packetPlayOutTitle$enumTitleAction(), "SUBTITLE").get(null);
    }
    private Class<?> craftWorld() {
    	return ReflectionUtils.getClass(packageObc + "CraftWorld");
    }
    private Method craftWorld_getHandle() {
    	return ReflectionUtils.getMethod(craftWorld(), "getHandle");
    }
    private Method entity_setCustomName() {
    	return ReflectionUtils.getMethod(entity, "setCustomName",
    		String.class
    	);
    }
    private Method entity_setCustomNameVisible() {
    	return ReflectionUtils.getMethod(entity, "setCustomNameVisible",
    		boolean.class
    	);
    }
    private Method entity_getId() {
    	return ReflectionUtils.getMethod(entity, "getId");
    }
    private Class<?> dataWatcher() {
    	return ReflectionUtils.getClass(packageNms + "DataWatcher");
    }
    private Method entity_getDataWatcher() {
    	return ReflectionUtils.getMethod(entity, "getDataWatcher");
    }
    private Class<?> entityLiving() {
    	return ReflectionUtils.getClass(packageNms + "EntityLiving");
    }
    private Method entityLiving_setHealth() {
    	return ReflectionUtils.getMethod(entityLiving(), "setHealth",
    		float.class
    	);
    }
    private Method entityLiving_getMaxHealth() {
    	return ReflectionUtils.getMethod(entityLiving(), "getMaxHealth");
    }
    private Class<?> entityWither() {
    	return ReflectionUtils.getClass(packageNms + "EntityWither");
    }
    private Field packetPlayOutScoreboardTeam_name() {
    	return ReflectionUtils.getField(packetPlayOutScoreboardTeam, "a");
    }
    private Field packetPlayOutScoreboardTeam_displayName() {
    	return ReflectionUtils.getField(packetPlayOutScoreboardTeam, "b");
    }
    private Field packetPlayOutScoreboardTeam_prefix() {
    	return ReflectionUtils.getField(packetPlayOutScoreboardTeam, "c");
    }
    private Field packetPlayOutScoreboardTeam_suffix() {
    	return ReflectionUtils.getField(packetPlayOutScoreboardTeam, "d");
    }
    private Field packetPlayOutScoreboardTeam_players() {
    	return ReflectionUtils.getField(packetPlayOutScoreboardTeam, "g");
    }
    private Field packetPlayOutScoreboardTeam_mode() {
    	return ReflectionUtils.getField(packetPlayOutScoreboardTeam, "h");
    }
    
	public VersionAdapter() {
		try {
    		String version;
            String packageName = Bukkit.getServer().getClass().getPackageName();
            String[] parts = packageName.split("\\.");
            version = parts.length >= 4 ? parts[3] : "Unknown";
            
    		packageObc = packageObc + (!version.equals("Unknown") ? version + "." : "");
    		packageNms = packageNms + (!version.equals("Unknown") ? version + "." : "");
    		
            blockPosition = ReflectionUtils.getFirstClass(
            	packageNms + "BlockPosition",
            	packageNm + "core.BlockPosition"
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
            	packageNms + "network.PlayerConnection"
            );
            playerConnection_networkManager = ReflectionUtils.getFirstField(playerConnection,
            	"networkManager"
            );
            networkManager = ReflectionUtils.getFirstClass(
            	packageNms + "NetworkManager",
            	packageNm + "network.NetworkManager"
            );
            networkManager_channel = ReflectionUtils.getField(networkManager, "channel");
            playerInteractManager = ReflectionUtils.getFirstClass(
            	packageNms + "PlayerInteractManager",
            	packageNms + "level.PlayerInteractManager"
            );
            entity = ReflectionUtils.getFirstClass(
            	packageNms + "Entity",
            	packageNm + "world.entity.Entity"
            );
            entity_setLocation = ReflectionUtils.getFirstMethod(entity,
            	List.of(
            		double.class,
            		double.class,
            		double.class,
            		float.class,
            		float.class
            	),
        		"setLocation",
        		"a"
            );
            entity_setInvisible = ReflectionUtils.getMethod(entity, "setInvisible",
            	boolean.class
            );
            world = ReflectionUtils.getFirstClass(
            	packageNms + "World",
            	packageNm + "world.level.World"
            );
            minecraftServer = ReflectionUtils.getClass(packageNms + "MinecraftServer");
    		worldServer = ReflectionUtils.getFirstClass(
    			packageNms + "WorldServer",
    			packageNms + "level.WorldServer"
    		);
            
            chatComponentText = ReflectionUtils.getFirstClass(
            	packageNms + "ChatComponentText",
            	packageNm + "network.chat.ChatComponentText"
            );
            
            entity_getBukkitEntity = ReflectionUtils.getMethod(entity, "getBukkitEntity");
            entity_setYawPitch = ReflectionUtils.getFirstMethod(entity,
            	List.of(
            		float.class,
            		float.class
            	),
            	"setYawPitch",
            	"setRot"
            );
            
            craftItemStack = ReflectionUtils.getClass(packageObc + "inventory.CraftItemStack");
            nmsItemStack = ReflectionUtils.getFirstClass(
            	packageNms + "ItemStack",
            	packageNm + "world.item.ItemStack"
            );
            nbtTagCompound = ReflectionUtils.getFirstClass(
            	packageNms + "NBTTagCompound",
            	packageNm + "nbt.NBTTagCompound"
            );
            craftItemStack_asNMSCopy = ReflectionUtils.getMethod(craftItemStack, "asNMSCopy",
            	ItemStack.class
            );
            craftItemStack_asBukkitCopy = ReflectionUtils.getMethod(craftItemStack, "asBukkitCopy",
            	nmsItemStack
            );
            nbtTagCompound_setString = ReflectionUtils.getFirstMethod(nbtTagCompound,
            	List.of(
            		String.class,
            		String.class
            	),
        		"setString",
        		"putString"
        	);
            nbtTagCompound_remove = ReflectionUtils.getMethod(nbtTagCompound, "remove",
            	String.class
            );
            nbtTagCompound_hasKey = ReflectionUtils.getFirstMethod(nbtTagCompound,
            	List.of(
            		String.class
            	),
            	"hasKey",
            	"contains"
            );
            nbtTagCompound_getString = ReflectionUtils.getMethod(nbtTagCompound, "getString",
            	String.class
            );
    		
            packet = ReflectionUtils.getFirstClass(
            	packageNms + "Packet",
            	packageNm + "network.protocol.Packet"
            );
            playerConnection_sendPacket = ReflectionUtils.getFirstMethod(playerConnection,
            	List.of(
            		packet
            	),
            	"sendPacket",
            	"send"
            );
            iChatBaseComponent = ReflectionUtils.getFirstClass(
            	packageNms + "IChatBaseComponent",
            	packageNm + "network.chat.IChatBaseComponent"
            );
            scoreboardTeam = ReflectionUtils.getFirstClass(
            	packageNms + "ScoreboardTeam",
            	packageNm + "world.scores.ScoreboardTeam"
            );
            scoreboard = ReflectionUtils.getFirstClass(
            	packageNms + "Scoreboard",
            	packageNm + "world.scores.Scoreboard"
            );
            scoreboardINST = new ClassInstanceBuilder(
            	scoreboard
            ).build();
            packetPlayOutScoreboardTeam = ReflectionUtils.getFirstClass(
            	packageNms + "PacketPlayOutScoreboardTeam",
            	packageNm + "network.protocol.game.PacketPlayOutScoreboardTeam"
            );
            craftPlayer = ReflectionUtils.getClass(packageObc + "entity.CraftPlayer");
            craftPlayer_getHandle = ReflectionUtils.getMethod(craftPlayer, "getHandle");
            player$Spigot = ReflectionUtils.getClass(packageOb + "entity.Player$Spigot");
            player_spigot = ReflectionUtils.getMethod(Player.class, "spigot");
            entityHuman = entityPlayer.getSuperclass();
            entityHuman_profile = ReflectionUtils.getFirstField(entityHuman,
            	"bH",
            	"gameProfile"
            );
            entityPlayer_ping = ReflectionUtils.getField(entityPlayer, "ping");
            packetPlayOutPlayerInfo$enumPlayerInfoAction = ReflectionUtils.getFirstClass(
            	packageNms + "PacketPlayOutPlayerInfo$EnumPlayerInfoAction",
            	packageNm + "network.protocol.game.ClientboundPlayerInfoUpdatePacket$Action"
            );
            packetPlayOutPlayerInfo$enumPlayerInfoAction_UPDATE_DISPLAY_NAME = ReflectionUtils.getField(packetPlayOutPlayerInfo$enumPlayerInfoAction, "UPDATE_DISPLAY_NAME").get(null);
            packetPlayOutPlayerInfo$enumPlayerInfoAction_ADD_PLAYER = ReflectionUtils.getField(packetPlayOutPlayerInfo$enumPlayerInfoAction, "ADD_PLAYER").get(null);
    		
            craftServer = ReflectionUtils.getClass(packageObc + "CraftServer");
            craftServer_getServer = ReflectionUtils.getMethod(craftServer, "getServer");
            bukkitServer = ReflectionUtils.getClass(packageOb + "Server");
            minecraftServer_getVersion = ReflectionUtils.getMethod(minecraftServer, "getVersion");
            minecraftServer_recentTps = ReflectionUtils.getField(minecraftServer, "recentTps");
    		
    		biomeBase = ReflectionUtils.getFirstClass(
    			packageNms + "BiomeBase",
    			packageNm + "world.level.biome.BiomeBase"
    		);
            biomeBase_biomes = ReflectionUtils.getField(biomeBase, "biomes");
            biomeBase_getBiome = ReflectionUtils.getMethod(biomeBase, "getBiome",
            	int.class
            );
		} catch (Exception e) {
    		throw new IllegalStateException("Failed to initialize " + getClass().getSimpleName(), e);
    	}
	}
	
	@Override
	public Object createChatComponentText(@NonNull String text) {
		try {
			return new ClassInstanceBuilder(
				chatComponentText
			).withParams(
				Map.of(
					String.class,
					text
				)
			).build();
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	@Override
	public Entity getBukkitEntity(@NonNull Object entity) {
		try {
			return (Entity) entity_getBukkitEntity.invoke(entity);
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Override
	public void setYawPitch(@NonNull Object entity, float yaw, float pitch) {
		try {
			entity_setYawPitch.invoke(entity,
				yaw,
				pitch
			);
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
    @Override
    public ItemStack setUUID(@NonNull ItemStack item, UUID uuid) {
    	try {
    		Object nmsItem = craftItemStack_asNMSCopy.invoke(null,
    			item
    		);
    		if (nmsItem == null) return item;
    		
    		Object tag = (boolean) nmsItemStack_hasTag().invoke(nmsItem)
    				? nmsItemStack_getTag().invoke(nmsItem)
    				: nbtTagCompound.getConstructor().newInstance();
    		if (uuid == null) {
    			nbtTagCompound_remove.invoke(tag,
    				"UUID"
    				);
    		} else {
    			nbtTagCompound_setString.invoke(tag,
    				"UUID",
    				uuid.toString()
    			);
    		}
            nmsItemStack_setTag().invoke(nmsItem,
            	tag
            );
            return (ItemStack) craftItemStack_asBukkitCopy.invoke(null,
            	nmsItem
            );
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public UUID getUUID(@NonNull ItemStack item) {
    	try {
    		Object nmsItem = craftItemStack_asNMSCopy.invoke(null,
    			item
    		);
    		if (nmsItem == null) return null;
    		
    		if ((boolean) nmsItemStack_hasTag().invoke(nmsItem)) {
    			Object tag = nmsItemStack_getTag().invoke(nmsItem);
    			if ((boolean) nbtTagCompound_hasKey.invoke(tag,
    				"UUID"
    			)) {
    				return UUID.fromString((String) nbtTagCompound_getString.invoke(tag,
    					"UUID"
    				));
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
    		Object nmsItem = craftItemStack_asNMSCopy.invoke(null,
    			item
    		);
    		if (nmsItem == null) return item;
    		
    		Object tag = new ClassInstanceBuilder(
    			nbtTagCompound
    		).build();
    		nmsItemStack_setTag().invoke(nmsItem,
    			tag
    		);
    		
    		return (ItemStack) craftItemStack_asBukkitCopy.invoke(null,
    			nmsItem
    		);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
    @Override
    public void sendPacket(@NonNull Player player, @NonNull Object packet) {
    	try {
    		Object entityPlayer = getEntityPlayer(player);
    		Object connection = entityPlayer_playerConnection.get(entityPlayer);
    		playerConnection_sendPacket.invoke(connection,
    			packet
    		);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void sendTitle(@NonNull Player player, String title, String subtitle) {
    	try {
    		title = title != null ? title : "";
    		subtitle = subtitle != null ? subtitle : "";
    		
			Object titleComponent = createChatComponentText(title);
			Object titlePacket = new ClassInstanceBuilder(
				packageNms + "PacketPlayOutTitle"
			).withParams(
				Map.of(
					packetPlayOutTitle$enumTitleAction(),
					packetPlayOutTitle$enumTitleAction_TITLE()
				),
				Map.of(
					iChatBaseComponent,
					titleComponent
				)
			).build();
			sendPacket(player, titlePacket);
    		
			Object subtitleComponent = createChatComponentText(subtitle);
			Object subtitlePacket = new ClassInstanceBuilder(
				packageNms + "PacketPlayOutTitle"
			).withParams(
				Map.of(
					packetPlayOutTitle$enumTitleAction(),
					packetPlayOutTitle$enumTitleAction_SUBTITLE()
				),
				Map.of(
					iChatBaseComponent,
					subtitleComponent
				)
			).build();
			sendPacket(player, subtitlePacket);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void sendTablist(@NonNull Player player, List<String> header, List<String> footer, String name) {
    	try {
    	    String headerText = header != null ? String.join("\n", header) : "";
    	    String footerText = footer != null ? String.join("\n", footer) : "";

    	    Object headerComponent = createChatComponentText(headerText);
    	    Object footerComponent = createChatComponentText(footerText);
            
            Object packet = new ClassInstanceBuilder(
            	packageNms + "PacketPlayOutPlayerListHeaderFooter"
            ).withParams(
        		Map.of(
    				iChatBaseComponent,
        			headerComponent
        		)
            ).build();
            
            ReflectionUtils.setField(packet, "b", footerComponent);
            
            sendPacket(player, packet);
            player.setPlayerListName(name);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void sendBossbar(@NonNull Player player, @NonNull String title, double progress) {
    	try {
    		removeBossbar(player);
    		
            Location eye = player.getEyeLocation().clone();
            Location location = eye.add(eye.getDirection().multiply(45));
            
            float yaw = eye.getYaw();
            float pitch = Math.max(-15, Math.min(15, eye.getPitch()));
            
            Object worldServer = getWorldServer(player.getWorld());
            Object wither = new ClassInstanceBuilder(
            	entityWither()
            ).withParams(
        		Map.of(
        			world,
        			worldServer
        		)
            ).build();
            
            entity_setInvisible.invoke(wither,
            	true
            );
            entity_setCustomName().invoke(wither,
            	title
            );
            entity_setCustomNameVisible().invoke(wither,
            	true
            );
            
            float maxHealth = (float) entityLiving_getMaxHealth().invoke(wither);
            float newHealth = (float) Math.max(1, Math.min(maxHealth, progress * maxHealth));
            entityLiving_setHealth().invoke(wither,
            	newHealth
            );
            
            entity_setLocation.invoke(wither,
            	location.getX(),
            	location.getY(),
            	location.getZ(),
            	yaw,
            	pitch
            );
            
            Object spawnPacket = new ClassInstanceBuilder(
            	packageNms + "PacketPlayOutSpawnEntityLiving"
            ).withParams(
        		Map.of(
        			entityLiving(),
        			wither
        		)
            ).build();
            sendPacket(player, spawnPacket);
            
            Object metadataPacket = new ClassInstanceBuilder(
            	packageNms + "PacketPlayOutEntityMetadata"
            ).withParams(
        		Map.of(
        			int.class,
        			entity_getId().invoke(wither)
        		),
				Map.of(
					dataWatcher(),
					entity_getDataWatcher().invoke(wither)
				),
				Map.of(
					boolean.class,
					true
				)
            ).build();
            sendPacket(player, metadataPacket);
            
            bossBars.put(player, wither);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void removeBossbar(@NonNull Player player) {
    	try {
    		Object wither = bossBars.remove(player);
    		if (wither != null) {
    			int id = (int) entity_getId().invoke(wither);
    			Object destroyPacket = new ClassInstanceBuilder(
    				packageNms + "PacketPlayOutEntityDestroy"
    			).withParams(
					Map.of(
						int[].class,
						new int[] { id }
					)
    			).build();
    			sendPacket(player, destroyPacket);
    		}
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void sendActionbar(@NonNull Player player, @NonNull String message) {
		try {
			Object chatMessage = createChatComponentText(message);
			Object packet = new ClassInstanceBuilder(
				packageNms + "PacketPlayOutChat"
			).withParams(
				Map.of(
					iChatBaseComponent,
					chatMessage
				),
				Map.of(
					byte.class,
					(byte) 2
				)
			).build();
			sendPacket(player, packet);
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
    }
    
    @Override
    public void sendNametag(@NonNull Player player, @NonNull String prefix, @NonNull String suffix) {
		try {
			Set<String> players = Set.of(player.getName());
			
	        Object createPacket = new ClassInstanceBuilder(
	        	packetPlayOutScoreboardTeam
	        ).build();
	        ReflectionUtils.setField(createPacket, packetPlayOutScoreboardTeam_name(), player.getName());	        
	        ReflectionUtils.setField(createPacket, packetPlayOutScoreboardTeam_displayName(), player.getName());
	        ReflectionUtils.setField(createPacket, packetPlayOutScoreboardTeam_players(), players);
	        ReflectionUtils.setField(createPacket, packetPlayOutScoreboardTeam_mode(), 0);
	        
	        Object updatePacket = new ClassInstanceBuilder(
	        	packetPlayOutScoreboardTeam
	        ).build();
	        ReflectionUtils.setField(updatePacket, packetPlayOutScoreboardTeam_name(), player.getName());	        
	        ReflectionUtils.setField(updatePacket, packetPlayOutScoreboardTeam_displayName(), player.getName());
	        ReflectionUtils.setField(updatePacket, packetPlayOutScoreboardTeam_players(), players);
	        ReflectionUtils.setField(updatePacket, packetPlayOutScoreboardTeam_mode(), 2);
	        
	        ReflectionUtils.setField(createPacket, packetPlayOutScoreboardTeam_prefix(), prefix);
	        ReflectionUtils.setField(updatePacket, packetPlayOutScoreboardTeam_prefix(), prefix);
	        
	        ReflectionUtils.setField(createPacket, packetPlayOutScoreboardTeam_suffix(), suffix);
	        ReflectionUtils.setField(updatePacket, packetPlayOutScoreboardTeam_suffix(), suffix);
	        
	        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
	        	sendPacket(onlinePlayer, createPacket);
	        	sendPacket(onlinePlayer, updatePacket);
	        }
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
    }
    
    @Override
    public void removeNametag(@NonNull Player player) {
		try {
			Set<String> players = Set.of(player.getName());
			
	        Object removePacket = new ClassInstanceBuilder(
	        	packetPlayOutScoreboardTeam
	        ).build();
	        ReflectionUtils.setField(removePacket, packetPlayOutScoreboardTeam_name(), player.getName());
	        ReflectionUtils.setField(removePacket, packetPlayOutScoreboardTeam_players(), players);
	        ReflectionUtils.setField(removePacket, packetPlayOutScoreboardTeam_mode(), 4);
			
	        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
	        	sendPacket(onlinePlayer, removePacket);
	        }
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
    }
    
    @Override
    public void playSound(@NonNull Player player, @NonNull Location location, @NonNull Sound sound, float volume, float pitch) {
    	player.playSound(location, org.bukkit.Sound.valueOf(sound.name()), volume, pitch);
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
    public Object getPlayerSpigot(@NonNull Player player) {
    	try {
    		return player_spigot.invoke(player);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public GameProfile getProfile(@NonNull Object entityPlayer) {
    	try {
            return (GameProfile) entityHuman_profile.get(entityPlayer);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public int getPing(@NonNull Player player) {
    	try {
    		Object entityPlayer = getEntityPlayer(player);
    		return (int) entityPlayer_ping.get(entityPlayer);
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
	        
	        ReflectionUtils.setField(ReflectionUtils.getField(GameProfile.class, "name"), name);

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
    		
	        player.setCustomName(original);
	        player.setCustomNameVisible(true);
	        player.setDisplayName(original);

	        ReflectionUtils.setField(ReflectionUtils.getField(GameProfile.class, "name"), original);

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
    		
    		Object update = packetPlayOutPlayerInfo$enumPlayerInfoAction_UPDATE_DISPLAY_NAME;
    		Object packet = new ClassInstanceBuilder(
				packageNms + "PacketPlayOutPlayerInfo",
				packageNm + "network.protocol.game.ClientboundPlayerInfoUpdatePacket"
			).withParams(
				Map.of(
					packetPlayOutPlayerInfo$enumPlayerInfoAction,
					update
				),
				Map.of(
					Iterable.class,
					List.of(entityPlayer)
				)
			).build();
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
    		Object add = packetPlayOutPlayerInfo$enumPlayerInfoAction_ADD_PLAYER;
    		Object packet = new ClassInstanceBuilder(
				packageNms + "PacketPlayOutPlayerInfo",
				packageNm + "network.protocol.game.ClientboundPlayerInfoUpdatePacket"
			).withParams(
				Map.of(
					packetPlayOutPlayerInfo$enumPlayerInfoAction,
					add
				),
				Map.of(
					Iterable.class,
					List.of(entityPlayer)
				)
			).build();
            sendPacket(player, packet);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void removeEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
    	try {
    		Object remove = packetPlayOutPlayerInfo$enumPlayerInfoAction_REMOVE_PLAYER();
    		Object packet = new ClassInstanceBuilder(
    			packageNms + "PacketPlayOutPlayerInfo"
    		).withParams(
				Map.of(
					packetPlayOutPlayerInfo$enumPlayerInfoAction,
					remove
				),
				Map.of(
					Iterable.class,
					List.of(entityPlayer)
				)
    		).build();
            sendPacket(player, packet);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void spawnEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
    	try {
    		addEntityPlayer(player, entityPlayer);
    		
    		Object spawn = new ClassInstanceBuilder(
    			packageNms + "PacketPlayOutNamedEntitySpawn",
    			packageNm + "network.protocol.game.ClientboundAddPlayerPacket"
    		).withParams(
				Map.of(
					entityHuman,
					entityPlayer
				)
    		).build();
    		sendPacket(player, spawn);
    		
    		Object teleport = new ClassInstanceBuilder(
    			packageNms + "PacketPlayOutEntityTeleport",
    			packageNm + "network.protocol.game.ClientboundPlayerPositionPacket"
    		).withParams(
				Map.of(
					entity,
					entityPlayer
				)
    		).build();
    		sendPacket(player, teleport);
    		
    		Bukkit.getScheduler().runTask(FalchusLibMinecraftSpigot.getInstance(), () -> removeEntityPlayer(player, entityPlayer));
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public Object getMcServer() {
    	try {
    		Object server = craftServer.cast(Bukkit.getServer());
    		return craftServer_getServer.invoke(server);
    	} catch (Exception e) {
    		throw new RuntimeException(e);
    	}
    }
    
    @Override
    public Object getBukkitServer() {
		return bukkitServer.cast(Bukkit.getServer());
    }
    
    @Override
    public String getVersion() {
    	try {
    		Object server = getMcServer();
    		return (String) minecraftServer_getVersion.invoke(server);
    	} catch (Exception e) {
    		throw new RuntimeException(e);
    	}
    }
    
    @Override
    public double[] getRecentTps() {
		try {
    		Object server = getMcServer();
    		return (double[]) minecraftServer_recentTps.get(server);
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
    		return biomeBase_getBiome.invoke(null,
    			id
    		);
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
    
    @Override
    public Object getWorldServer(@NonNull World world) {
    	try {
    		return craftWorld_getHandle().invoke(world);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

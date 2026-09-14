package com.falchus.lib.minecraft.spigot.utils.version;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.spigot.enums.GameRule;
import com.falchus.lib.minecraft.spigot.enums.Sound;
import com.falchus.lib.minecraft.spigot.packets.wrapper.IPacketWrapper;
import com.falchus.lib.minecraft.spigot.packets.wrapper.PacketWrapper;
import com.falchus.lib.minecraft.spigot.packets.wrapper.entity.WrappedPacketOutEntityDestroy;
import com.falchus.lib.minecraft.spigot.packets.wrapper.entity.WrappedPacketOutEntityMetadata;
import com.falchus.lib.minecraft.spigot.packets.wrapper.playerlistheaderfooter.PacketPlayerListHeaderFooter;
import com.falchus.lib.minecraft.spigot.packets.wrapper.playerlistheaderfooter.WrappedPacketOutPlayerListHeaderFooter;
import com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.team.PacketScoreboardTeam;
import com.falchus.lib.minecraft.spigot.packets.wrapper.scoreboard.team.WrappedPacketOutScoreboardTeam;
import com.falchus.lib.minecraft.spigot.packets.wrapper.spawn.entity.WrappedPacketOutSpawnEntityLiving;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;
import com.falchus.lib.minecraft.spigot.utils.SchedulerUtils;
import com.falchus.lib.minecraft.spigot.utils.builder.GameProfileBuilder;
import com.falchus.lib.minecraft.spigot.wrapper.SpigotWrapper;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.Component;
import com.falchus.lib.minecraft.spigot.wrapper.network.chat.WrappedComponent;
import com.falchus.lib.minecraft.spigot.wrapper.network.syncher.DataWatcher;
import com.falchus.lib.minecraft.spigot.wrapper.world.AxisAlignedBB;
import com.falchus.lib.minecraft.spigot.wrapper.world.entity.EntityLiving;
import com.falchus.lib.minecraft.spigot.wrapper.world.entity.WrappedEntity;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.ScoreboardTeam;
import com.falchus.lib.minecraft.spigot.wrapper.world.scores.WrappedScoreboardTeam;
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
	
	protected static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	
	final Map<Player, Object> bossBars = new HashMap<>();
	final Map<Player, String> nametags = new HashMap<>();
	
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
    @Getter Class<?> entity;
    @Getter Method entity_setLocation;
    @Getter Method entity_setInvisible;
    @Getter Class<?> entityLiving;
    @Getter Class<?> world;
    @Getter Class<?> minecraftServer;
    @Getter Class<?> worldServer;
    Class<?> iScoreboardCriteria;
    @Getter Class<?> iScoreboardCriteria$enumScoreboardHealthDisplay;
    
    Class<?> chatComponentText;
    
    Class<?> craftEntity;
    Method craftEntity_getHandle;
    Class<?> craftLivingEntity;
    Method craftLivingEntity_getHandle;
    Class<?> axisAlignedBB;
	
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
    Class<?> scoreboardObjective;
    Class<?> packetPlayOutScoreboardTeam;
    Class<?> craftPlayer;
    Method craftPlayer_getHandle;
    Class<?> player$Spigot;
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
    Method world_getCubes;
    
    @Override
    public Method entity_setCustomName() {
    	return ReflectionUtils.getMethod(entity, "setCustomName",
    		String.class
    	);
    }
    
    @Override
    public Method scoreboard_registerObjective() {
    	return ReflectionUtils.getFirstMethod(scoreboard,
			List.of(
				String.class,
				iScoreboardCriteria
			),
			"registerObjective",
			"addObjective"
		);
    }
    
    @Override
    public Method scoreboard_unregisterObjective() {
    	return ReflectionUtils.getFirstMethod(scoreboard,
			List.of(
				scoreboardObjective
			),
			"unregisterObjective",
			"removeObjective"
		);
    }
	
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
    private Class<?> dataWatcher() {
    	return ReflectionUtils.getClass(packageNms + "DataWatcher");
    }
    private Method dataWatcher_getFloat() {
    	return ReflectionUtils.getMethod(dataWatcher(), "getFloat",
    		int.class
    	);
    }
    private Method dataWatcher_watch() {
    	return ReflectionUtils.getMethod(dataWatcher(), "watch",
    		int.class,
    		Object.class
    	);
    }
    private Class<?> entityWither() {
    	return ReflectionUtils.getClass(packageNms + "EntityWither");
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
            entityLiving = ReflectionUtils.getClass(packageNms + "EntityLiving");
            world = ReflectionUtils.getFirstClass(
            	packageNms + "World",
            	packageNm + "world.level.World"
            );
            minecraftServer = ReflectionUtils.getClass(packageNms + "MinecraftServer");
    		worldServer = ReflectionUtils.getFirstClass(
    			packageNms + "WorldServer",
    			packageNms + "level.WorldServer"
    		);
    		iScoreboardCriteria = ReflectionUtils.getFirstClass(
    			packageNms + "IScoreboardCriteria",
    			packageNm + "world.scores.criteria.IScoreboardCriteria"
    		);
    		iScoreboardCriteria$enumScoreboardHealthDisplay = ReflectionUtils.getFirstClass(
    			packageNms + "IScoreboardCriteria$EnumScoreboardHealthDisplay",
    			packageNm + "world.scores.criteria.IScoreboardCriteria$EnumScoreboardHealthDisplay"
    		);
            
            chatComponentText = ReflectionUtils.getFirstClass(
            	packageNms + "ChatComponentText",
            	packageNm + "network.chat.ChatComponentText"
            );
            
            craftEntity = ReflectionUtils.getClass(packageObc + "entity.CraftEntity");
            craftEntity_getHandle = ReflectionUtils.getMethod(craftEntity, "getHandle");
            craftLivingEntity = ReflectionUtils.getClass(packageObc + "entity.CraftLivingEntity");
            craftLivingEntity_getHandle = ReflectionUtils.getMethod(craftLivingEntity, "getHandle");
            axisAlignedBB = ReflectionUtils.getFirstClass(
            	packageNms + "AxisAlignedBB",
            	packageNm + "world.phys.AxisAlignedBB"
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
            scoreboardObjective = ReflectionUtils.getFirstClass(
            	packageNms + "ScoreboardObjective",
            	packageNm + "world.scores.ScoreboardObjective"
            );
            packetPlayOutScoreboardTeam = ReflectionUtils.getFirstClass(
            	packageNms + "PacketPlayOutScoreboardTeam",
            	packageNm + "network.protocol.game.PacketPlayOutScoreboardTeam"
            );
            craftPlayer = ReflectionUtils.getClass(packageObc + "entity.CraftPlayer");
            craftPlayer_getHandle = ReflectionUtils.getMethod(craftPlayer, "getHandle");
            player$Spigot = ReflectionUtils.getClass(packageOb + "entity.Player$Spigot");
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
            minecraftServer_recentTps = ReflectionUtils.getFirstField(minecraftServer,
            	"recentTps",
            	"tickTimes"
        	);
    		
    		biomeBase = ReflectionUtils.getFirstClass(
    			packageNms + "BiomeBase",
    			packageNm + "world.level.biome.BiomeBase"
    		);
            biomeBase_biomes = ReflectionUtils.getField(biomeBase, "biomes");
            biomeBase_getBiome = ReflectionUtils.getMethod(biomeBase, "getBiome",
            	int.class
            );
            world_getCubes = ReflectionUtils.getMethod(world, "a",
            	axisAlignedBB
            );
		} catch (Exception e) {
    		throw new IllegalStateException("Failed to initialize " + getClass().getSimpleName(), e);
    	}
	}
	
	@Override
	public Object createChatComponentText(@NonNull String text) {
		return new ClassInstanceBuilder(
			chatComponentText
		).withParams(
			Map.of(
				String.class,
				text
			)
		).build();
	}
	
	@Override
	public Object createPacketOutScoreboardTeam(@NonNull Set<String> names, @NonNull ScoreboardTeam team, int mode, String playerName) {
		return new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				team.getHandle().getClass(),
				team.getHandle()
			),
			Map.of(
				int.class,
				mode
			)
		).build();
	}
	
	@Override
	public Object createPacketOutPlayerListHeaderFooter(@NonNull Set<String> names, @NonNull String header, @NonNull String footer) {
		PacketPlayerListHeaderFooter packet = PacketWrapper.wrap(new ClassInstanceBuilder(
			names
		).build());
		packet.setHeader(header);
		packet.setFooter(footer);
		return packet.getHandle();
	}
	
	@Override
	public Object createClientboundSetTitleTextPacket(@NonNull Set<String> names, @NonNull Component text) {
		return new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				packetPlayOutTitle$enumTitleAction(),
				packetPlayOutTitle$enumTitleAction_TITLE()
			),
			Map.of(
				iChatBaseComponent,
				text.getHandle()
			)
		).build();
	}
	
	@Override
	public Object createClientboundSetSubtitleTextPacket(@NonNull Set<String> names, @NonNull Component text) {
		return new ClassInstanceBuilder(
			names
		).withParams(
			Map.of(
				packetPlayOutTitle$enumTitleAction(),
				packetPlayOutTitle$enumTitleAction_SUBTITLE()
			),
			Map.of(
				iChatBaseComponent,
				text.getHandle()
			)
		).build();
	}
	
	@Override
	public Object getEntity(@NonNull Entity entity) {
		try {
			return craftEntity_getHandle.invoke(entity);
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	@Override
	public Object getEntityLiving(@NonNull LivingEntity entity) {
		try {
			return craftLivingEntity_getHandle.invoke(entity);
		} catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
    @Override
    public double getAbsorption(@NonNull Damageable entity) {
    	try {
    		DataWatcher dataWatcher = new WrappedEntity(entity).getDataWatcher();
    		return (float) dataWatcher_getFloat().invoke(dataWatcher.getHandle(),
    			17
    		);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void setAbsorption(@NonNull Damageable entity, double absorption) {
    	try {
    		DataWatcher dataWatcher = new WrappedEntity(entity).getDataWatcher();
    		dataWatcher_watch().invoke(dataWatcher.getHandle(),
    			17,
    			(float) absorption
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
    public void sendTablist(@NonNull Player player, List<String> header, List<String> footer, String name) {
	    String headerText = header != null ? String.join("\n", header) : "";
	    String footerText = footer != null ? String.join("\n", footer) : "";

	    IPacketWrapper packet = new WrappedPacketOutPlayerListHeaderFooter(headerText, footerText);
        
        PlayerUtils.sendPacket(player, packet);
        player.setPlayerListName(name);
    }
    
    @Override
    public void sendBossbar(@NonNull Player player, @NonNull String title, double progress) {
		removeBossbar(player);
		
        EntityLiving wither = SpigotWrapper.wrap(new ClassInstanceBuilder(
        	entityWither()
        ).withParams(
    		Map.of(
    			world,
    			getWorldServer(player.getWorld())
    		)
        ).build());
        wither.setInvisible(true);
        wither.setCustomName(title);
        wither.setCustomNameVisible(true);
        
        float maxHealth = wither.getMaxHealth();
        float newHealth = (float) Math.max(1, Math.min(maxHealth, progress * maxHealth));
        wither.setHealth(newHealth);
        
        Location eye = player.getEyeLocation().clone();
        Location location = eye.add(eye.getDirection().multiply(45));
        float pitch = Math.max(-15, Math.min(15, eye.getPitch()));
        wither.setLocation(location.getX(), location.getY(), location.getZ(), eye.getYaw(), pitch);
        
        IPacketWrapper spawnPacket = new WrappedPacketOutSpawnEntityLiving(wither);
        PlayerUtils.sendPacket(player, spawnPacket);
        
        IPacketWrapper metadataPacket = new WrappedPacketOutEntityMetadata(wither.getId(), wither.getDataWatcher(), true);
        PlayerUtils.sendPacket(player, metadataPacket);
        
        bossBars.put(player, wither);
    }
    
    @Override
    public void removeBossbar(@NonNull Player player) {
		Object obj = bossBars.remove(player);
		if (obj instanceof EntityLiving wither) {
			int id = wither.getId();
			IPacketWrapper destroyPacket = new WrappedPacketOutEntityDestroy(id);
			PlayerUtils.sendPacket(player, destroyPacket);
		}
    }
    
    @Override
    public void sendActionbar(@NonNull Player player, @NonNull String message) {
		Object packet = new ClassInstanceBuilder(
			packageNms + "PacketPlayOutChat"
		).withParams(
			Map.of(
				iChatBaseComponent,
				new WrappedComponent(message).getHandle()
			),
			Map.of(
				byte.class,
				(byte) 2
			)
		).build();
		sendPacket(player, packet);
    }
    
    @Override
    public void sendNametag(@NonNull Player player, @NonNull String prefix, @NonNull String suffix, int sort) {
    	removeNametag(player);
    	
		Set<String> players = Set.of(player.getName());
		
		long s = Math.clamp(Integer.MAX_VALUE - (long) sort, 0, Integer.MAX_VALUE);
		String teamName = String.format("%08x", s) + player.getName();
		if (teamName.length() > 16) {
			teamName = teamName.substring(0, 16);
		}
		
		ScoreboardTeam team = new WrappedScoreboardTeam(teamName);
		team.setDisplayName(player.getName());
		team.setPrefix(prefix);
		team.setSuffix(suffix);
		
		PacketScoreboardTeam createPacket = new WrappedPacketOutScoreboardTeam(team, WrappedPacketOutScoreboardTeam.Mode.CREATE);
		createPacket.setPlayers(players);
        
		PacketScoreboardTeam updatePacket = new WrappedPacketOutScoreboardTeam(team, WrappedPacketOutScoreboardTeam.Mode.UPDATE);
		updatePacket.setPlayers(players);
        
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
        	PlayerUtils.sendPacket(onlinePlayer, createPacket);
        	PlayerUtils.sendPacket(onlinePlayer, updatePacket);
        }
        
        nametags.put(player, teamName);
    }
    
    @Override
    public void removeNametag(@NonNull Player player) {
    	String teamName = nametags.remove(player);
    	if (teamName == null) return;
		
		PacketScoreboardTeam removePacket = new WrappedPacketOutScoreboardTeam(WrappedPacketOutScoreboardTeam.Mode.REMOVE, teamName);
		
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
        	PlayerUtils.sendPacket(onlinePlayer, removePacket);
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
    }
    
    @Override
    public void resetSkin(@NonNull Player player) {
		Object entityPlayer = getEntityPlayer(player);
		GameProfile profile = getProfile(entityPlayer);

        Property original = PlayerUtils.skins.get(player.getUniqueId());
        if (original != null) {
            profile.getProperties().removeAll("textures");
            profile.getProperties().put("textures", original);

            refresh(player);

            PlayerUtils.skins.remove(player.getUniqueId());
        }
    }
    
    @Override
    public void setName(@NonNull Player player, @NonNull String name) {
		Object entityPlayer = getEntityPlayer(player);
		GameProfile profile = getProfile(entityPlayer);
		
		PlayerUtils.names.put(player.getUniqueId(), profile.getName());

        player.setCustomName(name);
        player.setCustomNameVisible(true);
        player.setDisplayName(name);
        
        ReflectionUtils.setField(ReflectionUtils.getField(GameProfile.class, "name"), name);

        refresh(player);
    }
    
    @Override
    public void resetName(@NonNull Player player) {
        String original = PlayerUtils.names.remove(player.getUniqueId());
        if (original == null) return;
		
        player.setCustomName(original);
        player.setCustomNameVisible(true);
        player.setDisplayName(original);

        ReflectionUtils.setField(ReflectionUtils.getField(GameProfile.class, "name"), original);

        refresh(player);
    }
    
    @Override
    public void refresh(@NonNull Player player) {
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
    }
    
    @Override
    public void addEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
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
    }
    
    @Override
    public void removeEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
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
    }
    
    @Override
    public void spawnEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
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
			packageNm + "network.protocol.game.PacketPlayOutEntityTeleport"
		).withParams(
			Map.of(
				entity,
				entityPlayer
			)
		).build();
		sendPacket(player, teleport);
		
		SchedulerUtils.runTask(() -> removeEntityPlayer(player, entityPlayer));
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
    public Server getBukkitServer() {
		return (Server) bukkitServer.cast(Bukkit.getServer());
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
    public void setGameRule(@NonNull World world, @NonNull GameRule gameRule, @NonNull String value) {
    	world.setGameRuleValue(gameRule.getKey(), value);
    }
	
    @Override
    public Object[] getBiomes() {
    	try {
            return (Object[]) biomeBase_biomes.get(null);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public int getBiomeId(Biome biome) {
		int id;
		if (biome == null) {
			id = 1;
		} else {
            id = switch (biome.name()) {
                case "BEACH" -> 16;
                case "BIRCH_FOREST" -> 27;
                case "BIRCH_FOREST_MOUNTAINS" -> 155;
                case "BIRCH_FOREST_HILLS" -> 28;
                case "BIRCH_FOREST_HILLS_MOUNTAINS" -> 156;
                case "COLD_BEACH" -> 26;
                case "COLD_TAIGA" -> 30;
                case "COLD_TAIGA_HILLS" -> 31;
                case "COLD_TAIGA_MOUNTAINS" -> 158;
                case "DEEP_OCEAN" -> 24;
                case "DESERT" -> 2;
                case "DESERT_HILLS" -> 17;
                case "DESERT_MOUNTAINS" -> 145;
                case "EXTREME_HILLS" -> 3;
                case "EXTREME_HILLS_MOUNTAINS" -> 131;
                case "EXTREME_HILLS_PLUS" -> 20;
                case "EXTREME_HILLS_PLUS_MOUNTAINS" -> 148;
                case "FOREST" -> 4;
                case "FLOWER_FOREST" -> 132;
                case "FOREST_HILLS" -> 18;
                case "FROZEN_OCEAN" -> 10;
                case "FROZEN_RIVER" -> 11;
                case "HELL" -> 8;
                case "ICE_MOUNTAINS" -> 13;
                case "ICE_PLAINS" -> 12;
                case "ICE_PLAINS_SPIKES" -> 140;
                case "JUNGLE" -> 21;
                case "JUNGLE_EDGE" -> 23;
                case "JUNGLE_EDGE_MOUNTAINS" -> 151;
                case "JUNGLE_HILLS" -> 22;
                case "JUNGLE_MOUNTAINS" -> 149;
                case "MEGA_TAIGA" -> 32;
                case "MEGA_TAIGA_HILLS" -> 33;
                case "MESA" -> 37;
                case "MESA_BRYCE" -> 165;
                case "MESA_PLATEAU" -> 39;
                case "MESA_PLATEAU_FOREST" -> 38;
                case "MESA_PLATEAU_FOREST_MOUNTAINS" -> 166;
                case "MESA_PLATEAU_MOUNTAINS" -> 167;
                case "MUSHROOM_ISLAND" -> 14;
                case "MUSHROOM_SHORE" -> 15;
                case "OCEAN" -> 0;
                case "PLAINS" -> 1;
                case "SUNFLOWER_PLAINS" -> 129;
                case "RIVER" -> 7;
                case "ROOFED_FOREST" -> 29;
                case "ROOFED_FOREST_MOUNTAINS" -> 157;
                case "SAVANNA" -> 35;
                case "SAVANNA_MOUNTAINS" -> 163;
                case "SAVANNA_PLATEAU" -> 36;
                case "SAVANNA_PLATEAU_MOUNTAINS" -> 164;
                case "SKY" -> 9;
                case "SMALL_MOUNTAINS" -> 34;
                case "STONE_BEACH" -> 25;
                case "SWAMPLAND" -> 6;
                case "SWAMPLAND_MOUNTAINS" -> 134;
                case "TAIGA" -> 5;
                case "MEGA_SPRUCE_TAIGA" -> 160;
                case "MEGA_SPRUCE_TAIGA_HILLS" -> 161;
                case "TAIGA_HILLS" -> 19;
                case "TAIGA_MOUNTAINS" -> 133;

                default -> 1;
            };
		}
		return id;
    }
    
    @Override
    public Object getNmsBiome(Biome biome) {
        try {
    		return biomeBase_getBiome.invoke(null,
    			getNmsBiome(biome) // TODO: wtf???
    		);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public Object getWorld(@NonNull World world) {
    	try {
    		return this.world.cast(getWorldServer(world));
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
    
    @Override
    public List<AxisAlignedBB> getCollidingBlocks(@NonNull World world, @NonNull AxisAlignedBB axisAlignedBB) {
    	try {
    		List<AxisAlignedBB> list = new ArrayList<>();
    		for (Object obj : (List<?>) world_getCubes.invoke(getWorldServer(world),
    			axisAlignedBB.getHandle()
    		)) {
    			list.add(SpigotWrapper.wrap(obj));
    		}
    		return list;
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

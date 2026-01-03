package com.falchus.lib.minecraft.spigot.utils.nms;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.falchus.lib.minecraft.spigot.enums.Sound;
import com.falchus.lib.minecraft.spigot.utils.builder.NmsPacketBuilder;
import com.falchus.lib.utils.ReflectionUtils;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Default adapter for all versions. (tested with 1.8.8)
 * We override methods in newer versions only if needed.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NmsAdapterDefault extends AbstractNmsAdapter {
	
	Method nmsItemStack_getTag;
	Method nmsItemStack_setTag;
	Method nmsItemStack_hasTag;
	
    Object enumPlayerInfoAction_REMOVE_PLAYER;
    Class<?> enumTitle$Action;
    Object enumTitle$Action_TITLE;
    Object enumTitle$Action_SUBTITLE;
    Class<?> world;
    Class<?> craftWorld;
    Method craftWorld_getHandle;
    Class<?> entity;
    Method entity_setInvisible;
    Method entity_setCustomName;
    Method entity_setCustomNameVisible;
    Method entity_setLocation;
    Method entity_getId;
    Class<?> entityLiving;
    Method entityLiving_setHealth;
    Method entityLiving_getMaxHealth;
    Class<?> entityWither;
    Method entity_getDataWatcher;
	
	public NmsAdapterDefault() {
		try {
            nmsItemStack_getTag = ReflectionUtils.getMethod(nmsItemStack, "getTag");
            nmsItemStack_setTag = ReflectionUtils.getMethod(nmsItemStack, "setTag", nbtTagCompound);
            nmsItemStack_hasTag = ReflectionUtils.getMethod(nmsItemStack, "hasTag");
			
            enumPlayerInfoAction_REMOVE_PLAYER = ReflectionUtils.getField(enumPlayerInfo$Action, "REMOVE_PLAYER").get(null);
            enumTitle$Action = ReflectionUtils.getClass(packageNms + "PacketPlayOutTitle$EnumTitleAction");
            enumTitle$Action_TITLE = ReflectionUtils.getField(enumTitle$Action, "TITLE").get(null);
            enumTitle$Action_SUBTITLE = ReflectionUtils.getField(enumTitle$Action, "SUBTITLE").get(null);
            world = ReflectionUtils.getClass(packageNms + "World");
            craftWorld = ReflectionUtils.getClass(packageObc + "CraftWorld");
            craftWorld_getHandle = ReflectionUtils.getMethod(craftWorld, "getHandle");
            entity = ReflectionUtils.getClass(packageNms + "Entity");
            entity_setInvisible = ReflectionUtils.getMethod(entity, "setInvisible", boolean.class);
            entity_setCustomName = ReflectionUtils.getMethod(entity, "setCustomName", String.class);
            entity_setCustomNameVisible = ReflectionUtils.getMethod(entity, "setCustomNameVisible", boolean.class);
            entity_setLocation = ReflectionUtils.getMethod(entity, "setLocation", double.class, double.class, double.class, float.class, float.class);
            entity_getId = ReflectionUtils.getMethod(entity, "getId");
            entity_getDataWatcher = ReflectionUtils.getMethod(entity, "getDataWatcher");
            entityLiving = ReflectionUtils.getClass(packageNms + "EntityLiving");
            entityLiving_setHealth = ReflectionUtils.getMethod(entityLiving, "setHealth", float.class);
            entityLiving_getMaxHealth = ReflectionUtils.getMethod(entityLiving, "getMaxHealth");
            entityWither = ReflectionUtils.getClass(packageNms + "EntityWither");
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
    public void sendTitle(@NonNull Player player, String title, String subtitle) {
    	try {
    		if (title != null && !title.isEmpty()) {
    			Object component = chatComponentText.getConstructor(String.class).newInstance(title);
    			Object titlePacket = new NmsPacketBuilder(packageNms + "PacketPlayOutTitle")
    					.withArgs(enumTitle$Action_TITLE, component)
    					.build();
    			sendPacket(player, titlePacket);
    		}
    		
    		if (subtitle != null && !subtitle.isEmpty()) {
    			Object component = chatComponentText.getConstructor(String.class).newInstance(subtitle);
    			Object subtitlePacket = new NmsPacketBuilder(packageNms + "PacketPlayOutTitle")
    					.withArgs(enumTitle$Action_SUBTITLE, component)
    					.build();
    			sendPacket(player, subtitlePacket);
    		}
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void sendTablist(@NonNull Player player, List<String> header, List<String> footer, String name) {
    	try {
    	    String headerText = header != null ? String.join("\n", header) : "";
    	    String footerText = footer != null ? String.join("\n", footer) : "";
    	    
    	    Object headerComponent = chatComponentText.getConstructor(String.class).newInstance(headerText);
            Object footerComponent = chatComponentText.getConstructor(String.class).newInstance(footerText);
            
            Object packet = new NmsPacketBuilder(packageNms + "PacketPlayOutPlayerListHeaderFooter")
            		.withArgs(headerComponent)
            		.build();
            
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
            
            Object worldServer = craftWorld_getHandle.invoke(player.getWorld());
            Object wither = entityWither.getConstructor(world).newInstance(worldServer);
            
            entity_setInvisible.invoke(wither, true);
            entity_setCustomName.invoke(wither, title);
            entity_setCustomNameVisible.invoke(wither, true);
            
            float maxHealth = (float) entityLiving_getMaxHealth.invoke(wither);
            float newHealth = (float) Math.max(1, Math.min(maxHealth, progress * maxHealth));
            entityLiving_setHealth.invoke(wither, newHealth);
            
            entity_setLocation.invoke(wither, location.getX(), location.getY(), location.getZ(), yaw, pitch);
            
            Object spawnPacket = new NmsPacketBuilder(packageNms + "PacketPlayOutSpawnEntityLiving")
            		.withArgs(wither)
            		.build();
            sendPacket(player, spawnPacket);
            
            Object metadataPacket = new NmsPacketBuilder(packageNms + "PacketPlayOutEntityMetadata")
            		.withArgs(entity_getId.invoke(wither), entity_getDataWatcher.invoke(wither), true)
            		.build();
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
    			int id = (int) entity_getId.invoke(wither);
    			Object destroyPacket = new NmsPacketBuilder(packageNms + "PacketPlayOutEntityDestroy")
    					.withArgs(new int[] { id })
    					.build();
    			sendPacket(player, destroyPacket);
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
    public void removeEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
    	try {
    		Object remove = enumPlayerInfoAction_REMOVE_PLAYER;
    		Object packet = new NmsPacketBuilder(packageNms + "PacketPlayOutPlayerInfo")
    				.withArgs(remove, Collections.singletonList(entityPlayer))
    				.build();
            sendPacket(player, packet);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

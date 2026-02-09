package com.falchus.lib.minecraft.spigot.utils.nms;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.falchus.lib.minecraft.spigot.enums.Sound;
import com.falchus.lib.minecraft.spigot.utils.PlayerUtils;
import com.falchus.lib.minecraft.spigot.utils.builder.NmsPacketBuilder;
import com.falchus.lib.utils.ReflectionUtils;

import lombok.NonNull;
import lombok.SneakyThrows;

/**
 * Default adapter for all versions. (tested with 1.8.8)
 * We override methods in newer versions only if needed.
 */
public class NmsAdapterDefault extends AbstractNmsAdapter {
	
	private Method nmsItemStack_getTag() {
		return ReflectionUtils.getMethod(nmsItemStack, "getTag");
	}
	private Method nmsItemStack_setTag() {
		return ReflectionUtils.getMethod(nmsItemStack, "setTag", nbtTagCompound);
	}
	private Method nmsItemStack_hasTag() {
		return ReflectionUtils.getMethod(nmsItemStack, "hasTag");
	}
	
	@SneakyThrows private Object enumPlayerInfoAction_REMOVE_PLAYER() {
		return ReflectionUtils.getField(enumPlayerInfo$Action, "REMOVE_PLAYER").get(null);
	}
	private Class<?> enumTitle$Action() {
		return ReflectionUtils.getClass(packageNms + "PacketPlayOutTitle$EnumTitleAction");
	}
	@SneakyThrows private Object enumTitle$Action_TITLE() {
		return ReflectionUtils.getField(enumTitle$Action(), "TITLE").get(null);
	}
	@SneakyThrows private Object enumTitle$Action_SUBTITLE() {
    	return ReflectionUtils.getField(enumTitle$Action(), "SUBTITLE").get(null);
    }
    private Class<?> world() {
    	return ReflectionUtils.getClass(packageNms + "World");
    }
    private Class<?> craftWorld() {
    	return ReflectionUtils.getClass(packageObc + "CraftWorld");
    }
    private Method craftWorld_getHandle() {
    	return ReflectionUtils.getMethod(craftWorld(), "getHandle");
    }
    private Class<?> entity() {
    	return ReflectionUtils.getClass(packageNms + "Entity");
    }
    private Method entity_setInvisible() {
    	return ReflectionUtils.getMethod(entity(), "setInvisible", boolean.class);
    }
    private Method entity_setCustomName() {
    	return ReflectionUtils.getMethod(entity(), "setCustomName", String.class);
    }
    private Method entity_setCustomNameVisible() {
    	return ReflectionUtils.getMethod(entity(), "setCustomNameVisible", boolean.class);
    }
    private Method entity_setLocation() {
    	return ReflectionUtils.getMethod(entity(), "setLocation", double.class, double.class, double.class, float.class, float.class);
    }
    private Method entity_getId() {
    	return ReflectionUtils.getMethod(entity(), "getId");
    }
    private Method entity_getDataWatcher() {
    	return ReflectionUtils.getMethod(entity(), "getDataWatcher");
    }
    private Class<?> entityLiving() {
    	return ReflectionUtils.getClass(packageNms + "EntityLiving");
    }
    private Method entityLiving_setHealth() {
    	return ReflectionUtils.getMethod(entityLiving(), "setHealth", float.class);
    }
    private Method entityLiving_getMaxHealth() {
    	return ReflectionUtils.getMethod(entityLiving(), "getMaxHealth");
    }
    private Class<?> entityWither() {
    	return ReflectionUtils.getClass(packageNms + "EntityWither");
    }
	
	@Override
	public Object createChatComponentText(@NonNull String text) {
		try {
			return chatComponentText.getConstructor(String.class).newInstance(text);
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
    @Override
    public ItemStack setUUID(@NonNull ItemStack item, UUID uuid) {
    	try {
    		Object nmsItem = craftItemStack_asNMSCopy.invoke(null, item);
    		if (nmsItem == null) return item;
    		
    		Object tag = (boolean) nmsItemStack_hasTag().invoke(nmsItem) ? nmsItemStack_getTag().invoke(nmsItem) : nbtTagCompound.getConstructor().newInstance();
    		if (uuid == null) {
    			nbtTagCompound_remove.invoke(tag, "UUID");
    		} else {
    			nbtTagCompound_setString.invoke(tag, "UUID", uuid.toString());
    		}
            nmsItemStack_setTag().invoke(nmsItem, tag);
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
    		
    		if ((boolean) nmsItemStack_hasTag().invoke(nmsItem)) {
    			Object tag = nmsItemStack_getTag().invoke(nmsItem);
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
    		nmsItemStack_setTag().invoke(nmsItem, tag);
    		
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
    					.withArgs(enumTitle$Action_TITLE(), component)
    					.build();
    			sendPacket(player, titlePacket);
    		}
    		
    		if (subtitle != null && !subtitle.isEmpty()) {
    			Object component = chatComponentText.getConstructor(String.class).newInstance(subtitle);
    			Object subtitlePacket = new NmsPacketBuilder(packageNms + "PacketPlayOutTitle")
    					.withArgs(enumTitle$Action_SUBTITLE(), component)
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
            
            Object worldServer = craftWorld_getHandle().invoke(player.getWorld());
            Object wither = entityWither().getConstructor(world()).newInstance(worldServer);
            
            entity_setInvisible().invoke(wither, true);
            entity_setCustomName().invoke(wither, title);
            entity_setCustomNameVisible().invoke(wither, true);
            
            float maxHealth = (float) entityLiving_getMaxHealth().invoke(wither);
            float newHealth = (float) Math.max(1, Math.min(maxHealth, progress * maxHealth));
            entityLiving_setHealth().invoke(wither, newHealth);
            
            entity_setLocation().invoke(wither, location.getX(), location.getY(), location.getZ(), yaw, pitch);
            
            Object spawnPacket = new NmsPacketBuilder(packageNms + "PacketPlayOutSpawnEntityLiving")
            		.withArgs(wither)
            		.build();
            sendPacket(player, spawnPacket);
            
            Object metadataPacket = new NmsPacketBuilder(packageNms + "PacketPlayOutEntityMetadata")
            		.withArgs(entity_getId().invoke(wither), entity_getDataWatcher().invoke(wither), true)
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
    			int id = (int) entity_getId().invoke(wither);
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
    public void sendActionbar(@NonNull Player player, @NonNull String message) {
		try {
			Object chatMessage = plugin.getNmsAdapter().createChatComponentText(message);
			Object packet = new NmsPacketBuilder(plugin.getNmsAdapter().getPackageNms() + "PacketPlayOutChat")
					.withArgs(chatMessage, (byte) 2)
					.build();
			PlayerUtils.sendPacket(player, packet);
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
    		Object remove = enumPlayerInfoAction_REMOVE_PLAYER();
    		Object packet = new NmsPacketBuilder(packageNms + "PacketPlayOutPlayerInfo")
    				.withArgs(remove, Collections.singletonList(entityPlayer))
    				.build();
            sendPacket(player, packet);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

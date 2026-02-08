package com.falchus.lib.minecraft.spigot.utils.nms.modern;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import com.falchus.lib.minecraft.spigot.enums.Sound;
import com.falchus.lib.minecraft.spigot.utils.nms.AbstractNmsAdapter;
import com.falchus.lib.utils.ReflectionUtils;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

/**
 * Adapter for all versions over 1.17. (tested with 1.21.11)
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NmsAdapter_Modern extends AbstractNmsAdapter {
	
    Class<?> itemMeta;
    Method itemStack_getItemMeta;
    Method itemStack_setItemMeta;
    Method itemMeta_getPDC;
    Class<?> namespacedKey;
    Class<?> persistentDataType;
    Object persistentDataType_STRING;
    Class<?> persistentDataContainer;
    Method persistentDataContainer_set;
    Method persistentDataContainer_get;
    Method persistentDataContainer_remove;
	
    Class<?> clientboundPlayerInfoRemovePacket;

	Method chatComponentText_literal;
    Class<?> clientboundSetTitleTextPacket;
    Class<?> clientboundSetSubtitleTextPacket;
    Class<?> clientboundPlayerListHeaderFooter;
    Class<?> bossBar;
    Method bossBar_setProgress;
    Method bossBar_addPlayer;
    Method bossBar_removeBossBar;
    Class<?> barColor;
    Object barColor_WHITE;
    Class<?> barStyle;
    Object barStyle_SOLID;
    Class<?> bossFlag;
    Method bukkitServer_createBossBar;
	
	public NmsAdapter_Modern() {
		try {
            itemMeta = Class.forName("org.bukkit.inventory.meta.ItemMeta");
            itemStack_getItemMeta = ItemStack.class.getMethod("getItemMeta");
            itemStack_setItemMeta = ItemStack.class.getMethod("setItemMeta", itemMeta);

            itemMeta_getPDC = itemMeta.getMethod("getPersistentDataContainer");
            namespacedKey = Class.forName(packageOb + "NamespacedKey");
            persistentDataType = ReflectionUtils.getClass(packageOb + "persistence.PersistentDataType");
            persistentDataType_STRING = persistentDataType.getField("STRING").get(null);
            persistentDataContainer = ReflectionUtils.getClass(packageOb + "persistence.PersistentDataContainer");
            persistentDataContainer_set = persistentDataContainer.getMethod("set", namespacedKey, persistentDataType, Object.class);
            persistentDataContainer_get = persistentDataContainer.getMethod("get", namespacedKey, persistentDataType);
            persistentDataContainer_remove = persistentDataContainer.getMethod("remove", namespacedKey);
			
            clientboundPlayerInfoRemovePacket = ReflectionUtils.getClass(packageNm + "network.protocol.game.ClientboundPlayerInfoRemovePacket");
            
            chatComponentText_literal = ReflectionUtils.getMethod(chatComponentText, "literal", String.class);
			clientboundSetTitleTextPacket = ReflectionUtils.getClass(packageNm + "network.protocol.game.ClientboundSetTitleTextPacket");
			clientboundSetSubtitleTextPacket = ReflectionUtils.getClass(packageNm + "network.protocol.game.ClientboundSetSubtitleTextPacket");
			clientboundPlayerListHeaderFooter = ReflectionUtils.getClass(packageNm + "network.protocol.game.ClientboundTabListPacket");
			bossBar = ReflectionUtils.getClass(packageOb + "boss.BossBar");
			bossBar_setProgress = ReflectionUtils.getMethod(bossBar, "setProgress", double.class);
			bossBar_addPlayer = ReflectionUtils.getMethod(bossBar, "addPlayer", Player.class);
			bossBar_removeBossBar = ReflectionUtils.getMethod(bukkitServer, "removeBossBar", namespacedKey);
			barColor = ReflectionUtils.getClass(packageOb + "boss.BarColor");
			barColor_WHITE = ReflectionUtils.getField(barColor, "WHITE").get(null);
			barStyle = ReflectionUtils.getClass(packageOb + "boss.BarStyle");
			barStyle_SOLID = ReflectionUtils.getField(barStyle, "SOLID").get(null);
			bossFlag = ReflectionUtils.getClass(packageOb + "boss.BarFlag");
			bukkitServer_createBossBar = ReflectionUtils.getMethod(bukkitServer, "createBossBar", String.class, barColor, barStyle, Array.newInstance(bossFlag, 0).getClass());
		} catch (Exception e) {
    		throw new IllegalStateException("Failed to initialize " + getClass().getSimpleName(), e);
    	}
	}
	
	@Override
	public Object createChatComponentText(@NonNull String text) {
		try {
			return chatComponentText_literal.invoke(null, text);
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	@Override
    public ItemStack setUUID(@NonNull ItemStack item, UUID uuid) {
    	try {
    		Object meta = itemStack_getItemMeta.invoke(item);
    		if (meta == null) return item;
    		
            Object pdc = itemMeta_getPDC.invoke(meta);
            Constructor<?> ctor = namespacedKey.getConstructor(Plugin.class, String.class);
            Object key = ctor.newInstance(plugin, "UUID");
    		
    		if (uuid == null) {
                persistentDataContainer_remove.invoke(pdc, key);
    		} else {
                persistentDataContainer_set.invoke(pdc, key, persistentDataType_STRING, uuid.toString());
    		}
            itemStack_setItemMeta.invoke(item, meta);
            return item;
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public UUID getUUID(@NonNull ItemStack item) {
    	try {
            Object meta = itemStack_getItemMeta.invoke(item);
            if (meta == null) return null;
    		
            Object pdc = itemMeta_getPDC.invoke(meta);
            Constructor<?> ctor = namespacedKey.getConstructor(Plugin.class, String.class);
            Object key = ctor.newInstance(plugin, "UUID");
            Object uuid = persistentDataContainer_get.invoke(pdc, key, persistentDataType_STRING);
            
            return uuid != null ? UUID.fromString((String) uuid) : null;
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public ItemStack clearNBT(@NonNull ItemStack item) {
    	try {
            Object meta = itemStack_getItemMeta.invoke(item);
            if (meta == null) return item;
    		
            Object pdc = itemMeta_getPDC.invoke(meta);
            Constructor<?> ctor = namespacedKey.getConstructor(Plugin.class, String.class);
            Object key = ctor.newInstance(plugin, "UUID");
            persistentDataContainer_remove.invoke(pdc, key);
    		
            itemStack_setItemMeta.invoke(item, meta);
            return item;
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public void sendTitle(@NonNull Player player, String title, String subtitle) {
		try {
			if (title != null && !title.isEmpty()) {
				Object component = chatComponentText_literal.invoke(null, title);
				Constructor<?> ctor = clientboundSetTitleTextPacket.getConstructor(chatComponentText);
				Object packet = ctor.newInstance(component);
				sendPacket(player, packet);
			}
			
            if (subtitle != null && !subtitle.isEmpty()) {
				Object literal = chatComponentText_literal.invoke(null, subtitle);
				Constructor<?> ctor = clientboundSetSubtitleTextPacket.getConstructor(chatComponentText);
				Object packet = ctor.newInstance(literal);
				sendPacket(player, packet);
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
    	    
            Object headerComponent = chatComponentText_literal.invoke(null, headerText);
            Object footerComponent = chatComponentText_literal.invoke(null, footerText);
            
            Constructor<?> ctor = clientboundPlayerListHeaderFooter.getConstructor(chatComponentText, chatComponentText);
            Object packet = ctor.newInstance(headerComponent, footerComponent);
            
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
    		
            Constructor<?> ctor = namespacedKey.getConstructor(Plugin.class, String.class);
            Object key = ctor.newInstance(plugin, "Bossbar_" + player.getUniqueId());
    		
    		Object bossBar = bukkitServer_createBossBar.invoke(
    			getBukkitServer(),
    			key,
    			title,
    			barColor_WHITE,
    			barStyle_SOLID,
    			(Object) Array.newInstance(bossFlag, 0)
    		);
    		
    		bossBar_setProgress.invoke(bossBar, progress);
    		bossBar_addPlayer.invoke(bossBar, player);
    		
    		bossBars.put(player, key);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void removeBossbar(@NonNull Player player) {
    	try {
    		Object key = bossBars.remove(player);
    		if (key == null) return;
    		
    		bossBar_removeBossBar.invoke(getBukkitServer(), key);
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void playSound(@NonNull Player player, @NonNull Location location, @NonNull Sound sound, float volume, float pitch) {
    	player.playSound(location, org.bukkit.Sound.valueOf(sound.getModernName()), volume, pitch);
    }

	@Override
	public void removeEntityPlayer(@NonNull Player player, @NonNull Object entityPlayer) {
		try {
			UUID uuid = getProfile(entityPlayer).getId();
			Constructor<?> ctor = clientboundPlayerInfoRemovePacket.getConstructor(Collection.class);
			Object packet = ctor.newInstance(Collections.singletonList(uuid));
			sendPacket(player, packet);
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}

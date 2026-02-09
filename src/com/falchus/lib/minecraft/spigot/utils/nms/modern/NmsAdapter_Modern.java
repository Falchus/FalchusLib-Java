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
import com.falchus.lib.minecraft.spigot.utils.nms.v1_9_R1.NmsAdapter_v1_9_R1;
import com.falchus.lib.utils.ReflectionUtils;

import lombok.NonNull;
import lombok.SneakyThrows;

/**
 * Adapter for all versions over 1.17. (tested with 1.21.11)
 */
public class NmsAdapter_Modern extends NmsAdapter_v1_9_R1 {
	
    private Class<?> itemMeta() {
    	return ReflectionUtils.getClass(packageOb + "inventory.meta.ItemMeta");
    }
    private Method itemStack_getItemMeta() {
    	return ReflectionUtils.getMethod(ItemStack.class, "getItemMeta");
    }
    private Method itemStack_setItemMeta() {
    	return ReflectionUtils.getMethod(ItemStack.class, "setItemMeta", itemMeta());
    }
    private Method itemMeta_getPDC() {
    	return ReflectionUtils.getMethod(itemMeta(), "getPersistentDataContainer");
    }
    private Class<?> namespacedKey() {
    	return ReflectionUtils.getClass(packageOb + "NamespacedKey");
    }
    private Class<?> persistentDataType() {
    	return ReflectionUtils.getClass(packageOb + "persistence.PersistentDataType");
    }
    @SneakyThrows private Object persistentDataType_STRING() {
    	return ReflectionUtils.getField(persistentDataType(), "STRING").get(null);
    }
    private Class<?> persistentDataContainer() {
    	return ReflectionUtils.getClass(packageOb + "persistence.PersistentDataContainer");
    }
    private Method persistentDataContainer_set() {
    	return ReflectionUtils.getMethod(persistentDataContainer(), "set", namespacedKey(), persistentDataType(), Object.class);
    }
    private Method persistentDataContainer_get() {
    	return ReflectionUtils.getMethod(persistentDataContainer(), "get", namespacedKey(), persistentDataType());
    }
    private Method persistentDataContainer_remove() {
    	return ReflectionUtils.getMethod(persistentDataContainer(), "remove", namespacedKey());
    }
	
    private Class<?> clientboundPlayerInfoRemovePacket() {
    	return ReflectionUtils.getClass(packageNm + "network.protocol.game.ClientboundPlayerInfoRemovePacket");
    }

    private Method chatComponentText_literal() {
    	return ReflectionUtils.getMethod(chatComponentText, "literal", String.class);
    }
	private Class<?> clientboundSetTitleTextPacket() {
		return ReflectionUtils.getClass(packageNm + "network.protocol.game.ClientboundSetTitleTextPacket");
	}
    private Class<?> clientboundSetSubtitleTextPacket() {
    	return ReflectionUtils.getClass(packageNm + "network.protocol.game.ClientboundSetSubtitleTextPacket");
    }
    private Class<?> clientboundPlayerListHeaderFooter() {
    	return ReflectionUtils.getClass(packageNm + "network.protocol.game.ClientboundTabListPacket");
    }
    private Class<?> bossBar() {
    	return ReflectionUtils.getClass(packageOb + "boss.BossBar");
    }
    private Method bossBar_setProgress() {
    	return ReflectionUtils.getMethod(bossBar(), "setProgress", double.class);
    }
    private Method bossBar_addPlayer() {
    	return ReflectionUtils.getMethod(bossBar(), "addPlayer", Player.class);
    }
    private Method bossBar_removeBossBar() {
    	return ReflectionUtils.getMethod(bukkitServer, "removeBossBar", namespacedKey());
    }
    private Class<?> barColor() {
    	return ReflectionUtils.getClass(packageOb + "boss.BarColor");
    }
    @SneakyThrows private Object barColor_WHITE() {
    	return ReflectionUtils.getField(barColor(), "WHITE").get(null);
    }
    private Class<?> barStyle() {
    	return ReflectionUtils.getClass(packageOb + "boss.BarStyle");
    }
    @SneakyThrows private Object barStyle_SOLID() {
    	return ReflectionUtils.getField(barStyle(), "SOLID").get(null);
    }
    private Class<?> bossFlag() {
    	return ReflectionUtils.getClass(packageOb + "boss.BarFlag");
    }
    private Method bukkitServer_createBossBar() {
    	return ReflectionUtils.getMethod(bukkitServer, "createBossBar", namespacedKey(), String.class, barColor(), barStyle(), Array.newInstance(bossFlag(), 0).getClass());
    }
	
	@Override
	public Object createChatComponentText(@NonNull String text) {
		try {
			return chatComponentText_literal().invoke(null, text);
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
	
	@Override
    public ItemStack setUUID(@NonNull ItemStack item, UUID uuid) {
    	try {
    		Object meta = itemStack_getItemMeta().invoke(item);
    		if (meta == null) return item;
    		
            Object pdc = itemMeta_getPDC().invoke(meta);
            Constructor<?> ctor = namespacedKey().getConstructor(Plugin.class, String.class);
            Object key = ctor.newInstance(plugin, "UUID");
    		
    		if (uuid == null) {
                persistentDataContainer_remove().invoke(pdc, key);
    		} else {
                persistentDataContainer_set().invoke(pdc, key, persistentDataType_STRING(), uuid.toString());
    		}
            itemStack_setItemMeta().invoke(item, meta);
            return item;
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public UUID getUUID(@NonNull ItemStack item) {
    	try {
            Object meta = itemStack_getItemMeta().invoke(item);
            if (meta == null) return null;
    		
            Object pdc = itemMeta_getPDC().invoke(meta);
            Constructor<?> ctor = namespacedKey().getConstructor(Plugin.class, String.class);
            Object key = ctor.newInstance(plugin, "UUID");
            Object uuid = persistentDataContainer_get().invoke(pdc, key, persistentDataType_STRING());
            
            return uuid != null ? UUID.fromString((String) uuid) : null;
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public ItemStack clearNBT(@NonNull ItemStack item) {
    	try {
            Object meta = itemStack_getItemMeta().invoke(item);
            if (meta == null) return item;
    		
            Object pdc = itemMeta_getPDC().invoke(meta);
            Constructor<?> ctor = namespacedKey().getConstructor(Plugin.class, String.class);
            Object key = ctor.newInstance(plugin, "UUID");
            persistentDataContainer_remove().invoke(pdc, key);
    		
            itemStack_setItemMeta().invoke(item, meta);
            return item;
    	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public void sendTitle(@NonNull Player player, String title, String subtitle) {
		try {
			if (title != null && !title.isEmpty()) {
				Object component = chatComponentText_literal().invoke(null, title);
				Constructor<?> ctor = clientboundSetTitleTextPacket().getConstructor(chatComponentText);
				Object packet = ctor.newInstance(component);
				sendPacket(player, packet);
			}
			
            if (subtitle != null && !subtitle.isEmpty()) {
				Object literal = chatComponentText_literal().invoke(null, subtitle);
				Constructor<?> ctor = clientboundSetSubtitleTextPacket().getConstructor(chatComponentText);
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
    	    
            Object headerComponent = chatComponentText_literal().invoke(null, headerText);
            Object footerComponent = chatComponentText_literal().invoke(null, footerText);
            
            Constructor<?> ctor = clientboundPlayerListHeaderFooter().getConstructor(chatComponentText, chatComponentText);
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
    		
            Constructor<?> ctor = namespacedKey().getConstructor(Plugin.class, String.class);
            Object key = ctor.newInstance(plugin, "Bossbar_" + player.getUniqueId());
    		
    		Object bossBar = bukkitServer_createBossBar().invoke(
    			getBukkitServer(),
    			key,
    			title,
    			barColor_WHITE(),
    			barStyle_SOLID(),
    			Array.newInstance(bossFlag(), 0)
    		);
    		
    		bossBar_setProgress().invoke(bossBar, progress);
    		bossBar_addPlayer().invoke(bossBar, player);
    		
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
    		
    		bossBar_removeBossBar().invoke(getBukkitServer(), key);
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
			Constructor<?> ctor = clientboundPlayerInfoRemovePacket().getConstructor(Collection.class);
			Object packet = ctor.newInstance(Collections.singletonList(uuid));
			sendPacket(player, packet);
		} catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}

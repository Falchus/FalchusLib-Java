package com.falchus.lib.minecraft.spigot.utils.nms.modern;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

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
	
    Class<?> clientboundPlayerInfoRemovePacket;

	Method chatComponentText_literal;
    Class<?> clientboundSetTitleTextPacket;
    Class<?> clientboundSetSubtitleTextPacket;
    Class<?> clientboundPlayerListHeaderFooter;
	
	public NmsAdapter_Modern() {
		try {
            clientboundPlayerInfoRemovePacket = ReflectionUtils.getClass(packageNm + "network.protocol.game.ClientboundPlayerInfoRemovePacket");
            
            chatComponentText_literal = ReflectionUtils.getMethod(chatComponentText, "literal", String.class);
			clientboundSetTitleTextPacket = ReflectionUtils.getClass(packageNm + "network.protocol.game.ClientboundSetTitleTextPacket");
			clientboundSetSubtitleTextPacket = ReflectionUtils.getClass(packageNm + "network.protocol.game.ClientboundSetSubtitleTextPacket");
			clientboundPlayerListHeaderFooter = ReflectionUtils.getClass(packageNm + "network.protocol.game.ClientboundTabListPacket");
		} catch (Exception e) {
    		throw new IllegalStateException("Failed to initialize " + getClass().getSimpleName(), e);
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

package com.falchus.lib.minecraft.spigot.utils.version.v1_9_R1;

import com.falchus.lib.minecraft.spigot.utils.version.VersionAdapter;
import com.falchus.lib.utils.reflection.ReflectionUtils;
import lombok.NonNull;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

import java.lang.reflect.Method;

public class VersionAdapter_v1_9_R1 extends VersionAdapter {

    private Method player_sendTitle() {
        return ReflectionUtils.getMethod(Player.class, "sendTitle",
                String.class,
                String.class
        );
    }

    private Method player$Spigot_sendMessage() {
        return ReflectionUtils.getMethod(player$Spigot, "sendMessage",
                ChatMessageType.class,
                BaseComponent[].class
        );
    }

    @Override
    public void sendTitle(@NonNull Player player, String title, String subtitle) {
        try {
            title = title != null ? title : "";
            subtitle = subtitle != null ? subtitle : "";

            player_sendTitle().invoke(player,
                    title,
                    subtitle
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendActionbar(@NonNull Player player, @NonNull String message) {
        try {
            Object spigot = getPlayerSpigot(player);
            Object chatMessage = TextComponent.fromLegacyText(message);

            player$Spigot_sendMessage().invoke(spigot,
                    ChatMessageType.ACTION_BAR,
                    chatMessage
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

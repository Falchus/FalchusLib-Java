package com.falchus.lib.minecraft.command;

import java.util.Collections;
import java.util.List;

import com.falchus.lib.minecraft.FalchusLibMinecraft;
import com.falchus.lib.minecraft.enums.Software;

import lombok.NonNull;

/**
 * Abstraction for commands.
 */
public interface IBaseCommand {
	
	/**
	 * Returns the main command.
	 */
    String getCommand();
    
    /**
     * Returns the aliases.
     */
    String[] getAliases();
    
    /**
     * Returns the permission node required.
     */
    String getPermission();
    
    /**
     * Returns the message to send when a sender does not have permission.
     */
    String getNoPermissionMessage();
    
    /**
     * Returns the usage message for the command.
     */
    String getUsageMessage();
	
    /**
     * Executes the command logic.
     */
    void executeCommand(@NonNull Object sender, @NonNull String[] args);
    
    /**
     * Tab completion.
     */
    default List<String> tabComplete(@NonNull Object sender, @NonNull String[] args) {
    	return Collections.emptyList();
    }

    /**
     * Checks if the sender has the permission required to execute this command.
     */
    default boolean hasPermission(@NonNull Object sender) {
        String permission = getPermission();
        if (permission != null && !permission.isEmpty()) {
        	Software software = FalchusLibMinecraft.getSoftware();
            if (software == Software.SPIGOT) {
                if (sender instanceof org.bukkit.entity.Player player) {
                    return player.hasPermission(permission);
                }
            } else if (software == Software.BUNGEECORD) {
                if (sender instanceof net.md_5.bungee.api.connection.ProxiedPlayer player) {
                    return player.hasPermission(permission);
                }
            } else if (software == Software.VELOCITY) {
            	if (sender instanceof com.velocitypowered.api.proxy.Player player) {
            		return player.hasPermission(permission);
            	}
            }
        }
        return true;
    }

    /**
     * Sends a message to the command sender.
     */
    default void sendMessage(@NonNull Object s, @NonNull String message) {
    	Software software = FalchusLibMinecraft.getSoftware();
        if (software == Software.SPIGOT) {
            if (s instanceof org.bukkit.command.CommandSender sender) {
            	sender.sendMessage(message);
            }
        } else if (software == Software.BUNGEECORD) {
            if (s instanceof net.md_5.bungee.api.CommandSender sender) {
            	sender.sendMessage(new net.md_5.bungee.api.chat.TextComponent(message));
            }
        } else if (software == Software.VELOCITY) {
        	if (s instanceof com.velocitypowered.api.command.CommandSource sender) {
        		sender.sendMessage(net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().deserialize(message));
        	}
        }
    }
}

package com.falchus.lib.minecraft.command.impl;

import java.util.Collections;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.command.IBaseCommand;

import lombok.Getter;
import lombok.NonNull;

/**
 * Abstract adapter for Spigot commands.
 */
@Getter
public abstract class SpigotCommandAdapter implements IBaseCommand, CommandExecutor, TabCompleter {

    private final String permission;
    private final String noPermissionMessage;
    private final String usageMessage;
	
    /**
     * Constructs a new SpigotCommandAdapter.
     */
	public SpigotCommandAdapter(String permission, String noPermissionMessage, String usageMessage) {
        this.permission = permission;
        this.noPermissionMessage = noPermissionMessage != null ? noPermissionMessage : "§cInsufficient permissions!";
        this.usageMessage = usageMessage != null ? usageMessage : "§cWrong usage.";
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!hasPermission(sender)) {
			sendMessage(sender, getNoPermissionMessage());
			return false;
		}
		executeCommand(sender, args);
		return true;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
		if (!hasPermission(sender)) return Collections.emptyList();
		List<String> list = tabComplete(sender, args);
		return list != null ? list : Collections.emptyList();
	}
	
	@Override
	public boolean hasPermission(@NonNull Object sender) {
        String permission = getPermission();
        if (permission != null) {
            if (sender instanceof Player player) {
                return player.hasPermission(permission);
            }
        }
        return true;
	}
	
	@Override
	public void sendMessage(@NonNull Object s, @NonNull String message) {
        if (s instanceof CommandSender sender) {
        	sender.sendMessage(message);
        }
	}
}
package com.falchus.lib.minecraft.command.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.falchus.lib.FalchusLib;
import com.falchus.lib.minecraft.command.BaseCommand;
import com.falchus.lib.minecraft.utils.AdventureUtils;
import com.falchus.lib.utils.StringUtils;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;

import lombok.Getter;
import lombok.NonNull;

@Getter
public abstract class VelocityCommandAdapter implements BaseCommand, SimpleCommand {

    private final String permission;
    private final String noPermissionMessage;
    private final String usageMessage;
    
	public VelocityCommandAdapter(String permission, String noPermissionMessage, String usageMessage) {
        this.permission = permission;
        this.noPermissionMessage = noPermissionMessage != null ? noPermissionMessage : FalchusLib.noPermissionMessage;
        this.usageMessage = usageMessage != null ? usageMessage : FalchusLib.prefix + "§cWrong usage.";
	}
	
	@Override
	public void execute(Invocation invocation) {
		CommandSource sender = invocation.source();
		String[] args = invocation.arguments();
		if (!hasPermission(sender)) {
			sendMessage(sender, getNoPermissionMessage());
			return;
		}
		executeCommand(sender, args);
	}
	
	@Override
	public List<String> suggest(Invocation invocation) {
		CommandSource sender = invocation.source();
		if (!hasPermission(sender)) return Collections.emptyList();
		
		String[] args = invocation.arguments();
		if (args.length == 0) return Collections.emptyList();
		
		List<String> list = tabComplete(sender, args);
		if (list == null) return Collections.emptyList();
		
		return StringUtils.copyPartialMatches(args[args.length - 1], list, new ArrayList<>());
	}
	
	@Override
	public CompletableFuture<List<String>> suggestAsync(Invocation invocation) {
		return CompletableFuture.completedFuture(suggest(invocation));
	}
	
	@Override
	public boolean hasPermission(@NonNull Object sender) {
        if (permission != null) {
        	if (sender instanceof Player player) {
        		return player.hasPermission(permission);
        	}	
        }
    	return true;
	}
	
	@Override
	public void sendMessage(@NonNull Object s, @NonNull String message) {
    	if (s instanceof CommandSource sender) {
    		sender.sendMessage(AdventureUtils.legacy(message));
    	}
	}
}

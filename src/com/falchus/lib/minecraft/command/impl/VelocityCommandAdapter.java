package com.falchus.lib.minecraft.command.impl;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.falchus.lib.minecraft.command.IBaseCommand;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.command.SimpleCommand;

import lombok.Getter;

/**
 * Abstract adapter for Velocity commands.
 */
@Getter
public abstract class VelocityCommandAdapter implements IBaseCommand, SimpleCommand {

    private final String command;
    private final String[] aliases;
    private final String permission;
    private final String noPermissionMessage;
    private final String usageMessage;
    
    /**
     * Constructs a new VelocityCommandAdapter.
     */
	public VelocityCommandAdapter(String permission, String noPermissionMessage, String usageMessage) {
        this.command = "";
        this.aliases = new String[0];
        this.permission = permission;
        this.noPermissionMessage = noPermissionMessage != null ? noPermissionMessage : "§cInsufficient permissions!";
        this.usageMessage = usageMessage != null ? usageMessage : "§cWrong usage.";
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
		String[] args = invocation.arguments();
		if (!hasPermission(sender)) return Collections.emptyList();
		List<String> list = tabComplete(sender, args);
		return list != null ? list : Collections.emptyList();
	}
	
	@Override
	public CompletableFuture<List<String>> suggestAsync(Invocation invocation) {
		return CompletableFuture.completedFuture(suggest(invocation));
	}
}

package com.falchus.lib.minecraft.command;

import java.util.Collections;
import java.util.List;

import lombok.NonNull;

/**
 * Abstraction for commands.
 */
public interface IBaseCommand {
    
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
    boolean hasPermission(@NonNull Object sender);

    /**
     * Sends a message to the command sender.
     */
    void sendMessage(@NonNull Object s, @NonNull String message);
}

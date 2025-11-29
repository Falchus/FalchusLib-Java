package com.falchus.lib.minecraft.spigot.utils.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * Utility class for backing up and restoring a player's inventory and armor.
 * supporting multiple backups per player by IDs.
 */
@UtilityClass
public class InventoryBackup {

    private static final Map<UUID, Map<Integer, BackupData>> playerInventories = new HashMap<>();

    /**
     * Internal class to hold data.
     */
    @AllArgsConstructor
    private static class BackupData {
        ItemStack[] inventory;
        ItemStack[] armor;
    }

    /**
     * Creates or overwrites a backup with the given ID for a player.
     */
    public static void backupInventory(@NonNull Player player, int id) {
    	Map<Integer, BackupData> backups = playerInventories.computeIfAbsent(
    		player.getUniqueId(),
    		k -> new HashMap<>()
    	);

        ItemStack[] inventory = player.getInventory().getContents().clone();
        ItemStack[] armor = player.getInventory().getArmorContents().clone();

        backups.put(id, new BackupData(inventory, armor));
    }

    /**
     * Restores a specific backup by its ID.
     * The backup is removed after restoration.
     * 
     * @return true if restored successfully
     */
    public static boolean loadInventory(@NonNull Player player, int id) {
    	Map<Integer, BackupData> backups = playerInventories.get(player.getUniqueId());
    	if (backups == null || !backups.containsKey(id)) return false;
        BackupData backup = backups.get(id);

        player.getInventory().setContents(backup.inventory);
        player.getInventory().setArmorContents(backup.armor);

        backups.remove(id);
        if (backups.isEmpty()) {
        	playerInventories.remove(player.getUniqueId());
        }
        return true;
    }

    /**
     * Checks if a specific backup exists for a player.
     */
    public static boolean hasBackup(@NonNull Player player, int id) {
        Map<Integer, BackupData> backups = playerInventories.get(player.getUniqueId());
        return backups != null && backups.containsKey(id);
    }
    
    /**
     * Returns all backups for a player.
     */
    public static Map<Integer, BackupData> getBackups(@NonNull Player player) {
    	return playerInventories.getOrDefault(player.getUniqueId(), Map.of());
    }
    
    /**
     * Deletes a specific backup.
     * 
     * @return true if deleted
     */
    public static boolean deleteBackup(@NonNull Player player, int id) {
    	Map<Integer, BackupData> backups = playerInventories.get(player.getUniqueId());
    	if (backups != null && backups.remove(id) != null) {
    		if (backups.isEmpty()) {
    			playerInventories.remove(player.getUniqueId());
    		}
    		return true;
    	}
    	return false;
    }
}
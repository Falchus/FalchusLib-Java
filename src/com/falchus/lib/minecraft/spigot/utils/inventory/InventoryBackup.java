package com.falchus.lib.minecraft.spigot.utils.inventory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import lombok.AllArgsConstructor;
import lombok.Getter;
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
    @Getter
    public static class BackupData {
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
     * Creates a backup using the next available ID.
     */
    public static void backupInventory(@NonNull Player player) {
    	int id = getNextBackupId(player);
    	backupInventory(player, id);
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
     * Loads the newest (highest ID) backup.
     * 
     * @return true if loaded successfully
     */
    public static boolean loadInventory(@NonNull Player player) {
        Map<Integer, BackupData> backups = playerInventories.get(player.getUniqueId());
        if (backups == null || backups.isEmpty()) return false;

        int id = backups.keySet().stream().max(Integer::compareTo).get();
        return loadInventory(player, id);
    }

    /**
     * Checks if a specific backup exists for a player.
     */
    public static boolean hasBackup(@NonNull Player player, int id) {
        Map<Integer, BackupData> backups = playerInventories.get(player.getUniqueId());
        return backups != null && backups.containsKey(id);
    }
    
    /**
     * Returns true if the player has at least one backup.
     */
    public static boolean hasBackup(@NonNull Player player) {
        Map<Integer, BackupData> backups = playerInventories.get(player.getUniqueId());
        return backups != null && !backups.isEmpty();
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
    
    /**
     * Creates the next available backup ID for a player.
     */
    public static int getNextBackupId(@NonNull Player player) {
    	Map<Integer, BackupData> backups = playerInventories.get(player.getUniqueId());
    	if (backups == null || backups.isEmpty()) {
    		return 1;
    	}
    	return backups.keySet().stream().max(Integer::compareTo).orElse(0) + 1;
    }
}
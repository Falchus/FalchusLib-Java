package com.falchus.lib.minecraft.spigot.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.function.Consumer;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.falchus.lib.interfaces.consumer.TriConsumer;
import com.falchus.lib.minecraft.spigot.utils.version.VersionProvider;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemUtils {
	
	public static final Map<UUID, Consumer<Player>> itemActions = new HashMap<>();
    public static final Map<UUID, TriConsumer<Player, ItemStack, InventoryClickEvent>> itemActionsInventory = new HashMap<>();
    public static final Map<Inventory, TriConsumer<Player, ItemStack, InventoryClickEvent>> inventoryCallbacks = new HashMap<>();

    private static final Set<UUID> permanent = new HashSet<>();
    
    public static Consumer<PlayerInteractEvent> globalInteractCallback;
    public static Consumer<InventoryClickEvent> globalInventoryCallback;

    /**
     * Sets a UUID on the given item via NBT.
     */
	public static ItemStack setUUID(@NonNull ItemStack item, UUID uuid) {
    	return VersionProvider.get().setUUID(item, uuid);
    }

    /**
     * Retrieves the UUID stores on the given item.
     */
    public static UUID getUUID(@NonNull ItemStack item) {
    	return VersionProvider.get().getUUID(item);
    }
    
    /**
     * Removes all NBT tags from the item.
     */
    public static ItemStack clearNBT(@NonNull ItemStack item) {
    	return VersionProvider.get().clearNBT(item);
    }
    
    /**
     * Sets the given item to permanent.
     */
    public static void setPermanent(@NonNull UUID uuid, boolean permanent) {
    	if (permanent) {
    		ItemUtils.permanent.add(uuid);
    	} else {
    		ItemUtils.permanent.remove(uuid);
    	}
    }
    
    /**
     * @return {@code true} if permanent, else {@code false}
     */
    public static boolean isPermanent(@NonNull UUID uuid) {
    	return permanent.contains(uuid);
    }
    
    /**
     * Gets an array of ItemStacks from a Base64 String.
     */
    public static ItemStack[] itemStackArrayFromBase64(String base64) {
    	try {
        	ByteArrayInputStream stream = new ByteArrayInputStream(Base64Coder.decodeLines(base64));
        	BukkitObjectInputStream input = new BukkitObjectInputStream(stream);
        	
        	ItemStack[] items = new ItemStack[input.readInt()];
        	for (int i = 0; i < items.length; i++) {
        		items[i] = (ItemStack) input.readObject();
        	}
        	return items;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return new ItemStack[0];
    }
    
    /**
     * Converts an array of ItemStacks to a Base64 String.
     */
    public static String itemStackArrayToBase64(ItemStack[] items) {
    	try {
    		ByteArrayOutputStream stream = new ByteArrayOutputStream();
    		BukkitObjectOutputStream output = new BukkitObjectOutputStream(stream);
        	
    		output.writeInt(items.length);
    		for (ItemStack item : items) {
    			output.writeObject(item);
            }
        	return Base64Coder.encodeLines(stream.toByteArray());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return null;
    }
    
    public static void clearActions(ItemStack item) {
    	if (item == null) return;
    	UUID uuid = getUUID(item);
    	if (uuid == null || permanent.contains(uuid)) return;
    	itemActions.remove(uuid);
    	itemActionsInventory.remove(uuid);
    }
    
    public static void clearActions(Inventory inventory) {
    	if (inventory == null) return;
    	for (ItemStack item : inventory.getContents()) {
    		clearActions(item);
    	}
    }
    
    public static void clearActions(@NonNull Player player) {
    	clearActions(player.getInventory());
    	for (ItemStack item : player.getInventory().getArmorContents()) {
    		clearActions(item);
    	}
    	clearActions(player.getItemOnCursor());
    	
    	Inventory top = player.getOpenInventory().getTopInventory();
    	inventoryCallbacks.remove(top);
    	if (top != player.getInventory()) {
    		clearActions(top);
    	}
    }

    /**
     * Represents an item in an inventory.
     */
    public record InventoryItem(int slot, @NonNull ItemStack item, Consumer<Player> onInventoryClick) {}
}
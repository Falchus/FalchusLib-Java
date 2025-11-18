package com.falchus.lib.minecraft.spigot.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.function.Consumer;

import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import com.falchus.lib.interfaces.consumer.TriConsumer;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.minecraft.server.v1_8_R3.NBTTagCompound;

/**
 * Utility class for items.
 */
@UtilityClass
public class ItemUtils {

	public static final Map<UUID, Consumer<Player>> itemActions = new HashMap<>();
    public static final Map<UUID, TriConsumer<Player, ItemStack, InventoryClickEvent>> itemActionsInventory = new HashMap<>();
    public static final Map<Inventory, TriConsumer<Player, ItemStack, InventoryClickEvent>> inventoryCallbacks = new HashMap<>();

    public static Consumer<PlayerInteractEvent> globalInteractCallback;
    public static Consumer<InventoryClickEvent> globalInventoryCallback;

    /**
     * Sets a UUID on the given item via NBT.
     */
    @SuppressWarnings("deprecation")
	public static ItemStack setUUID(@NonNull ItemStack item, @NonNull UUID uuid) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        if (nmsItem == null) {
            nmsItem = new net.minecraft.server.v1_8_R3.ItemStack(net.minecraft.server.v1_8_R3.Item.getById(item.getType().getId()));
        }

        NBTTagCompound tag = nmsItem.hasTag() ? nmsItem.getTag() : new NBTTagCompound();
        tag.setString("UUID", uuid.toString());
        nmsItem.setTag(tag);
        return CraftItemStack.asBukkitCopy(nmsItem);
    }

    /**
     * Retrieves the UUID stores on the given item.
     */
    public static UUID getUUID(@NonNull ItemStack item) {
        net.minecraft.server.v1_8_R3.ItemStack nmsItem = CraftItemStack.asNMSCopy(item);
        if (nmsItem == null) return null;

        if (nmsItem.hasTag() && nmsItem.getTag().hasKey("UUID")) {
            return UUID.fromString(nmsItem.getTag().getString("UUID"));
        }
        return null;
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

    /**
     * Represents an item in an inventory.
     */
    public static class InventoryItem {
        public final int slot;
        public final ItemStack item;
        public final Consumer<Player> onInventoryClick;

        /**
         * Constructs a new InventoryItem.
         */
        public InventoryItem(@NonNull Integer slot, @NonNull ItemStack item, Consumer<Player> onInventoryClick) {
            this.slot = slot;
            this.item = item;
            this.onInventoryClick = onInventoryClick;
        }
    }
}
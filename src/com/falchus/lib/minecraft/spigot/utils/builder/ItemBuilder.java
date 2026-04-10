package com.falchus.lib.minecraft.spigot.utils.builder;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.falchus.lib.interfaces.consumer.TriConsumer;
import com.falchus.lib.minecraft.spigot.enums.Version;
import com.falchus.lib.minecraft.spigot.utils.ItemUtils;
import com.falchus.lib.minecraft.spigot.utils.ServerUtils;
import com.falchus.lib.minecraft.spigot.utils.WorldUtils;
import com.falchus.lib.utils.reflection.ReflectionUtils;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import lombok.NonNull;

public class ItemBuilder {

	private ItemStack item;
	
	/**
	 * Creates an ItemBuilder for the given material and amount.
	 */
	public ItemBuilder(@NonNull Material material, int amount) {
		this.item = new ItemStack(material, amount);
	}
	
	/**
	 * Creates an ItemBuilder for the given material.
	 */
	public ItemBuilder(@NonNull Material material) {
		this(material, 1);
	}
	
	/**
	 * Creates an ItemBuilder for the given material and amount.
	 */
	public ItemBuilder(@NonNull com.falchus.lib.minecraft.spigot.enums.Material material, int amount) {
		Material mat = WorldUtils.getMaterial(material);
		if (ServerUtils.getVersion().isBefore(Version.v1_13)) {
			this.item = new ItemStack(mat, amount, (short) material.getLegacyDurability());
		} else {
			this.item = new ItemStack(mat, amount);
		}
	}
	
	/**
	 * Creates an ItemBuilder for the given material.
	 */
	public ItemBuilder(@NonNull com.falchus.lib.minecraft.spigot.enums.Material material) {
		this(material, 1);
	}
	
	/**
	 * Creates an ItemBuilder from an existing ItemStack.
	 */
	public ItemBuilder(@NonNull ItemStack item) {
		this.item = item.clone();
	}
	
	/**
	 * Sets the display name.
	 */
	public ItemBuilder setName(@NonNull String name) {
		ItemMeta meta = item.getItemMeta();
		if (meta != null) {
			meta.setDisplayName(name);
			item.setItemMeta(meta);
		}
		return this;
	}
	
	/**
	 * Sets the lore.
	 */
	public ItemBuilder setLore(@NonNull List<String> lore) {
		ItemMeta meta = item.getItemMeta();
		if (meta != null) {
			meta.setLore(lore);
			item.setItemMeta(meta);
		}
		return this;
	}
	
	/**
	 * Adds an unsafe enchantment.
	 */
	public ItemBuilder addEnchantment(@NonNull Enchantment enchantment, int level) {
		item.addUnsafeEnchantment(enchantment, level);
		return this;
	}
	
	/**
	 * Adds a item flag.
	 */
	public ItemBuilder addItemFlag(@NonNull ItemFlag itemFlag) {
		ItemMeta meta = item.getItemMeta();
		if (meta != null) {
			meta.addItemFlags(itemFlag);
			item.setItemMeta(meta);
		}
		return this;
	}
	
	/**
	 * Sets the durability.
	 */
	public ItemBuilder setDurability(short durability) {
		item.setDurability(durability);
		return this;
	}
	
	/**
	 * Sets the skull owner.
	 */
	public ItemBuilder setSkullOwner(@NonNull String owner) {
		if (item.getType() == WorldUtils.getMaterial(com.falchus.lib.minecraft.spigot.enums.Material.PLAYER_HEAD)) {
			SkullMeta meta = (SkullMeta) item.getItemMeta();
			if (meta != null) {
				meta.setOwner(owner);
				item.setItemMeta(meta);
			}
		}
		return this;
	}
	
	/**
	 * Sets a custom skull texture using a Base64 texture string.
	 */
	public ItemBuilder setSkullTexture(@NonNull String texture) {
		if (item.getType() == WorldUtils.getMaterial(com.falchus.lib.minecraft.spigot.enums.Material.PLAYER_HEAD)) {
			SkullMeta meta = (SkullMeta) item.getItemMeta();
			if (meta != null) {
				try {
					GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
					gameProfile.getProperties().put("textures", new Property("textures", texture));
					
					Field gameProfile_profile = ReflectionUtils.getField(meta.getClass(), "profile");
					gameProfile_profile.set(meta, gameProfile);
					
					item.setItemMeta(meta);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return this;
	}
	
	/**
	 * Sets a custom UUID (stored in NBT).
	 */
	public ItemBuilder setUUID(@NonNull UUID uuid) {
		ItemUtils.setUUID(item, uuid);
		return this;
	}

	/**
	 * Registers a callback to be executed when a player interacts.
	 */
	public ItemBuilder withInteractListener(@NonNull Consumer<Player> onPlayerInteract) {
	    UUID uuid = ItemUtils.getUUID(item);
	    if (uuid == null) {
	        uuid = UUID.randomUUID();
	        item = ItemUtils.setUUID(item, uuid);
	    }
	    ItemUtils.itemActions.put(uuid, onPlayerInteract);
	    return this;
	}

	/**
	 * Registers a callback to be executed when a player clicks in an inventory.
	 */
	public ItemBuilder withInventoryClickListener(@NonNull TriConsumer<Player, ItemStack, InventoryClickEvent> onInventoryClick) {
	    UUID uuid = ItemUtils.getUUID(item);
	    if (uuid == null) {
	        uuid = UUID.randomUUID();
	        item = ItemUtils.setUUID(item, uuid);
	    }
	    ItemUtils.itemActionsInventory.put(uuid, onInventoryClick);
	    return this;
	}
	
	/**
	 * Builds and returns the final {@link ItemStack}.
	 */
	public ItemStack build() {
		return item;
	}
}

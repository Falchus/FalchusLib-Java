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
import com.falchus.lib.minecraft.spigot.utils.ItemUtils;
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
		item = new ItemStack(material, amount);
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
		Material mat = material.toBukkit();
		Integer durability = material.getDurability();
		this.item = durability != null
			? new ItemStack(mat, amount, durability.shortValue())
			: new ItemStack(mat, amount);
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
	 * Makes the item glow by adding {@link Enchantment#DURABILITY} and hiding enchantments.
	 */
	public ItemBuilder glow() {
		addEnchantment(Enchantment.DURABILITY, 0);
		addItemFlag(ItemFlag.HIDE_ENCHANTS);
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
		if (item.getType() == com.falchus.lib.minecraft.spigot.enums.Material.PLAYER_HEAD.toBukkit()) {
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
		if (item.getType() == com.falchus.lib.minecraft.spigot.enums.Material.PLAYER_HEAD.toBukkit()) {
			SkullMeta meta = (SkullMeta) item.getItemMeta();
			if (meta != null) {
				GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);
				gameProfile.getProperties().put("textures", new Property("textures", texture));
				
				Field gameProfile_profile = ReflectionUtils.getField(meta.getClass(), "profile");
				ReflectionUtils.setField(meta, gameProfile_profile, gameProfile);
				
				item.setItemMeta(meta);
			}
		}
		return this;
	}
	
	/**
	 * Sets a custom UUID (stored in NBT).
	 */
	public ItemBuilder setUUID(@NonNull UUID uuid) {
		item = ItemUtils.setUUID(item, uuid);
		return this;
	}
	
	/**
	 * Sets as permanent.
	 */
	public ItemBuilder permanent() {
		UUID uuid = ItemUtils.getUUID(item);
		if (uuid == null) {
			uuid = UUID.randomUUID();
			setUUID(uuid);
		}
		ItemUtils.setPermanent(uuid, true);
		return this;
	}

	/**
	 * Registers a callback to be executed when a player interacts.
	 */
	public ItemBuilder withInteractListener(@NonNull Consumer<Player> onPlayerInteract) {
	    UUID uuid = ItemUtils.getUUID(item);
	    if (uuid == null) {
	        uuid = UUID.randomUUID();
	        setUUID(uuid);
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
	        setUUID(uuid);
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
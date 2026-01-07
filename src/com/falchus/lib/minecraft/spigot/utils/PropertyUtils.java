package com.falchus.lib.minecraft.spigot.utils;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.falchus.lib.minecraft.spigot.enums.Property;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PropertyUtils {
	
	private static final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	
	/**
	 * @return PropertyManager
	 */
	public static Object getPropertyManager() {
		return plugin.getNmsAdapter().getPropertyManager();
	}

	/**
	 * Saves the properties.
	 */
	public static void saveProperties() {
		plugin.getNmsAdapter().saveProperties();
	}
	
	/**
	 * Set a property value.
	 */
	public static void setProperty(@NonNull Property property, @NonNull Object value) {
		plugin.getNmsAdapter().setProperty(property, value);
	}
}

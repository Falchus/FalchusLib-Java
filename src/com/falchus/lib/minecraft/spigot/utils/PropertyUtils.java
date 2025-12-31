package com.falchus.lib.minecraft.spigot.utils;

import com.falchus.lib.minecraft.spigot.enums.Property;
import com.falchus.lib.minecraft.spigot.utils.nms.NmsAdapter;
import com.falchus.lib.minecraft.spigot.utils.nms.NmsProvider;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * Utility class for properties.
 */
@UtilityClass
public class PropertyUtils {
	
	private static final NmsAdapter nms = NmsProvider.get();
	
	/**
	 * @return PropertyManager
	 */
	public static Object getPropertyManager() {
		return nms.getPropertyManager();
	}

	/**
	 * Saves the properties.
	 */
	public static void saveProperties() {
		nms.saveProperties();
	}
	
	/**
	 * Set a property value.
	 */
	public static void setProperty(@NonNull Property property, @NonNull Object value) {
		nms.setProperty(property, value);
	}
}

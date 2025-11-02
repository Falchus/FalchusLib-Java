package com.falchus.lib.minecraft.spigot.utils;

import com.falchus.lib.minecraft.spigot.enums.Property;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import net.minecraft.server.v1_8_R3.DedicatedServer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PropertyManager;

@UtilityClass
public class PropertyUtils {
	
	public static PropertyManager getPropertyManager() {
		return ((DedicatedServer) MinecraftServer.getServer()).propertyManager;
	}

	public static void saveProperties() {
		getPropertyManager().savePropertiesFile();
	}
	
	public static void setProperty(@NonNull Property property, @NonNull Object value) {
		getPropertyManager().setProperty(property.getKey(), value);
		saveProperties();
	}
}

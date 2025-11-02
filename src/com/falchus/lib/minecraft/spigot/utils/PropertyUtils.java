package com.falchus.lib.minecraft.spigot.utils;

import com.falchus.lib.minecraft.spigot.enums.Property;

import lombok.experimental.UtilityClass;
import net.minecraft.server.v1_8_R3.DedicatedServer;
import net.minecraft.server.v1_8_R3.MinecraftServer;

@UtilityClass
public class PropertyUtils {

	public static void saveProperties() {
		((DedicatedServer) MinecraftServer.getServer()).propertyManager.savePropertiesFile();
	}
	
	public static void setProperty(Property property, Object value) {
		((DedicatedServer) MinecraftServer.getServer()).propertyManager.setProperty(property.getKey(), value);
	}
}

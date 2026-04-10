package com.falchus.lib.minecraft.utils;

import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class AdventureUtils {

	public static String toJson(@NonNull Component component) {
	    return GsonComponentSerializer.gson().serialize(component);
	}
	
	public static TextComponent legacy(@NonNull String input) {
		return LegacyComponentSerializer.legacySection().deserialize(input);
	}
}

package com.falchus.lib.minecraft.utils;

import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class AdventureUtils {

	public static String toJson(@NonNull Component component) {
	    return GsonComponentSerializer.gson().serialize(component);
	}
	
	public static Component legacy(@NonNull String input) {
		return LegacyComponentSerializer.legacySection().deserialize(input);
	}
	
	public static String plain(@NonNull String input) {
		return PlainTextComponentSerializer.plainText().serialize(legacy(input));
	}
}

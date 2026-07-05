package com.falchus.lib.minecraft.utils;

import lombok.NonNull;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;

public class AdventureUtils {
	
	public static final GsonComponentSerializer gson = GsonComponentSerializer.gson();
	public static final LegacyComponentSerializer legacy = LegacyComponentSerializer.legacySection();
	public static final PlainTextComponentSerializer plain = PlainTextComponentSerializer.plainText();

	public static String toJson(@NonNull Component component) {
	    return gson.serialize(component);
	}
	
	public static Component legacy(@NonNull String input) {
		return legacy.deserialize(input);
	}
	
	public static String plain(@NonNull String input) {
		return plain.serialize(legacy(input));
	}
}

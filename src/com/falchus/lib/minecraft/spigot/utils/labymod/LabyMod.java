package com.falchus.lib.minecraft.spigot.utils.labymod;

import java.util.Collection;
import java.util.UUID;

import org.bukkit.entity.Player;

import com.falchus.lib.minecraft.spigot.FalchusLibMinecraftSpigot;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LabyMod {
	
	private final FalchusLibMinecraftSpigot plugin = FalchusLibMinecraftSpigot.getInstance();
	
	@AllArgsConstructor
	@Getter
	public enum BalanceType {
		CASH("cash"),
		BANK("bank");
		
		private final String key;
	}

	/**
	 * Just send this packet to update the value of the balance or to show/hide it
	 */
	public static void updateBalanceDisplay(@NonNull Player player, BalanceType type, boolean visible, int balance) {
	    JsonObject economyObject = new JsonObject();
	    JsonObject cashObject = new JsonObject();

	    // Visibility
	    cashObject.addProperty("visible", visible);

	    // Amount
	    cashObject.addProperty("balance", balance);

	    /*
	    // Icon (Optional)
	    cashObject.addProperty("icon", "<url to image>");

	    // Decimal number (Optional)    
	    JsonObject decimalObject = new JsonObject();
	    decimalObject.addProperty("format", "##.##"); // Decimal format
	    decimalObject.addProperty("divisor", 100); // The value that divides the balance
	    cashObject.add("decimal", decimalObject);
	    */

	    // The display type can be "cash" or "bank".
	    economyObject.add(type.getKey(), cashObject);

	    // Send to LabyMod using the API
	    LabyModProtocol.sendMessage(plugin, player, "economy", economyObject);
	}
	
	public static void setSubtitle(@NonNull Player receiver, UUID subtitlePlayer, String value) {
	    // List of all subtitles
	    JsonArray array = new JsonArray();

	    // Add subtitle
	    JsonObject subtitle = new JsonObject();
	    subtitle.addProperty("uuid", subtitlePlayer.toString());

	    // Optional: Size of the subtitle
	    subtitle.addProperty("size", 0.8d); // Range is 0.8 - 1.6 (1.6 is Minecraft default)

	    // no value = remove the subtitle
	    if (value != null) {
	        subtitle.addProperty("value", value);
	    }

	    // If you want to use the new text format in 1.16+
	    // subtitle.add("raw_json_text", textObject);

	    // You can set multiple subtitles in one packet
	    array.add(subtitle);

	    // Send to LabyMod using the API
	    LabyModProtocol.sendMessage(plugin, receiver, "account_subtitle", array);
	}
	
	public static void sendFlag(@NonNull Player receiver, UUID uuid, String countryCode) {
	    JsonObject flagPacket = new JsonObject();

	    // Create array
	    JsonArray users = new JsonArray();

	    // Add user to array
	    JsonObject userObject = new JsonObject();
	    userObject.addProperty("uuid", uuid.toString()); // UUID of the flag player
	    userObject.addProperty("code", countryCode); // The country code (e.g. "us", "de")
	    users.add(userObject);

	    // Add array to flag object packet
	    flagPacket.add("users", users);

	    LabyModProtocol.sendMessage(plugin, receiver, "language_flag", flagPacket);
	}
	
	public enum ActionType {
		NONE,
		CLIPBOARD,
		RUN_COMMAND,
		SUGGEST_COMMAND,
		OPEN_BROWSER
	}
	
	public static void setMiddleClickActions(@NonNull Player player, Collection<JsonObject> entries) {
	    // List of all action menu entries
	    JsonArray array = new JsonArray();

	    // Add entries
	    for (JsonObject entry : entries) {
	    	array.add(entry);
	    }

	    // Send to LabyMod using the API
	    LabyModProtocol.sendMessage(plugin, player, "user_menu_actions", array);
	}
	
	public static void sendCurrentPlayingGamemode(@NonNull Player player, boolean visible, String gamemodeName) {
	    JsonObject object = new JsonObject();
	    object.addProperty("show_gamemode", visible); // Gamemode visible for everyone
	    object.addProperty("gamemode_name", gamemodeName); // Name of the current playing gamemode

	    // Send to LabyMod using the API
	    LabyModProtocol.sendMessage(plugin, player, "server_gamemode", object);
	}
	
	/**
	 * @param player The input prompt receiver
	 * @param promptSessionId A unique id for each packet, use a static number and increase it for each prompt request
	 * @param message The message above the text field
	 * @param value The value inside of the text field
	 * @param placeholder A placeholder text inside of the text field if there is no value given
	 * @param maxLength Max amount of characters of the text field value
	 */
	public static void sendInputPrompt(@NonNull Player player, int promptSessionId, String message, String value, String placeholder, int maxLength) {
	    JsonObject object = new JsonObject();
	    object.addProperty("id", promptSessionId);
	    object.addProperty("message", message);
	    object.addProperty("value", value);
	    object.addProperty("placeholder", placeholder);
	    object.addProperty("max_length", maxLength);

	    // If you want to use the new text format in 1.16+
	    // object.add("raw_json_text", textObject);

	    LabyModProtocol.sendMessage(plugin, player, "input_prompt", object);
	}
	
	public static void sendClientToServer(@NonNull Player player, String title, String address, boolean preview) {
	    JsonObject object = new JsonObject();
	    object.addProperty("title", title); // Title of the warning
	    object.addProperty("address", address); // Destination server address
	    object.addProperty("preview", preview); // Display the server icon, motd and user count

	    // Send to LabyMod using the API
	    LabyModProtocol.sendMessage(plugin, player, "server_switch", object);
	}
	
	/**
	 * Just send this packet to set the cinescope coverage
	 *  0% - Disabled
	 * 50% - Fully blind
	 */
	public static void sendCineScope(@NonNull Player player, int coveragePercent, long duration) {
	    JsonObject object = new JsonObject();

	    // Cinescope height (0% - 50%)
	    object.addProperty("coverage", coveragePercent);

	    // Duration
	    object.addProperty("duration", duration);

	    // Send to LabyMod using the API
	    LabyModProtocol.sendMessage(plugin, player, "cinescopes", object);
	}
}

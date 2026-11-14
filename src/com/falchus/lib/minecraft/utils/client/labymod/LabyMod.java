package com.falchus.lib.minecraft.utils.client.labymod;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import com.falchus.lib.minecraft.FalchusLibMinecraft;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LabyMod {
	
	@AllArgsConstructor
	@Getter
	public enum BalanceType {
		CASH("cash"),
		BANK("bank");
		
		private final String key;
	}

	public static void updateBalanceDisplay(@NonNull UUID uuid, @NonNull BalanceType type, boolean visible, int balance, String icon, JsonObject decimal) {
	    JsonObject economyObj = new JsonObject();
	    JsonObject cashObj = new JsonObject();

	    cashObj.addProperty("visible", visible);
	    cashObj.addProperty("balance", balance);

	    if (icon != null) {
	    	cashObj.addProperty("icon", icon);
	    }
	    
	    if (decimal != null) {
	    	cashObj.add("decimal", decimal);
	    }

	    economyObj.add(type.getKey(), cashObj);

	    LabyModProtocol.sendMessage(uuid, "economy", economyObj);
	}
	
	public static void setBanner(@NonNull UUID uuid, @NonNull String url) {
		JsonObject obj = new JsonObject();
		obj.addProperty("url", url);
		LabyModProtocol.sendMessage(uuid, "server_banner", obj);
	}
	
	/**
	 * @param countryCode	e.g. "us", "de"
	 */
	public static void setFlag(@NonNull UUID receiver, UUID uuid, String countryCode) {
	    JsonObject packet = new JsonObject();

	    JsonArray users = new JsonArray();

	    JsonObject userObj = new JsonObject();
	    userObj.addProperty("uuid", uuid.toString());
	    userObj.addProperty("code", countryCode);
	    users.add(userObj);

	    packet.add("users", users);

	    LabyModProtocol.sendMessage(receiver, "language_flag", packet);
	}
	
	public enum ActionType {
		NONE,
		CLIPBOARD,
		RUN_COMMAND,
		SUGGEST_COMMAND,
		OPEN_BROWSER
	}
	
	public static void setMiddleClickActions(@NonNull UUID uuid, Collection<JsonObject> entries) {
	    JsonArray array = new JsonArray();
	    for (JsonObject entry : entries) {
	    	array.add(entry);
	    }
	    LabyModProtocol.sendMessage(uuid, "user_menu_actions", array);
	}
	
	public static void emote(@NonNull UUID uuid, @NonNull UUID npcUUID, int emoteId) {
	    JsonArray array = new JsonArray();
	    
	    JsonObject emote = new JsonObject();
	    emote.addProperty("uuid", npcUUID.toString());
	    emote.addProperty("emote_id", emoteId);
	    array.add(emote);
	    
	    LabyModProtocol.sendMessage(uuid, "emote_api", array);
	}
	
	public static void sendCurrentPlayingGamemode(@NonNull UUID uuid, boolean visible, String gamemodeName) {
	    JsonObject obj = new JsonObject();
	    obj.addProperty("show_gamemode", visible);
	    obj.addProperty("gamemode_name", gamemodeName);

	    LabyModProtocol.sendMessage(uuid, "server_gamemode", obj);
	}
	
	/**
	 * @param uuid 			The input prompt receiver
	 * @param promptSessionId 	A unique id for each packet, use a static number and increase it for each prompt request
	 * @param message 			The message above the text field
	 * @param value 			The value inside of the text field
	 * @param placeholder 		A placeholder text inside of the text field if there is no value given
	 * @param maxLength 		Max amount of characters of the text field value
	 */
	public static void sendInputPrompt(@NonNull UUID uuid, int promptSessionId, String message, String value, String placeholder, int maxLength) {
	    JsonObject obj = new JsonObject();
	    obj.addProperty("id", promptSessionId);
	    obj.addProperty("message", message);
	    obj.addProperty("value", value);
	    obj.addProperty("placeholder", placeholder);
	    obj.addProperty("max_length", maxLength);

	    LabyModProtocol.sendMessage(uuid, "input_prompt", obj);
	}
	
	public static void sendClientToServer(@NonNull UUID uuid, String title, String address, boolean preview) {
	    JsonObject obj = new JsonObject();
	    obj.addProperty("title", title);
	    obj.addProperty("address", address);
	    obj.addProperty("preview", preview);

	    LabyModProtocol.sendMessage(uuid, "server_switch", obj);
	}
	
	public record Addon(UUID uuid, boolean required) {}
	
	public static void recommendAddons(@NonNull UUID uuid, Addon... addons) {
	    JsonObject obj = new JsonObject();
	    JsonArray array = new JsonArray();
	    for (Addon addon : addons) {
		    JsonObject a = new JsonObject();
		    a.addProperty("uuid", addon.uuid().toString());
		    a.addProperty("required", addon.required());
		    array.add(a);
	    }
	    obj.add("addons", array);

	    LabyModProtocol.sendMessage(uuid, "addon_recommendation", obj);
	}
	
	private enum Type {
		PERMISSION,
		ADDON
	}
	
	@Getter
	@AllArgsConstructor
	public enum Features {
		ANIMATIONS(Type.PERMISSION, true),
		BETTER_PERSPECTIVE_UNLOCK_CAMERA(Type.PERMISSION, true),
		BLOCKBUILD(Type.PERMISSION, true),
		CHAT(Type.PERMISSION, true),
		CHAT_AUTOTEXT(Type.PERMISSION, true),
		CROSSHAIR_SYNC(Type.PERMISSION, false),
		ENTITY_MARKER(Type.PERMISSION, false),
		GUI_ALL(Type.PERMISSION, true),
		GUI_ARMOR_HUD(Type.PERMISSION, true),
		GUI_ITEM_HUD(Type.PERMISSION, true),
		GUI_POTION_EFFECTS(Type.PERMISSION, true),
		IMPROVED_LAVA(Type.PERMISSION, false),
		RANGE(Type.PERMISSION, false), // CLASSIC PVP - 1.16 only
		REFILL_FIX(Type.PERMISSION, false),
		SATURATION_BAR(Type.PERMISSION, true),
		SLOWDOWN(Type.PERMISSION, false), // CLASSIC PVP - 1.16 only
		TAGS(Type.PERMISSION, true),
	    
		ADVANCEDAUTOGG(Type.ADDON, Set.of(
			"9f09b810-abbd-4f8f-9509-8e478b616a13"
		), true),
		ANTI_CLEAR_CHAT(Type.ADDON, Set.of(
			"6bc88ae0-b817-418f-b364-ceb11ca174d6",
			"d2770c5d-1733-4bf4-9553-b4e65522a9f4"
		), true),
		ANTI_COMMAND_CHOKER(Type.ADDON, Set.of(
			"1dc1b0e9-d927-4163-8428-a2f89a2cce97"
		), true),
		ANTIRAGE(Type.ADDON, Set.of(
			"c5a3f0f7-4856-4f0f-bf22-ed20ad27858e"
		), true),
		AUTOGG(Type.ADDON, Set.of(
			"a5ac827a-b0ef-46df-b356-247451b6fd10",
			"01debb7c-49ad-4439-821f-75391b3b35ee",
			"8b986a0e-35b7-40cb-a7f3-7675022af394"
		), true),
		AUTOGL(Type.ADDON, Set.of(
			"d682d69e-bc0d-46fb-a264-2c0cce5da690"
		), true),
		AUTORECONNECT(Type.ADDON, Set.of(
			"e3362096-13b5-49b8-8f4c-7d13083368fd",
			"e73fcfc9-d41b-47e8-9d2d-e911b837b861",
			"695bcbd5-3ded-4fe0-a88f-f413712cf087"
		), true),
		BADGES(Type.ADDON, Set.of(
			"9d18a620-ccc6-4333-93f2-53a5776f2139"
		), true),
		BATTYS_COORDINATES(Type.ADDON, Set.of(
			"b89e2567-090f-4c8d-ad7c-d7d54eebe2a0",
			"b2b04f6d-65d4-4afa-bb5b-a2f2ea27b3b6"
		), true),
		BETTER_PERSPECTIVE(Type.ADDON, Set.of(
			"588d5717-a7f6-4181-9dc8-9980b0a58c0e",
			"aa33e71a-3828-4b98-8dc4-c984a4b9e1e3",
			"85b45d21-76eb-44ac-9d1f-75c71f95ebc2"
		), true),
		BETTERHAT(Type.ADDON, Set.of(
			"686b5a9f-0cd4-4bd6-a230-08771fa206e8",
			"b7f0a906-b505-4732-a1fb-0a11a0b1c79c"
		), true),
		BETTERSCREENSHOT(Type.ADDON, Set.of(
			"4a183b6d-210a-4f49-a0fe-090f5e842b7b",
			"463d59e2-4cc9-4ab4-9a17-d9c7f523ea01"
		), true),
		BETTERTOOLTIPS(Type.ADDON, Set.of(
			"13ce3743-8ea6-43e9-a0a8-e4c11bee76a6"
		), true),
		CHATCOPIED(Type.ADDON, Set.of(
			"2c2b030c-4cba-420c-94fb-588c6a802ec4"
		), true),
		CHATLOG(Type.ADDON, Set.of(
			"a44cbd59-2d16-4ace-b8ac-48abe1edc3c4",
			"26bb2788-f55d-4f6e-a37e-504cf73b5941"
		), true),
		CHATTIME(Type.ADDON, Set.of(
			"ae639712-c591-4b11-a576-8ebaa8d225f4",
			"f3b68a60-af70-448d-9e08-f83e6c0bea20",
			"cba48d17-fbaa-4245-bb14-d3b882cfdf02",
			"37fb30e9-f984-4e7e-b73b-556dbbd9d7cf"
		), true),
		CHATTRANSLATOR(Type.ADDON, Set.of(
			"7a040e25-0713-41af-ab62-7d7b910f606c"
		), true),
		CHATUTILS(Type.ADDON, Set.of(
			"5c7445f2-7a84-40de-9fc1-95164fe66cc5"
		), true),
		COLOR_CORRECTION(Type.ADDON, Set.of(
			"53b3d90c-6e56-4602-9d61-2d3a35574167"
		), true),
		CONNECTIONHISTORY(Type.ADDON, Set.of(
			"545e8966-eb5f-4bd3-a975-7ea2af005904"
		), true),
		CONTROLLER(Type.ADDON, Set.of(
			"5e692cd6-8e08-4cd2-9a63-58be20f1ea50",
			"e3d90472-5793-4007-8996-f17d4f672725"
		), true),
		CPU_USAGE_DISPLAY(Type.ADDON, Set.of(
			"c8cb4468-50cc-4082-8733-f439b34a4d28"
		), true),
		CUSTOM_BLOCK_OVERLAY(Type.ADDON, Set.of(
			"6d140725-6dcd-4ae9-b401-534a480d214e",
			"e4c46b18-b5b8-40d5-badc-5f65d6f10c44",
			"d33dfb02-da37-4488-b91b-5550bc5b1551"
		), true),
		CUSTOM_FONT(Type.ADDON, Set.of(
			"325388d5-c435-42bf-83be-d577d816da00",
			"9bbe7717-028c-4230-bf4c-ba7057b04993"
		), true),
		CUSTOM_MAIN_MENU(Type.ADDON, Set.of(
			"bb3b7bde-7d4e-4b82-a94c-ca1a7c0b5997",
			"6f1fce7b-5f4e-4284-8a7f-a7f0d2536036",
			"c84acd04-07d0-4118-8511-d65b5a6dc99a"
		), true),
		CUSTOMCROSSHAIR(Type.ADDON, Set.of(
			"617c0013-b1c0-44b6-93af-86ab6720ed92",
			"4bfdd875-960c-497b-aca4-cbcb71de4b21",
			"5dfc0883-e2fe-4be2-ab5d-e0e6f736ccc2"
		), true),
		CUSTOMFILTERSOUNDS(Type.ADDON, Set.of(
			"818de25e-e31c-4036-a730-ad821dc6ebdb"
		), true),
		CUSTOMHITBOXES(Type.ADDON, Set.of(
			"167a11ee-a9d0-4955-a602-8e8f94713add",
			"da3b53e5-88f9-46a8-94ba-2cf3b4b9c8a8",
			"de7e9108-aa0a-4637-b81e-0f3317a16660",
			"ccb8a00a-7992-4b87-8921-1b19656f113c"
		), true),
		CUSTOMTIME(Type.ADDON, Set.of(
			"41a83e79-e36c-401e-87bf-cb9164611747"
		), true),
		DAMAGEINDICATOR(Type.ADDON, Set.of(
			"8d4ed1eb-4b1c-46bc-aac9-8abc8c6a1308",
			"686fdf14-225d-4475-b062-9f68be19f4d1",
			"211222f3-2650-4463-b986-e1d29c46117f"
		), true),
		DIRECT_CONNECT_HISTORY(Type.ADDON, Set.of(
			"49d842ca-780a-4ee7-960e-b5a759a73e81",
			"e97121db-8af9-4069-80e2-4d185fda5f76",
			"772af41d-f4c2-4806-80ee-955afbb3a3f3"
		), true),
		DIRECTIONHUD(Type.ADDON, Set.of(
			"5dc6e650-8536-11e7-bb31-be2e44b06b34",
			"26d6f912-825a-45f8-877e-97149fb88df8"
		), true),
		EMOTECHAT(Type.ADDON, Set.of(
			"8f645692-0be5-4d49-8f51-999a11943017",
			"ad5e9075-1357-43a6-80f0-484945ddf8f7",
			"6068f458-3ef0-4add-ae04-c21fac71d783"
		), true),
		FASTJOIN(Type.ADDON, Set.of(
			"5801cf56-18ca-48a3-b2d9-e693d92c57a3"
		), true),
		FOCUS_MOVEMENT_FIX(Type.ADDON, Set.of(
			"c5eb92ad-bd70-45ef-8a57-ed791cf79bfd"
		), true),
		FRIEND_TAGS(Type.ADDON, Set.of(
			"e11eac53-d2ae-4535-8233-0de32176b196",
			"c4a4fdb0-4a86-4ea8-8f38-afaef9fd0a48",
			"72115a66-1f9b-4cf0-b346-918075847a7d"
		), true),
		FRIENDVIEWER(Type.ADDON, Set.of(
			"4dd30326-f696-4c69-b56e-4719da08ea9e"
		), true),
		FULL_BRIGHT(Type.ADDON, Set.of(
			"7fdcb1fb-36fb-4851-9991-c765d76ed311",
			"3a099a6a-8810-44de-a0c7-8a99f3c18167",
			"0291b785-8028-4f88-bb40-f3f2fea6ce91"
		), true),
		FURNITURE(Type.ADDON, Set.of(
			"bac6b314-75e1-4bba-8837-fe46cfb8c20f"
		), true),
		GLINT_COLORIZER(Type.ADDON, Set.of(
			"388ea126-96f1-415f-8ad3-4131f32f1e66",
			"11e6f678-01e8-4986-a171-b8c88170c408"
		), true),
		GLOBALCHAT(Type.ADDON, Set.of(
			"54ab1f45-ea4f-4685-aef0-50f6f46dcc1c",
			"388c4c0d-5c06-4e15-bcb0-47cb3ab7851e"
		), true),
		GUI_BLUR_MOD(Type.ADDON, Set.of(
			"692dccfe-02d1-41ce-b688-690cdff1e2b7"
		), true),
		HDSKINS(Type.ADDON, Set.of(
			"b5b78bc3-a2d9-46ac-bbd0-e6236436b346",
			"ff6dc051-2878-4452-b15b-3dd9da7ac6a1",
			"dbaf2be9-0762-4480-8015-2682f3aa1e3e"
		), true),
		HEADOWNER(Type.ADDON, Set.of(
			"41cbf364-c3cd-4328-a2b5-23a4ce5bd265"
		), true),
		HOTBAR(Type.ADDON, Set.of(
			"0846893c-e926-470c-bffa-72b2d96935a1"
		), true),
		I_LOVE_MUSIC(Type.ADDON, Set.of(
			"c55895f3-d55c-4be6-8c32-bc6a1fb519b8",
			"8b6bff84-4266-4e12-89c2-204b142e74fa"
		), true),
		INSTANT_TAB_COMPLETION(Type.ADDON, Set.of(
			"20661a70-faae-4459-ac67-b36a73e4fb69"
		), true),
		ITEMPHYSICS(Type.ADDON, Set.of(
			"8a7b096a-8536-11e7-bb31-be2e44b06b34",
			"c74ed735-cb16-4921-b3e0-187b17b17a35",
			"355524e5-2b6e-4c43-8d2c-8f80ad4dce6b"
		), true),
		LABYCHATREPLY(Type.ADDON, Set.of(
			"d094478b-1034-4f20-b724-6ca5a122397e",
			"cd51d18d-2f46-45dc-9f4c-c2794a56a4d6",
			"6ac5c905-9ef8-4751-9d4c-de5d051b24f5"
		), true),
		LABYINSTAGRAM(Type.ADDON, Set.of(
			"d0ed1f6a-2811-4113-819f-495078d23915"
		), true),
		LABYTIKTOK(Type.ADDON, Set.of(
			"f7428c84-75e6-496a-87e4-dc5f22759fc8"
		), true),
		LABYTWITTER(Type.ADDON, Set.of(
			"b57b064e-5eb6-441b-92d8-5dd6d2bfbd58",
			"8f928aca-94f4-40c3-927e-86eeaadd384f"
		), true),
		LABYS_KEYSTROKES(Type.ADDON, Set.of(
			"00525fd0-eab6-428a-8591-8c3f6eb411fd",
			"19a3fe1c-7187-471b-82ca-ab33f9afddeb",
			"17981daf-e6ae-44cc-8733-7a68e0e800f6"
		), true),
		LABYS_MINIMAP(Type.ADDON, Set.of(
			"5d77d5b6-bebc-45bf-82f2-336bba64b9b1",
			"6e6f4a6a-f817-4d7e-bfe4-4cc7104d4d38",
			"6289a31b-8914-4142-a77d-b087661e7f0a"
		), true),
		MINIGAMES(Type.ADDON, Set.of(
			"a67fd3ed-6a66-4a1e-8cfd-cba6ebd8576c",
			"0119dff5-29e3-4fa4-9104-de06a9e8dfde"
		), true),
		MOREPARTICLES(Type.ADDON, Set.of(
			"de053e2e-7a28-47bc-9d50-09ba2348a551",
			"3a289d2b-025b-487c-a096-5aca52fc22ef",
			"7f1a5def-a2c5-40d8-b63c-29ab6eea09ba"
		), true),
		MOTIONBLUR(Type.ADDON, Set.of(
			"3f18b7d9-04ee-4e9d-947d-ebe38b7c84d2",
			"00f3ef82-a6a4-47c3-a4df-ae8750dc6a39"
		), true),
		NOBOB(Type.ADDON, Set.of(
			"2f36cb0e-a7d0-42db-8475-dabcc561043e",
			"d97b993d-85a4-4fb5-8b93-904fa8467022",
			"b614b841-cc86-4766-a71e-be316fa1ace8"
		), true),
		OPTIFINE(Type.ADDON, Set.of(
			"2cc09032-995f-4b57-a2a1-f1399addbb21",
			"8d08e37e-c2cd-4fd8-b9e9-7d0a8ab0059c",
			"7d62bffd-fe3f-4667-8200-e8decb384fa0"
		), true),
		PINGTAG(Type.ADDON, Set.of(
			"8aaf5651-10e3-4753-a39b-a462504b2300"
		), true),
		RADIOBOTSEU(Type.ADDON, Set.of(
			"ef7f3e2d-0cec-4435-aac4-2af84ab32b4a"
		), true),
		RAINBOW_GUI(Type.ADDON, Set.of(
			"376936ee-9dd3-476e-be84-4147dbc17baf",
			"ba85252b-a452-458a-8c11-cac5e1034773",
			"2485081f-b50a-47b8-82ed-6aed3952f8ab"
		), true),
		RESOURCEPACKS24(Type.ADDON, Set.of(
			"9e2b576c-3153-4b1d-a844-c43060c42cdf",
			"510bc550-38ce-4738-ad11-4f5b63d9c0a3",
			"0705124b-7659-44a2-b008-45f8850b62f8"
		), true),
		SEND_TO_SERVER(Type.ADDON, Set.of(
			"24d1f048-f85f-4380-9207-0af65fdc43ed",
			"b64c9238-4c58-4134-9913-78afbf83a4ca"
		), true),
		SETTINGS_PROFILE_MOD(Type.ADDON, Set.of(
			"df1be19c-8536-11e7-bb31-be2e44b06b34",
			"9a1c1d16-c8b9-4113-a2f5-e1e1e25e0ab5",
			"c49001df-fe56-4eee-9f92-879603412843"
		), true),
		SHINYPOTS(Type.ADDON, Set.of(
			"77be2777-f908-4a3f-863c-2149fd4bd741",
			"0ab4f35d-6f01-47b0-a972-093352af3a74"
		), true),
		SKINSTEALER(Type.ADDON, Set.of(
			"0fd555c3-7cc8-4ef1-9100-9278d426e23f"
		), true),
		SODIUM(Type.ADDON, Set.of(
			"b556b1f3-e5b7-4df2-a95b-313110a9819e"
		), true),
		SOUNDPLAYER(Type.ADDON, Set.of(
			"989ee076-ded2-497f-acd4-3c3f7caeec6d",
			"38dd0486-8fb4-45af-bbd4-7f68efca274a"
		), true),
		SPOTIFY(Type.ADDON, Set.of(
			"1160b0a4-9aec-44cb-835b-da2b6cd684a9",
			"cc066c3b-12ff-478f-8391-f57fad6724aa",
			"01687f50-baee-4af9-9123-9e1c680f2d9d"
		), true),
		STREAMCHAT_PLUS(Type.ADDON, Set.of(
			"c7f7055f-443c-4160-8714-a172ab8f74b5"
		), true),
		SUPERPAY(Type.ADDON, Set.of(
			"284cdc95-0ff6-4335-a9cb-f7b06e0ba8be"
		), true),
		TEAMSPEAK(Type.ADDON, Set.of(
			"37efd9cd-0024-4fff-83ac-47beca5cccd8",
			"1ce6f1db-01fa-4d05-ba90-343c953e76b0",
			"f99fe3da-bc9b-4ff3-8bb1-27765c31aecf"
		), true),
		TNT_TIMER(Type.ADDON, Set.of(
			"abd295f8-4483-42d6-8c6e-be845a8e20cb",
			"0f0ed529-c073-4c2a-bc8b-2c9194fce73a",
			"0cf09c27-b0a1-4d91-85c7-625f26d10701"
		), true),
		TOGGLESNEAK(Type.ADDON, Set.of(
			"a71154bc-8536-11e7-bb31-be2e44b06b34",
			"8218ec9a-6ad6-4518-a629-16046f9a24fb",
			"ba6021d6-bb1a-449b-b59a-680e494a08b8"
		), true),
		TOGGLETAB(Type.ADDON, Set.of(
			"0cb59a93-6a59-423f-9172-284f9d3024ff"
		), true),
		TOOLBREAK_WARNING(Type.ADDON, Set.of(
			"102b3d9f-c1e0-4bb9-8d8d-641ad4122c9d"
		), true),
		TRANSLUCENT_SKINS(Type.ADDON, Set.of(
			"d9468870-331b-4597-8ca9-5b600cd59710"
		), true),
		VOICE_CHAT(Type.ADDON, Set.of(
			"43152d5b-ca80-4b29-8f48-39fd63e48dee",
			"55c0c094-b79e-4c1b-bfb8-1a9f947c3314",
			"24c0644d-ad56-4609-876d-6e9da3cc9794"
		), true),
		WORLDEDITCUI(Type.ADDON, Set.of(
			"c9853888-8536-11e7-bb31-be2e44b06b34",
			"fee2a522-3769-4af7-bc40-690aa42947ec"
		), true);
		
	    private final Type type;
		private final Set<String> uuids;
	    private final boolean defaultEnabled;
	    
	    Features(Type type, boolean defaultEnabled) {
	    	this(type, Set.of(), defaultEnabled);
	    }
	    
	    public Feature allow() {
	    	return new Feature(this, true);
	    }
	    
	    public Feature deny() {
	    	return new Feature(this, false);
	    }
	    
	    public Feature withDefault() {
	    	return new Feature(this, defaultEnabled);
	    }
	}
	
	public record Feature(Features feature, boolean enabled) {}
	
	public static void setFeatures(@NonNull UUID uuid, Feature... features) {
		JsonObject permissions = new JsonObject();
		JsonObject addons = new JsonObject();
		Boolean damageIndicator = null;
		
		for (Feature feature : features) {
			Features f = feature.feature();
			switch (f.getType()) {
				case PERMISSION -> permissions.addProperty(f.name(), feature.enabled());
				case ADDON -> {
					if (f == Features.DAMAGEINDICATOR) {
						damageIndicator = feature.enabled();
					}
					
					for (String addon : f.getUuids()) {
						JsonObject obj = new JsonObject();
						obj.addProperty("allowed", feature.enabled());
						addons.add(addon, obj);
					}
				}
			}
		}
		
		if (permissions.size() > 0) {
			LabyModProtocol.sendMessage(uuid, "PERMISSIONS", permissions);
		}
		if (addons.size() > 0) {
			LabyModProtocol.sendMessage(uuid, "addon_permissions", addons);
		}
		if (damageIndicator != null) {
			FalchusLibMinecraft.get().sendMessage(uuid, LabyModMessage.damageindicator, new byte[] {
				(byte) (damageIndicator ? 1 : 0)
			});
		}
	}
}

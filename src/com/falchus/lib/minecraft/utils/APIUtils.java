package com.falchus.lib.minecraft.utils;

import java.util.UUID;

import com.falchus.lib.utils.http.HTTPRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class APIUtils {

	/**
	 * Gets the UUID of a player.
	 * 
	 * @return the player's {@link UUID}
	 */
    public static UUID getUUID(@NonNull String username) {
    	String body = HTTPRequest.get("https://api.mojang.com/users/profiles/minecraft/" + username);
        if (body == null) return null;

    	JsonObject json = new JsonParser().parse(body).getAsJsonObject();
    	String id = json.get("id").getAsString();
    	
        String uuid = id.replaceFirst(
            "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w+)",
            "$1-$2-$3-$4-$5"
        );
        return UUID.fromString(uuid);
    }
    
    /**
     * Gets the name of a player by UUID.
     * 
     * @return the player's username {@link String}
	 */
    public static String getName(@NonNull String uuid) {
        String body = HTTPRequest.get("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.replace("-", ""));
        if (body == null) return null;

    	JsonObject json = new JsonParser().parse(body).getAsJsonObject();
    	return json.get("name").getAsString();
    }
}
package com.falchus.lib.minecraft.utils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * Utility class for Minecraft-related API calls.
 */
@UtilityClass
public class APIUtils {
	
	private static final HttpClient client = HttpClient.newHttpClient();

	/**
	 * Retrieves the UUID of a Minecraft player.
	 */
    public static UUID getUUID(@NonNull String username) {
        try {
        	HttpRequest request = HttpRequest.newBuilder()
        			.uri(URI.create("https://api.mojang.com/users/profiles/minecraft/" + username))
        			.build();

        	HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
            	JsonObject json = new JsonParser().parse(response.body()).getAsJsonObject();
            	String id = json.get("id").getAsString();
            	
                String uuid = id.replaceFirst(
                    "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w+)",
                    "$1-$2-$3-$4-$5"
                );
                return UUID.fromString(uuid);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Retrieves the name of a Minecraft player by UUID.
	 */
    public static String getName(@NonNull String uuid) {
        try {
        	HttpRequest request = HttpRequest.newBuilder()
        			.uri(URI.create("https://sessionserver.mojang.com/session/minecraft/profile/" + uuid.replace("-", "")))
        			.build();
        	
        	HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
            	JsonObject json = new JsonParser().parse(response.body()).getAsJsonObject();
            	String name = json.get("name").getAsString();
            	
            	return name;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
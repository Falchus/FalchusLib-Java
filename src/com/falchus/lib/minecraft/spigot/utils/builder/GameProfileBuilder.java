package com.falchus.lib.minecraft.spigot.utils.builder;

import com.falchus.lib.cache.Cache;
import com.falchus.lib.task.Promise;
import com.falchus.lib.utils.http.HTTPRequest;
import com.google.gson.*;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.util.UUIDTypeAdapter;

import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class GameProfileBuilder {

    private static final String url = "https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false";
    private static final String jsonSkin = "{\"timestamp\":%d,\"profileId\":\"%s\",\"profileName\":\"%s\",\"isPublic\":true,\"textures\":{\"SKIN\":{\"url\":\"%s\"}}}";
    private static final String jsonCape = "{\"timestamp\":%d,\"profileId\":\"%s\",\"profileName\":\"%s\",\"isPublic\":true,\"textures\":{\"SKIN\":{\"url\":\"%s\"},\"CAPE\":{\"url\":\"%s\"}}}";

    private static final Gson gson = new GsonBuilder().disableHtmlEscaping().registerTypeAdapter(UUID.class, new UUIDTypeAdapter()).registerTypeAdapter(GameProfile.class, new GameProfileSerializer()).registerTypeAdapter(PropertyMap.class, new PropertyMap.Serializer()).create();
    private static final Cache<UUID, GameProfile> cache = Cache.of()
    		.maxSize(5000);
    
    /**
     * Don't run in main thread!
     * <p>
     * Fetches the GameProfile from the Mojang servers
     *
     * @param uuid	The player uuid
     * @return {@link GameProfile}
     */
    public static Promise<GameProfile> fetch(UUID uuid) {
        return fetch(uuid, false);
    }

    /**
     * Don't run in main thread!
     * <p>
     * Fetches the GameProfile from the Mojang servers
     *
     * @param uuid		The player uuid
     * @param forceNew	If true the cache is ignored
     * @return {@link GameProfile}
     */
    public static Promise<GameProfile> fetch(UUID uuid, boolean forceNew) {
    	if (forceNew) {
    		cache.remove(uuid);
    	}
    	
    	GameProfile hit = cache.getIfPresent(uuid);
    	if (hit != null) return Promise.of(hit);
    	
        return HTTPRequest.get(String.format(url, UUIDTypeAdapter.fromUUID(uuid))).thenApply(json -> {
        	if (json == null) return null;
        	
        	GameProfile profile = gson.fromJson(json, GameProfile.class);
        	if (profile != null) {
        		cache.put(uuid, profile);
        	}
        	return profile;
        });
    }

    /**
     * Builds a GameProfile for the specified args
     *
     * @param uuid 	The uuid
     * @param name 	The name
     * @param skin 	The url from the skin image
     * @return A {@link GameProfile} built from the arguments
     */
    public static GameProfile getProfile(UUID uuid, String name, String skin) {
        return getProfile(uuid, name, skin, null);
    }

    /**
     * Builds a GameProfile for the specified args
     *
     * @param uuid		The uuid
     * @param name		The name
     * @param skinUrl	Url from the skin image
     * @param capeUrl	Url from the cape image
     * @return A {@link GameProfile} built from the arguments
     */
    public static GameProfile getProfile(UUID uuid, String name, String skinUrl, String capeUrl) {
        GameProfile profile = new GameProfile(uuid, name);
        boolean cape = capeUrl != null && !capeUrl.isEmpty();

        List<Object> args = new ArrayList<>();
        args.add(System.currentTimeMillis());
        args.add(UUIDTypeAdapter.fromUUID(uuid));
        args.add(name);
        args.add(skinUrl);
        if (cape) {
        	args.add(capeUrl);
        }

        profile.getProperties().put("textures", new Property("textures", Base64Coder.encodeString(String.format(cape ? jsonCape : jsonSkin, args.toArray(new Object[0])))));
        return profile;
    }

    /**
     * Sets the time as long as you want to keep the gameprofiles in cache (-1 = never remove it)
     *
     * @param	time cache time (default = -1)
     */
    public static void setCacheTime(long time) {
    	cache.expireAfterWrite(time < 0 ? 0 : time, TimeUnit.MILLISECONDS);
    }

    private static class GameProfileSerializer implements JsonSerializer<GameProfile>, JsonDeserializer<GameProfile> {

        public GameProfile deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            JsonObject object = (JsonObject) json;
            UUID id = object.has("id") ? (UUID) context.deserialize(object.get("id"), UUID.class) : null;
            String name = object.has("name") ? object.getAsJsonPrimitive("name").getAsString() : null;
            GameProfile profile = new GameProfile(id, name);

            if (object.has("properties")) {
                for (Entry<String, Property> prop : ((PropertyMap) context.deserialize(object.get("properties"), PropertyMap.class)).entries()) {
                    profile.getProperties().put(prop.getKey(), prop.getValue());
                }
            }
            return profile;
        }

        public JsonElement serialize(GameProfile profile, Type type, JsonSerializationContext context) {
            JsonObject result = new JsonObject();
            if (profile.getId() != null) {
                result.add("id", context.serialize(profile.getId()));
            }
            if (profile.getName() != null) {
                result.addProperty("name", profile.getName());
            }
            if (!profile.getProperties().isEmpty()) {
                result.add("properties", context.serialize(profile.getProperties()));
            }
            return result;
        }

    }
}
package com.falchus.lib.minecraft.utils.client.lunar;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;

import com.falchus.lib.minecraft.FalchusLibMinecraft;
import com.google.common.collect.Table;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class LunarJsonPacketUtil {
	
	public static void sendMessage(@NonNull UUID uuid, @NonNull JsonObject message) {
	    FalchusLibMinecraft.get().sendMessage(uuid, LunarMessage.apolloJson, message.toString().getBytes());
	}
	
	public static void broadcastPacket(@NonNull JsonObject message) {
	    Bukkit.getOnlinePlayers().forEach(player -> sendMessage(player.getUniqueId(), message));
	}
	
	public static JsonObject createEnableModuleObject(@NonNull String module, Map<String, Object> properties) {
	    JsonObject enableModuleObject = new JsonObject();
	    enableModuleObject.addProperty("apollo_module", module);
	    enableModuleObject.addProperty("enable", true);
	 
	    if (properties != null) {
	        JsonObject propertiesObject = new JsonObject();
	        for (Map.Entry<String, Object> entry : properties.entrySet()) {
	            propertiesObject.add(entry.getKey(), convertToJsonElement(entry.getValue()));
	        }
	 
	        enableModuleObject.add("properties", propertiesObject);
	    }
	 
	    return enableModuleObject;
	}
	
	public static void enableModules(@NonNull UUID uuid, @NonNull List<String> modules, Table<String, String, Object> moduleProperties) {
	    JsonArray settings = modules.stream()
	        .map(module -> createEnableModuleObject(module, moduleProperties.row(module)))
	        .collect(JsonArray::new, JsonArray::add, JsonArray::addAll);
	 
	    JsonObject message = new JsonObject();
	    message.addProperty("@type", "type.googleapis.com/lunarclient.apollo.configurable.v1.OverrideConfigurableSettingsMessage");
	    message.add("configurable_settings", settings);
	 
	    sendMessage(uuid, message);
	}
	
	@SuppressWarnings("rawtypes")
	private static JsonElement convertToJsonElement(Object value) {
        switch (value) {
            case null -> {
                return JsonNull.INSTANCE;
            }
            case String str -> {
                return new JsonPrimitive(str);
            }
            case Number num -> {
                return new JsonPrimitive(num);
            }
            case Boolean bool -> {
                return new JsonPrimitive(bool);
            }
            case List list -> {
                JsonArray array = new JsonArray();
                for (Object item : list) {
                    array.add(convertToJsonElement(item));
                }
                return array;
            }
            
            default -> throw new RuntimeException("Unable to wrap value of type '" + value.getClass().getSimpleName() + "'!");
        }
	}
}

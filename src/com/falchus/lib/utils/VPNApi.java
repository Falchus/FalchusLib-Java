package com.falchus.lib.utils;

import com.falchus.lib.utils.http.HTTPRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

/**
 * Utility class for checking if an IP is a VPN.
 */
@UtilityClass
public class VPNApi {

	/**
	 * Checks if the given IP is a VPN.
	 * via vpnapi.io
	 * 
	 * @return {@code true} if it is, {@code false} otherwise.
	 */
	public static boolean isVPN(@NonNull String ip, String apiKey) {
		String url = "https://vpnapi.io/api/" + ip;
		if (apiKey != null && !apiKey.isEmpty()) {
			url += "?key=" + apiKey;
		}
		
		String body = HTTPRequest.get(url);
		if (body == null) return false;
		
		JsonObject json = new JsonParser().parse(body).getAsJsonObject();
		return json.has("security") &&
				json.getAsJsonObject("security").has("vpn") &&
				json.getAsJsonObject("security").get("vpn").getAsBoolean();
	}
}

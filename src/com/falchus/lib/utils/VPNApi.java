package com.falchus.lib.utils;

import com.falchus.lib.task.Promise;
import com.falchus.lib.utils.http.HTTPRequest;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VPNApi {

	/**
	 * Checks if the given IP is a VPN.
	 * via vpnapi.io
	 * 
	 * @return {@code true} if it is, {@code false} otherwise.
	 */
	public static Promise<Boolean> isVPN(@NonNull String ip, String apiKey) {
		String url = "https://vpnapi.io/api/" + ip;
		if (apiKey != null && !apiKey.isEmpty()) {
			url += "?key=" + apiKey;
		}
		
		return HTTPRequest.get(url).thenApply(body -> {
			if (body == null) return false;
			
			JsonObject json = JsonParser.parseString(body).getAsJsonObject();
			return json.has("security") &&
					json.getAsJsonObject("security").has("vpn") &&
					json.getAsJsonObject("security").get("vpn").getAsBoolean();
		});
	}
}

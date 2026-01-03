package com.falchus.lib.utils.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HTTPRequest {

	private static final HttpClient client = HttpClient.newHttpClient();
	
	/**
	 * @return the response body as a {@link String}, or {@code null}
	 */
	public static String get(@NonNull String url) {
		try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create(url))
					.GET()
					.build();
			
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) return null;
            
            return response.body();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

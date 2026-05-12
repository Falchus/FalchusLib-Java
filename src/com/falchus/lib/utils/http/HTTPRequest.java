package com.falchus.lib.utils.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HTTPRequest {

	private static final HttpClient client = HttpClient.newHttpClient();
	
	public static String get(@NonNull String url, Map<String, String> headers) {
		return request("GET", url, headers, null);
	}
	
	public static String get(@NonNull String url) {
		return get(url, null);
	}
	
	public static String post(@NonNull String url, Map<String, String> headers, String body) {
		return request("POST", url, headers, body);
	}
	
	public static String post(@NonNull String url, String body) {
		return post(url, null, body);
	}
	
	public static String put(@NonNull String url, Map<String, String> headers, String body) {
		return request("PUT", url, headers, body);
	}
	
	public static String put(@NonNull String url, String body) {
		return put(url, null, body);
	}
	
	public static String delete(@NonNull String url, Map<String, String> headers) {
		return request("DELETE", url, headers, null);
	}
	
	public static String delete(@NonNull String url) {
		return delete(url, null);
	}
	
	public static String head(@NonNull String url, Map<String, String> headers) {
		return request("HEAD", url, headers, null);
	}
	
	public static String head(@NonNull String url) {
		return head(url, null);
	}
	
	public static String request(@NonNull String method, @NonNull String url, Map<String, String> headers, String body) {
		try {
			Builder builder = HttpRequest.newBuilder()
					.uri(URI.create(url));
			
			if (headers != null) {
				headers.forEach(builder::header);
			}
			
			BodyPublisher publisher = body == null
				? BodyPublishers.noBody()
				: BodyPublishers.ofString(body);
			switch (method.toUpperCase()) {
				case "POST":
					builder.POST(publisher);
					break;
				case "PUT":
					builder.PUT(publisher);
					break;
				case "DELETE":
					builder.method("DELETE", publisher);
					break;
				case "HEAD":
					builder.HEAD();
					break;
					
				case "GET":
				default:
					builder.GET();
					break;
			}
			
			HttpRequest request = builder.build();
			
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			int code = response.statusCode();
			if (code < 200 || code >= 300) return null;
			
            return response.body();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

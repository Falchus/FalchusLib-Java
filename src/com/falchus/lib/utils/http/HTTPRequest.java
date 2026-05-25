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
import java.util.concurrent.ConcurrentHashMap;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HTTPRequest {

	private static final HttpClient client = HttpClient.newHttpClient();
	
	private static final Map<String, String> etags = new ConcurrentHashMap<>();
	private static final Map<String, String> cache = new ConcurrentHashMap<>();
	
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
			
			method = method.toUpperCase();
			String activeEtag = etags.get(url);
			
			BodyPublisher publisher = body == null
					? BodyPublishers.noBody()
					: BodyPublishers.ofString(body);
			switch (method) {
				case "POST":
					builder.POST(publisher);
					break;
				case "PUT":
					if (activeEtag != null) builder.header("If-Match", activeEtag);
					builder.PUT(publisher);
					break;
				case "DELETE":
					if (activeEtag != null) builder.header("If-Match", activeEtag);
					builder.method("DELETE", publisher);
					break;
				case "HEAD":
					builder.HEAD();
					break;
					
				case "GET":
				default:
					if (activeEtag != null) builder.header("If-None-Match", activeEtag);
					builder.GET();
					break;
			}
			
			HttpRequest request = builder.build();
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
			int code = response.statusCode();
			if (code == 304) {
				return cache.getOrDefault(url, "{}");
			}
			if (code < 200 || code >= 300) return null;
			
			String responseBody = response.body();
			if (method.equals("GET") || method.equals("PUT")) {
				response.headers().map().entrySet().stream()
					.filter(entry -> entry.getKey().equalsIgnoreCase("etag"))
					.map(entry -> entry.getValue().getFirst())
					.findFirst()
					.ifPresent(etag -> {
						etags.put(url, etag);
						cache.put(url, responseBody);
					});
			} else if (method.equals("DELETE")) {
				etags.remove(url);
				cache.remove(url);
			}
			
            return responseBody;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}

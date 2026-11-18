package com.falchus.lib.utils.http;

import java.net.Proxy;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.falchus.lib.task.Promise;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class HTTPRequest {

	private static final ThreadLocal<Proxy> requestProxy = new ThreadLocal<>();
	private static volatile HttpClient client = HttpClient.newHttpClient();
	
	private static final Map<String, String> etags = new ConcurrentHashMap<>();
	private static final Map<String, String> cache = new ConcurrentHashMap<>();
	
	private static final Pattern etagJsonPattern = Pattern.compile("\"etag\"\\s*:\\s*\"([^\"]+)\"", Pattern.CASE_INSENSITIVE);

	public static void setClient(HttpClient httpClient) {
		client = httpClient == null ? HttpClient.newHttpClient() : httpClient;
	}

	public static void setRequestProxy(Proxy proxy) {
		if (proxy == null) requestProxy.remove();
		else requestProxy.set(proxy);
	}

	public static Proxy getRequestProxy() {
		return requestProxy.get();
	}
	
	public static Promise<HTTPResponse> get(@NonNull String url, Map<String, String> headers) {
		return request("GET", url, headers, null);
	}
	
	public static Promise<HTTPResponse> get(@NonNull String url) {
		return get(url, null);
	}
	
	public static Promise<HTTPResponse> post(@NonNull String url, Map<String, String> headers, String body) {
		return request("POST", url, headers, body);
	}
	
	public static Promise<HTTPResponse> post(@NonNull String url, String body) {
		return post(url, null, body);
	}
	
	public static Promise<HTTPResponse> put(@NonNull String url, Map<String, String> headers, String body) {
		return request("PUT", url, headers, body);
	}
	
	public static Promise<HTTPResponse> put(@NonNull String url, String body) {
		return put(url, null, body);
	}
	
	public static Promise<HTTPResponse> delete(@NonNull String url, Map<String, String> headers) {
		return request("DELETE", url, headers, null);
	}
	
	public static Promise<HTTPResponse> delete(@NonNull String url) {
		return delete(url, null);
	}
	
	public static Promise<HTTPResponse> head(@NonNull String url, Map<String, String> headers) {
		return request("HEAD", url, headers, null);
	}
	
	public static Promise<HTTPResponse> head(@NonNull String url) {
		return head(url, null);
	}
	
	public static Promise<HTTPResponse> request(@NonNull String method, @NonNull String url, Map<String, String> headers, String body) {
		Proxy proxy = requestProxy.get();
		return Promise.supplyAsync(() -> {
			if (proxy != null) requestProxy.set(proxy);
			try {
				Builder builder = HttpRequest.newBuilder()
						.uri(URI.create(url));
				
				if (headers != null) {
					headers.forEach(builder::header);
				}
				
				String m = method.toUpperCase();
				String activeEtag = etags.get(url);
				
				BodyPublisher publisher = body == null
						? BodyPublishers.noBody()
						: BodyPublishers.ofString(body);
				switch (m) {
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
				
				long start = System.nanoTime();
				HttpResponse<String> response = client.send(builder.build(), BodyHandlers.ofString());
				long latencyMs = (System.nanoTime() - start) / 1_000_000;
				int code = response.statusCode();
				String responseBody = response.body();

				if (code == 304) {
					return new HTTPResponse(response, latencyMs, cache.getOrDefault(url, "{}"));
				}

				if (code >= 200 && code < 300) {
					if (m.equals("GET") || m.equals("PUT")) {
						String etag = response.headers().map().entrySet().stream()
							.filter(entry -> entry.getKey().equalsIgnoreCase("etag"))
							.map(entry -> entry.getValue().getFirst())
							.findFirst()
							.orElse(null);
						if (etag == null && responseBody != null) {
							Matcher matcher = etagJsonPattern.matcher(responseBody);
							if (matcher.find()) {
								etag = matcher.group(1);
							}
						}
						
						if (etag != null) {
							etags.put(url, etag);
							cache.put(url, responseBody);
						}
					} else if (m.equals("DELETE")) {
						etags.remove(url);
						cache.remove(url);
					}
				}

				return new HTTPResponse(response, latencyMs);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				requestProxy.remove();
			}
		});
	}
}

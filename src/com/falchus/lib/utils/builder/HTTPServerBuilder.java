package com.falchus.lib.utils.builder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.function.BiConsumer;

import com.falchus.lib.utils.http.HTTPServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import lombok.NonNull;

public class HTTPServerBuilder {
	
	private int port = 8080;
	
    private final Map<String, BiConsumer<HttpExchange, Map<String, String>>> routes = new ConcurrentHashMap<>();
    private BiConsumer<HttpExchange, Map<String, String>> defaultHandler;
    
    public HTTPServerBuilder port(int port) {
    	this.port = port;
    	return this;
    }
    
    /**
     * Adds a route.
     * {params} in paths!
     */
    public HTTPServerBuilder route(@NonNull String path, @NonNull BiConsumer<HttpExchange, Map<String, String>> handler) {
        routes.put(path, handler);
        return this;
    }
    
    /**
     * Sets a default handler (used if no routes match).
     */
    public HTTPServerBuilder defaultHandler(@NonNull BiConsumer<HttpExchange, Map<String, String>> handler) {
        this.defaultHandler = handler;
        return this;
    }
    
    /**
     * @param ip	usually 127.0.0.1
     */
    public HTTPServer build(@NonNull String ip) {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(ip, port), 0);

            server.createContext("/", exchange -> {
            	String path = URLDecoder.decode(exchange.getRequestURI().getPath(), StandardCharsets.UTF_8);
        		String[] pathParts = path.split("/");
            	for (Map.Entry<String, BiConsumer<HttpExchange, Map<String, String>>> entry : routes.entrySet()) {
            		String[] routeParts = entry.getKey().split("/");
            		if (routeParts.length != pathParts.length) continue;
            		
            		Map<String, String> params = new HashMap<>();
            		boolean matched = true;
            		
            		for (int i = 0; i < routeParts.length; i++) {
            			String routePart = routeParts[i];
            			String pathPart = pathParts[i];
            			if (routePart.startsWith("{") && routePart.endsWith("}")) {
            				String param = routePart.substring(1, routePart.length() - 1);
            				if (pathPart == null || pathPart.isEmpty()) {
            					matched = false;
            					break;
            				}
            				
            				params.put(param, pathPart);
            				continue;
            			}
            			
            			if (!routePart.equals(pathPart)) {
            				matched = false;
            				break;
            			}
            		}
            		if (!matched) continue;
            		
            		String query = exchange.getRequestURI().getRawQuery();
            		if (query != null && !query.isEmpty()) {
            			for (String pair : query.split("&")) {
            				String[] kv = pair.split("=", 2);
            				String k = URLDecoder.decode(kv[0], StandardCharsets.UTF_8);
            				String v = kv.length > 1
            						? URLDecoder.decode(kv[1], StandardCharsets.UTF_8)
    								: "";
            				params.put(k, v);
            			}
            		}
            		entry.getValue().accept(exchange, params);
            		return;
            	}
            	
                if (defaultHandler != null) {
                    defaultHandler.accept(exchange, new HashMap<>());
                } else {
                    HTTPServer.sendText(exchange, "Not Found", 404);
                }
            });

            server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
            return new HTTPServer(server);
        } catch (IOException e) {
            throw new RuntimeException("Failed to start HTTP server", e);
        }
    }
}

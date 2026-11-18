package com.falchus.lib.utils.http;

import java.net.http.HttpResponse;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Delegate;

@Getter
@AllArgsConstructor
public final class HTTPResponse implements HttpResponse<String> {

	@Delegate private final HttpResponse<String> response;
	private final long latencyMs;
	private final String body;
	
	public HTTPResponse(HttpResponse<String> response, long latencyMs) {
		this(response, latencyMs, null);
	}
	
	public boolean isSuccess() {
		int status = statusCode();
		return status >= 200 && status < 300;
	}
	
	public boolean isOk() {
		return isSuccess() && body() != null;
	}

	public Optional<String> header(String name) {
		return headers().firstValue(name);
	}
	
	@Override
	public String body() {
		return body != null ? body : response.body();
	}
}

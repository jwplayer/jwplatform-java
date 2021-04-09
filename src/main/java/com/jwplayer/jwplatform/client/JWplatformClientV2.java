package com.jwplayer.jwplatform.client;

import java.util.Map;

public abstract class JWplatformClientV2 {
	protected Map<String,String> headers;
	
	/**
	 * Add custom/additional headers
	 * @param key
	 * @param value
	 */
	public void addHeader(String key, String value) {
		headers.put(key, value);
	}
	
	/**
	 * Remove a header
	 * @param key
	 */
	public void removeHeader(String key) {
		headers.remove(key);
	}
}

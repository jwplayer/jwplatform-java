package com.jwplayer.jwplatform.client;

import java.util.Map;

/**
 * 
 * @author smurthy Base JWPlatformClient class for V2 providing common
 *         functionalities shared by V2 clients.
 */
public abstract class JWPlatformClientV2 {
	protected Map<String, String> headers;

	/**
	 * Add custom/additional headers
	 * 
	 * @param key   - Custom alphanumeric key
	 * @param value - Custom alphanumeric value
	 */
	public void addHeader(String key, String value) {
		headers.put(key, value);
	}

	/**
	 * Remove a header
	 * 
	 * @param key Custom alphanumeric key
	 */
	public void removeHeader(String key) {
		headers.remove(key);
	}
}

package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Playlists API client.
 *
 * <p>An API client for the JW Platform Media API. For the API documentation see:
 * https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2
 *
 * <p>Example:
 *    PlaylistsClient client = PlaylistsClient.getClient(secret);
 */
public class PlaylistsClient {

	private String path;	
	private final String secret;
	private static Map<String,String> headers;
	
	/**
	   * Instantiate a new {@code PlaylistsClient} instance.
	   *
	   * @param secret - your api secret
	   */
	private PlaylistsClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/playlists/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}
	
	/**
	   * see {@link #PlaylistsClient(String)}.
	   */
	public static PlaylistsClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new PlaylistsClient(secret);
	}
	
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
	
	public JSONObject listPlaylists(String siteId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	
}

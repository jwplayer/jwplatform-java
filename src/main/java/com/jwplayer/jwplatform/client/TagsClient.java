package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Tags API client.
 *
 * <p>
 * An API client for the JW Platform Tags API. For the API documentation
 * see: <a href=
 * "https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2">Introduction
 * to api v2</a>
 *
 * <p>
 * Example: TagsClient client = TagsClient.getClient(secret);
 */

public class TagsClient extends JWPlatformClientV2 {
	private String path;
	private final String secret;

	/**
	 * Instantiate a new {@code TagsClient} instance.
	 *
	 * @param secret - your api secret
	 */
	private TagsClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}

	/**
	 * see {@link #TagsClient(String)}.
	 */
	public static TagsClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new TagsClient(secret);
	}
	
	/**
	 * 
	 * @param siteId - PropertyID
	 * @param params - Parameters to be included in the request
	 * @return JSON response from Tags APIs
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/put_v2-sites-site-id-remove-tag"</a>
	 */
	public JSONObject bulkRemoveTag(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId)+"remove_tag/";
		return HttpCalls.request(this.path, bodyParams, false, "PUT", headers);
	}
	
	/**
	 * 
	 * @param siteId - PropertyID
	 * @param params - Parameters to be included in the request
	 * @return JSON response from Tags API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/put_v2-sites-site-id-rename-tag"</a>
	 */
	public JSONObject bulkRenameTag(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId)+"rename_tag/";
		return HttpCalls.request(this.path, bodyParams, false, "PUT", headers);
	}

}

package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Usage API client.
 *
 * <p>
 * An API client for the JW Platform Usage API. For the API documentation
 * see: <a href=
 * "https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2">Introduction
 * to api v2</a>
 *
 * <p>
 * Example: UsageClient client = UsageClient.getClient(secret);
 */

public class UsageClient extends JWPlatformClientV2 {
	private String path;
	private final String secret;

	/**
	 * Instantiate a new {@code UsageClient} instance.
	 *
	 * @param secret - your api secret
	 */
	private UsageClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/";
		headers = new HashMap<>();
		headers.put("Authorization", this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}

	/**
	 * see {@link #UsageClient(String)}.
	 */
	public static UsageClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new UsageClient(secret);
	}
	
	/**
	 * 
	 * @param bodyParams - Parameters to be included in the request
	 * @return JSON response from Usage API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/put_v2-query-usage">Query Account Usage</a>
	 */
	public JSONObject queryAccountUsage(Map<String, String> bodyParams) throws JWPlatformException {
		this.path = this.path+ "query_usage/";
		return HttpCalls.request(this.path, bodyParams, false, "PUT", headers);
	}
	
	/**
	 * 
	 * @param siteId - PropertyID
	 * @param bodyParams - Parameters to be included in the request
	 * @return JSON response from Usage API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/put_v2-sites-site-id-query-usage">Query Site Usage</a>
	 */
	public JSONObject querySiteUsage(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = this.path+ "sites/"+ siteId+ "/query_usage/";
		return HttpCalls.request(this.path, bodyParams, false, "PUT", headers);
	}
}
	

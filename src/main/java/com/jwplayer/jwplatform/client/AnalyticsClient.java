package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Analytics API client.
 *
 * <p>
 * An API client for the JW Platform Analytics API. For the API documentation
 * see: <a href=
 * "https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2">Introduction
 * to api v2</a>
 *
 * <p>
 * Example: AnalyticsClient client = AnalyticsClient.getClient(secret);
 */
public class AnalyticsClient extends JWPlatformClientV2 {

	private String path;
	private final String secret;

	/**
	 * Instantiate a new {@code AnalyticsClient} instance.
	 *
	 * @param secret - your api secret
	 */
	private AnalyticsClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/analytics/queries/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}

	/**
	 * see {@link #AnalyticsClient(String)}.
	 */
	public static AnalyticsClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new AnalyticsClient(secret);
	}

	/**
	 * 
	 * @param siteId - Property ID
	 * @param source - Data set against which to run the request query, can be null
	 *               or empty, defaults to "default"
	 * @param format - File type of the response query output, can be null or empty,
	 *               defaults to "json"
	 * @param params - Parameters to be included in the request
	 * @return JSONObject from api call
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#run-a-query">
	 *                             Run Query </a>
	 */
	public JSONObject runQuery(String siteId, String source, String format, Map<String, String> params)
			throws JWPlatformException {
		if (source == null || source.equals("")) {
			source = "default";
		}
		if (format == null || format.equals("")) {
			format = "json";
		}
		this.path = String.format(path, siteId) + "?source=" + source + "&format=" + format;
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
}

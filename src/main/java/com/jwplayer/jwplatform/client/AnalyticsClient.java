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
 * <p>An API client for the JW Platform Analytics API. For the API documentation see:
 * https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2
 *
 * <p>Example:
 *    AnalyticsClient client = AnalyticsClient.getClient(secret);
 */
public class AnalyticsClient{

	private String path;
	private final String secret;
	private static Map<String,String> headers;
	
	/**
	   * Instantiate a new {@code AnalyticsClient} instance.
	   *
	   * @param secret - your api secret
	   */
	private AnalyticsClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/analytics/queries/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+this.secret);
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
	
	/**
	 * 
	 * @param siteId
	 * @param source - can be null or empty, defaults to "default"
	 * @param format - can be null or empty, defaults to "json"
	 * @param params
	 * @return JSONObject from api call
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#run-a-query
	 */
	public JSONObject runQuery(String siteId, String source, String format, Map<String,String> params) throws JWPlatformException {
		if(source==null || source.equals("")) source = "default";
		if(format==null || format.equals("")) format = "json";
		this.path = String.format(path, siteId)+"?source="+source+"&format="+format;
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
}
	
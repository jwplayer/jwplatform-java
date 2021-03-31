package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Events API client.
 *
 * <p>An API client for the JW Platform Webhooks API. For the API documentation see:
 * https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2
 *
 * <p>Example:
 *    WebhooksClient client = WebhooksClient.getClient(secret);
 */
public class WebhooksClient {

	private String path;
	private final String secret;
	private static Map<String,String> headers;
	
	/**
	   * Instantiate a new {@code WebhooksClient} instance.
	   *
	   * @param secret - your api secret
	   */
	private WebhooksClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/webhooks/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}
	
	/**
	   * see {@link #WebhooksClient(String)}.
	   */
	public static WebhooksClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new WebhooksClient(secret);
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
	 * @param params
	 * @return JSON response from Media API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-webhooks
	 */
	public JSONObject listWebhooks(Map<String, String> params) throws JWPlatformException {
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param bodyParams
	 * @return JSON response from Media API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-webhooks
	 */
	public JSONObject createWebhookResource(Map<String, String> bodyParams) throws JWPlatformException {
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}
	
	/**
	 * 
	 * @param webhookId
	 * @param params
	 * @return JSON response from Media API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-webhooks-webhook-id-
	 */
	public JSONObject retrieveWebhookById(String webhookId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(webhookId, "Webhook ID must not be null!");
		this.path = String.format(this.path)+webhookId+"/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param webhookId
	 * @return JSON response from Media API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-webhooks-webhook-id-
	 */
	public JSONObject deleteWebhook(String webhookId) throws JWPlatformException {
		Preconditions.checkNotNull(webhookId, "Webhook ID must not be null!");
		this.path = String.format(this.path)+webhookId+"/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}
	
	/**
	 * 
	 * @param webhookId
	 * @param bodyParams
	 * @return JSON response from Media API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-webhooks-webhook-id-
	 */
	public JSONObject updateWebhook(String webhookId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(webhookId, "Webhook ID must not be null!");
		this.path = String.format(this.path)+webhookId+"/";
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}
}

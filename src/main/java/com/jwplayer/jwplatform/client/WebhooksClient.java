package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Webhooks API client.
 *
 * <p>
 * An API client for the JW Platform Webhooks API. For the API documentation
 * see: <a href=
 * "https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2">Introduction
 * to api v2</a>
 *
 * <p>
 * Example: WebhooksClient client = WebhooksClient.getClient(secret);
 */
public class WebhooksClient extends JWPlatformClientV2 {

	private String path;
	private final String secret;

	/**
	 * Instantiate a new {@code WebhooksClient} instance.
	 *
	 * @param secret - your api secret
	 */
	private WebhooksClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/webhooks/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + this.secret);
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
	 * 
	 * @param params - Parameters to be included in the request
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-webhooks">List
	 *                             webhooks</a>
	 */
	public JSONObject listWebhooks(Map<String, String> params) throws JWPlatformException {
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-webhooks">Create
	 *                             webhook resource</a>
	 */
	public JSONObject createWebhookResource(Map<String, String> bodyParams) throws JWPlatformException {
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}

	/**
	 * 
	 * @param webhookId - Unique identifier for a resource
	 * @param params    - Parameters to be included in the request
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-webhooks-webhook-id-">Retrieve
	 *                             webhook by ID</a>
	 */
	public JSONObject retrieveWebhookById(String webhookId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(webhookId, "Webhook ID must not be null!");
		this.path = String.format(this.path) + webhookId + "/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param webhookId - Unique identifier for a resource
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-webhooks-webhook-id-">Delete
	 *                             webhook</a>
	 */
	public JSONObject deleteWebhook(String webhookId) throws JWPlatformException {
		Preconditions.checkNotNull(webhookId, "Webhook ID must not be null!");
		this.path = String.format(this.path) + webhookId + "/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}

	/**
	 * 
	 * @param webhookId  - Unique identifier for a resource
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-webhooks-webhook-id-">Update
	 *                             webhook</a>
	 */
	public JSONObject updateWebhook(String webhookId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(webhookId, "Webhook ID must not be null!");
		this.path = String.format(this.path) + webhookId + "/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}
}

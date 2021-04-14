package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform VPBConfigs API client.
 *
 * <p>
 * An API client for the JW Platform VPBConfigs API. For the API documentation
 * see: <a href=
 * "https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2">Introduction
 * to api v2</a>
 *
 * <p>
 * Example: VPBConfigsClient client = VPBConfigsClient.getClient(secret);
 */
public class VPBConfigsClient extends JWPlatformClientV2 {

	private String path;
	private final String secret;

	/**
	 * Instantiate a new {@code VPBConfigsClient} instance.
	 *
	 * @param secret - your api secret
	 */
	private VPBConfigsClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/advertising/vpb_configs/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}

	/**
	 * see {@link #VPBConfigsClient(String)}.
	 */
	public static VPBConfigsClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new VPBConfigsClient(secret);
	}

	/**
	 * 
	 * @param siteId - PropertyID
	 * @param params - Parameters to be included in the request
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-advertising-vpb-configs">List
	 *                             configs</a>
	 */
	public JSONObject listConfigs(String siteId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-advertising-vpb-configs">Create
	 *                             config</a>
	 */
	public JSONObject createConfig(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param configId   - Unique identifier for a resource
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-advertising-vpb-configs-config-id-">Update
	 *                             config</a>
	 */
	public JSONObject updateConfig(String siteId, String configId, Map<String, String> bodyParams)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(configId, "Config ID must not be null!");
		this.path = String.format(this.path, siteId) + configId + "/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}

	/**
	 * 
	 * @param siteId   - PropertyID
	 * @param configId - Unique identifier for a resource
	 * @param params   - Parameters to be included in the request
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-advertising-vpb-configs-config-id-">Get
	 *                             config by ID</a>
	 */
	public JSONObject getConfigById(String siteId, String configId, Map<String, String> params)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(configId, "Config ID must not be null!");
		this.path = String.format(this.path, siteId) + configId + "/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId   - PropertyID
	 * @param configId - Unique identifier for a resource
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-advertising-vpb-configs-config-id-">Delete
	 *                             config</a>
	 */
	public JSONObject deleteConfig(String siteId, String configId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(configId, "Config ID must not be null!");
		this.path = String.format(this.path, siteId) + configId + "/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#put_v2-sites-site-id-advertising-update-schedules-vpb-config">Update
	 *                             schedules</a>
	 */
	public JSONObject updateSchedules(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = "https://api.jwplayer.com/v2/sites/" + siteId + "/advertising/update_schedules_vpb_config/";
		return HttpCalls.request(this.path, bodyParams, false, "PUT", headers);
	}

}

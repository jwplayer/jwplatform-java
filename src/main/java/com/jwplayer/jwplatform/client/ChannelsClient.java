package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Channels API client.
 *
 * <p>
 * An API client for the JW Platform Channels API. For the API documentation
 * see: <a href=
 * "https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2">Introduction
 * to api v2</a>
 *
 * <p>
 * Example: ChannelsClient client = ChannelsClient.getClient(secret);
 */
public class ChannelsClient extends JWPlatformClientV2 {
	private String path;
	private final String secret;

	/**
	 * Instantiate a new {@code ChannelsClient} instance.
	 *
	 * @param secret - your api secret
	 */
	private ChannelsClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/channels/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}

	/**
	 * see {@link #ChannelsClient(String)}.
	 */
	public static ChannelsClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new ChannelsClient(secret);
	}

	/**
	 * 
	 * @param siteId - Property ID
	 * @param params -Parameters to be included in the request
	 * @return JSONObject response from api call
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-channels">List
	 *                             Channels</a>
	 */
	public JSONObject listChannels(String siteId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId     - Property ID
	 * @param bodyParams - Parameters to be included in the body of the request
	 * @return JSONObject response from api call
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-channels">
	 *                             Create Channel </a>
	 */
	public JSONObject createChannel(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}

	/**
	 * 
	 * @param siteId
	 * @param channelId - Live Channel ID
	 * @param params    - Parameters to be included in the request
	 * @return JSONObject response from api call
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-channels-channel-id-">
	 *                             Get Details By Id</a>
	 */
	public JSONObject getDetailsById(String siteId, String channelId, Map<String, String> params)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(channelId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId) + channelId + "/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId    - Property ID
	 * @param channelId - Live Channel ID
	 * @return JSONObject response from api call
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-channels-channel-id-">
	 *                             Delete Channel </a>
	 */
	public JSONObject deleteChannel(String siteId, String channelId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(channelId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId) + channelId + "/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}

	/**
	 * 
	 * @param siteId     - Property ID
	 * @param channelId  - Live Channel ID
	 * @param bodyParams - Parameters to be included in the body of the request
	 * @return JSONObject response from api call
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-channels-channel-id-">
	 *                             Change Settings For Channel </a>
	 */
	public JSONObject changeSettingsForChannel(String siteId, String channelId, Map<String, String> bodyParams)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(channelId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId) + channelId + "/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}

}

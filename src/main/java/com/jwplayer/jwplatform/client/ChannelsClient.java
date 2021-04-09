package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Imports API client.
 *
 * <p>An API client for the JW Platform Channels API. For the API documentation see:
 * https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2
 *
 * <p>Example:
 *    ChannelsClient client = ChannelsClient.getClient(secret);
 */
public class ChannelsClient extends JWplatformClientV2{
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
		headers.put("Authorization", "Bearer "+this.secret);
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
	 * @param siteId
	 * @param params
	 * @return JSONObject response from api call
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-channels
	 */
	public JSONObject listChannels(String siteId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param bodyParams
	 * @return JSONObject response from api call
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-channels
	 */
	public JSONObject createChannel(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param channelId
	 * @param params
	 * @return JSONObject response from api call
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-channels-channel-id-
	 */
	public JSONObject getDetailsById(String siteId, String channelId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(channelId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId)+channelId+"/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param channelId
	 * @return JSONObject response from api call
	 * @throws JWPlatformException
	 * https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-channels-channel-id-
	 */
	public JSONObject deleteChannel(String siteId, String channelId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(channelId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId)+channelId+"/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param channelId
	 * @param bodyParams
	 * @return JSONObject response from api call
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-channels-channel-id-
	 */
	public JSONObject changeSettingsForChannel(String siteId, String channelId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(channelId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId)+channelId+"/";
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}
	
}

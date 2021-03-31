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
 * <p>An API client for the JW Platform Channels API. For the API documentation see:
 * https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2
 *
 * <p>Example:
 *    EventsClient client = EventsClient.getClient(secret);
 */
public class EventsClient {

	private String path;
	private final String secret;
	private static Map<String,String> headers;
	
	/**
	   * Instantiate a new {@code EventsClient} instance.
	   *
	   * @param secret - your api secret
	   */
	private EventsClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/channels/%s/events/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}
	
	/**
	   * see {@link #EventsClient(String)}.
	   */
	public static EventsClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new EventsClient(secret);
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
	 * @param channelId
	 * @param params
	 * @return JSONObject response from api call
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-channels-channel-id-events
	 */
	public JSONObject listEventsOfChannel(String siteId, String channelId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(channelId, "Channel ID must not be null!");
		this.path = String.format(this.path, siteId, channelId);
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param channelId
	 * @param eventId
	 * @param params
	 * @return JSONObject response from api call
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-channels-channel-id-events-event-id-
	 */
	public JSONObject listEventDetailsOfChannel(String siteId, String channelId, String eventId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(channelId, "Channel ID must not be null!");
		Preconditions.checkNotNull(eventId, "Event ID must not be null!");
		this.path = String.format(this.path, siteId, channelId)+eventId;
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param channelId
	 * @param eventId
	 * @param bodyParams
	 * @return JSONObject response from api call
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#put_v2-sites-site-id-channels-channel-id-events-event-id-request-master
	 */
	public JSONObject requestMasterDownloadAvailability(String siteId, String channelId, String eventId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(channelId, "Channel ID must not be null!");
		Preconditions.checkNotNull(eventId, "Event ID must not be null!");
		this.path = String.format(this.path, siteId, channelId)+eventId+"/request_master/";
		return HttpCalls.request(this.path, bodyParams, false, "PUT", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param channelId
	 * @param eventId
	 * @param bodyParams
	 * @return JSONObject response from api call
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#put_v2-sites-site-id-channels-channel-id-events-event-id-clip-1
	 */
	public JSONObject createVODAsset(String siteId, String channelId, String eventId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(channelId, "Channel ID must not be null!");
		Preconditions.checkNotNull(eventId, "Event ID must not be null!");
		this.path = String.format(this.path, siteId, channelId)+eventId+"/clip/";
		return HttpCalls.request(this.path, bodyParams, false, "PUT", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param channelId
	 * @param eventId
	 * @param params
	 * @return JSONObject response from api call
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_sites-site-id-channels-channel-id-events-event-id-master-1
	 */
	public JSONObject downloadMasterAsset(String siteId, String channelId, String eventId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(channelId, "Channel ID must not be null!");
		Preconditions.checkNotNull(eventId, "Event ID must not be null!");
		this.path = String.format(this.path, siteId, channelId)+eventId+"/master/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
}

package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Media API client.
 *
 * <p>An API client for the JW Platform Media API. For the API documentation see:
 * https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2
 *
 * <p>Example:
 *    MediaClient client = MediaClient.getClient(secret);
 */
public class MediaClient{

	private String path;	
	private final String secret;
	private static Map<String,String> headers;
	
	/**
	   * Instantiate a new {@code MediaClient} instance.
	   *
	   * @param secret - your api secret
	   */
	private MediaClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/media/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}
	
	/**
	   * see {@link #MediaClient(String)}.
	   */
	public static MediaClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new MediaClient(secret);
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
	 * @param params
	 * @return JSON response from Media API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference#get_v2-sites-site-id-media
	 */
	public JSONObject listAllMedia(String siteId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId
	 * @param bodyParams
	 * @return JSON response from Media API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference#post_v2-sites-site-id-media
	 */
	public JSONObject createMedia(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param mediaId
	 * @param params
	 * @return JSON response from Media API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference#get_v2-sites-site-id-media-media-id-
	 */
	public JSONObject retrieveMediaById(String siteId, String mediaId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(mediaId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId)+mediaId+"/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId
	 * @param mediaId
	 * @return JSON response from Media API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference#delete_v2-sites-site-id-media-media-id-
	 */
	public JSONObject deleteMedia(String siteId, String mediaId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(mediaId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId)+mediaId+"/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param mediaId
	 * @param bodyParams
	 * @return JSON response from Media API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference#patch_v2-sites-site-id-media-media-id-
	 */
	public JSONObject updateMedia(String siteId, String mediaId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(mediaId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId)+mediaId+"/";
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param mediaId
	 * @param bodyParams
	 * @return JSON response from Media API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference#put_v2-sites-site-id-media-media-id-reupload
	 */
	public JSONObject reuploadMedia(String siteId, String mediaId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(mediaId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId)+mediaId+"/reupload/";
		return HttpCalls.request(this.path, bodyParams, false, "PUT", headers);
	}
}

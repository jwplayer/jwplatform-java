package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform MediaRendition API client.
 *
 * <p>
 * An API client for the JW Platform MediaRendition API. For the API documentation
 * see: <a href=
 * "https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2">Introduction
 * to api v2</a>
 *
 * <p>
 * Example: MediaRenditionClient client = MediaRenditionClient.getClient(secret);
 */
public class MediaRenditionClient extends JWPlatformClientV2 {

	private String path;
	private final String secret;

	/**
	 * Instantiate a new {@code MediaRenditionClient} instance.
	 *
	 * @param secret - your api secret
	 */
	private MediaRenditionClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/media/%s/media_renditions/";
		headers = new HashMap<>();
		headers.put("Authorization", this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}

	/**
	 * see {@link #MediaRenditionClient(String)}.
	 */
	public static MediaRenditionClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new MediaRenditionClient(secret);
	}

	/**
	 * 
	 * @param siteId - PropertyID
	 * @param mediaId - PropertyID
	 * @param params - Parameters to be included in the request
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/get_v2-sites-site-id-media-media-id-media-renditions">List Renditions</a>
	 */
	public JSONObject listMediaRenditions(String siteId, String mediaId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(mediaId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId, mediaId);
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param mediaId - Unique identifier for a resource 
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/post_v2-sites-site-id-media-media-id-media-renditions">Create Rendition</a>
	 */
	public JSONObject createRendition(String siteId, String mediaId,Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(mediaId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId, mediaId);
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}

	/**
	 * 
	 * @param siteId   - PropertyID
	 * @param mediaId - Unique identifier for a resource
	 * @param renditionId - Unique identifier for a rendition
	 * @param params   - Parameters to be included in the request
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/get_v2-sites-site-id-media-media-id-media-renditions-rendition-id-">Get Rendition By ID</a>
	 */
	public JSONObject getRenditionById(String siteId, String mediaId, String renditionId, Map<String, String> params)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(mediaId, "Media ID must not be null!");
		Preconditions.checkNotNull(renditionId, "Rendition ID must not be null!");
		this.path = String.format(this.path, siteId, mediaId)+renditionId+"/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId   - PropertyID
	 * @param mediaId - Unique identifier for a resource
	 * @param renditionId - Unique identifier for a rendition
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/delete_v2-sites-site-id-media-media-id-media-renditions-rendition-id-">Delete Rendition</a>
	 */
	public JSONObject deleteRendition(String siteId, String mediaId, String renditionId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(mediaId, "Media ID must not be null!");
		Preconditions.checkNotNull(renditionId, "Rendition ID must not be null!");
		this.path = String.format(this.path, siteId, mediaId)+renditionId+"/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}
}
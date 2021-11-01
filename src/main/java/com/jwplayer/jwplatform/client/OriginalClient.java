package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Original API client.
 *
 * <p>
 * An API client for the JW Platform Original API. For the API documentation
 * see: <a href=
 * "https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2">Introduction
 * to api v2</a>
 *
 * <p>
 * Example: OriginalClient client = OriginalClient.getClient(secret);
 */
public class OriginalClient extends JWPlatformClientV2 {

	private String path;
	private final String secret;

	/**
	 * Instantiate a new {@code OriginalClient} instance.
	 *
	 * @param secret - your api secret
	 */
	private OriginalClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/media/%s/originals/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}

	/**
	 * see {@link #OriginalClient(String)}.
	 */
	public static OriginalClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new OriginalClient(secret);
	}

	/**
	 * 
	 * @param siteId - PropertyID
	 * @param mediaId - PropertyID
	 * @param params - Parameters to be included in the request
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/get_v2-sites-site-id-media-media-id-originals">List Originals</a>
	 */
	public JSONObject listOriginals(String siteId, String mediaId, Map<String, String> params) throws JWPlatformException {
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
	 *                             "https://developer.jwplayer.com/jwplayer/reference/post_v2-sites-site-id-media-media-id-originals">Create Originals</a>
	 */
	public JSONObject createOriginals(String siteId, String mediaId,Map<String, String> bodyParams) throws JWPlatformException {
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
	 * @param originalId - Unique identifier for a rendition
	 * @param params   - Parameters to be included in the request
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/get_v2-sites-site-id-media-media-id-originals-original-id-">Get Original By ID</a>
	 */
	public JSONObject getOriginalById(String siteId, String mediaId, String originalId, Map<String, String> params)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(mediaId, "Media ID must not be null!");
		Preconditions.checkNotNull(originalId, "Original ID must not be null!");
		this.path = String.format(this.path, siteId, mediaId)+ originalId + "/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param mediaId   - Unique identifier for a resource
	 * @param originalId   - Unique identifier for an original
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/patch_v2-sites-site-id-media-media-id-originals-original-id-">Update Original</a>
	 */
	public JSONObject updateOriginal(String siteId, String mediaId, String originalId, Map<String, String> bodyParams)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(mediaId, "Config ID must not be null!");
		this.path = String.format(this.path, siteId, mediaId) + originalId + "/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}

	/**
	 * 
	 * @param siteId   - PropertyID
	 * @param mediaId - Unique identifier for a resource
	 * @param originalId - Unique identifier for a rendition
	 * @return JSON response from Media API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/delete_v2-sites-site-id-media-media-id-originals-original-id-">Delete Rendition</a>
	 */
	public JSONObject deleteOriginal(String siteId, String mediaId, String originalId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(mediaId, "Media ID must not be null!");
		Preconditions.checkNotNull(originalId, "Original ID must not be null!");
		this.path = String.format(this.path, siteId, mediaId) + originalId + "/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}

}
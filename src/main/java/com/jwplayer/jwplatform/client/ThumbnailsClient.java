package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Thumbnails API client.
 *
 * <p>
 * An API client for the JW Platform Thumbnails API. For the API documentation see:
 * <a href=
 * "https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2">Introduction
 * to api v2</a>
 *
 * <p>
 * Example: ThumbnailsClient client = ThumbnailsClient.getClient(secret);
 */
public class ThumbnailsClient extends JWPlatformClientV2 {

	private String path;
	private final String secret;

	/**
	 * Instantiate a new {@code ThumbnailsClient} instance.
	 *
	 * @param secret - your api secret
	 */
	private ThumbnailsClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/thumbnails/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}

	/**
	 * see {@link #ThumbnailsClient(String)}.
	 */
	public static ThumbnailsClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new ThumbnailsClient(secret);
	}

	/**
	 * 
	 * @param siteId - PropertyID
	 * @param params - Parameters to be included in the request
	 * @return JSON response from Thumbnail API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/get_v2-sites-site-id-thumbnails">List
	 *                             All Thumbnails </a>
	 */
	public JSONObject listThumbnails(String siteId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Thumbnail API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/post_v2-sites-site-id-thumbnails">Create
	 *                             Thumbnail</a>
	 */
	public JSONObject createThumbnail(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}

	/**
	 * 
	 * @param siteId  - PropertyID
	 * @param thumbnailId - Unique alphanumeric ID of the media
	 * @param params  - Parameters to be included in the request
	 * @return JSON response from Thumbnail API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/get_v2-sites-site-id-thumbnails-thumbnail-id-">Retreive
	 *                             Thumbnail by ID</a>
	 */
	public JSONObject retrieveThumbnailById(String siteId, String thumbnailId, Map<String, String> params)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(thumbnailId, "Thumbnail ID must not be null!");
		this.path = String.format(this.path, siteId) + thumbnailId + "/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId  - PropertyID
	 * @param thumbnailId - Unique alphanumeric ID of the media
	 * @return JSON response from Thumbnail API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/delete_v2-sites-site-id-thumbnails-thumbnail-id-">Delete
	 *                             thumbnail</a>
	 */
	public JSONObject deleteThumbnail(String siteId, String thumbnailId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(thumbnailId, "Thumbnail ID must not be null!");
		this.path = String.format(this.path, siteId) + thumbnailId + "/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param thumbnailId    - Unique alphanumeric ID of the media
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Thumbnail API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/patch_v2-sites-site-id-thumbnails-thumbnail-id-">Update
	 *                             Thumbnail</a>
	 */
	public JSONObject updateThumbnail(String siteId, String thumbnailId, Map<String, String> bodyParams)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(thumbnailId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId) + thumbnailId + "/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}
}

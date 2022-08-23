package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Text Tracks API client.
 *
 * <p>
 * An API client for the JW Platform Tracks API. For the API documentation
 * see: <a href=
 * "https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2">Introduction
 * to api v2</a>
 *
 * <p>
 * Example: TextTracksClient client = TextTracksClient.getClient(secret);
 */

public class TextTracksClient extends JWPlatformClientV2 {
	private String path;
	private final String secret;

	/**
	 * Instantiate a new {@code TextTracksClient} instance.
	 *
	 * @param secret - your api secret
	 */
	private TextTracksClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/media/%s/text_tracks/";
		headers = new HashMap<>();
		headers.put("Authorization", this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}

	/**
	 * see {@link #TextTracksClient(String)}.
	 */
	public static TextTracksClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new TextTracksClient(secret);
	}
	
	/**
	 * 
	 * @param siteId - PropertyID
	 * @param params - Parameters to be included in the request
	 * @return JSON response from Text Tracks API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/get_v2-sites-site-id-media-media-id-text-tracks">Text Tracks Client</a>
	 */
	public JSONObject listTextTracks(String siteId, String mediaId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId, mediaId);
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId - PropertyID
	 * @param mediaId - Unique identifier for a Media
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Text Tracks API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/post_v2-sites-site-id-media-media-id-text-tracks">Create Track</a>
	 */
	public JSONObject createTextTrack(String siteId,  String mediaId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId, mediaId);
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}

	/**
	 * 
	 * @param siteId - PropertyID
	 * @param mediaId - Unique identifier for a Media
	 * @param trackId - Unique identifier for a Track
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Text Tracks API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/patch_v2-sites-site-id-media-media-id-text-tracks-track-id-">Update Track</a>
	 */
	public JSONObject updateTextTrack(String siteId, String mediaId, String trackId, Map<String, String> bodyParams)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(mediaId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId, mediaId) + trackId + "/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}

	/**
	 * 
	 * @param siteId - PropertyID
	 * @param mediaId - Unique identifier for a Media
	 * @param trackId - Unique identifier for a Track
	 * @param params   - Parameters to be included in the request
	 * @return JSON response from Text Tracks API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/get_v2-sites-site-id-media-media-id-text-tracks-track-id-">Get Track</a>
	 */
	public JSONObject getTextTrackById(String siteId, String mediaId, String trackId, Map<String, String> params)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(mediaId, "Media ID must not be null!");
		Preconditions.checkNotNull(trackId, "Track ID must not be null!");
		this.path = String.format(this.path, siteId, mediaId) + trackId + "/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId - PropertyID
	 * @param mediaId - Unique identifier for a Media
	 * @param trackId - Unique identifier for a Track
	 * @return JSON response from Text Tracks API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/delete_v2-sites-site-id-media-media-id-text-tracks-track-id-">Delete Track</a>
	 */
	public JSONObject deleteTextTrack(String siteId, String mediaId, String trackId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(mediaId, "Media ID must not be null!");
		Preconditions.checkNotNull(trackId, "Track ID must not be null!");
		this.path = String.format(this.path, siteId,mediaId) + trackId + "/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}
	
	/**
	 * 
	 * @param siteId - PropertyID
	 * @param mediaId - Unique identifier for a Media
	 * @param trackId - Unique identifier for a Track
	 * @param bodyParams - Parameters to be included in the request
	 * @return JSON response from Text Tracks API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/put_v2-sites-site-id-media-media-id-text-tracks-track-id-publish">Publish Text Track</a>
	 */
	public JSONObject publishTextTrack(String siteId, String mediaId, String trackId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(mediaId, "Media ID must not be null!");
		Preconditions.checkNotNull(trackId, "Track ID must not be null!");
		this.path = String.format(this.path, siteId,mediaId) + trackId + "/publish/";
		return HttpCalls.request(this.path, bodyParams, false, "PUT", headers);
	}

	/**
	 * 
	 * @param siteId - PropertyID
	 * @param mediaId - Unique identifier for a Media
	 * @param trackId - Unique identifier for a Track
	 * @param bodyParams - Parameters to be included in the request
	 * @return JSON response from Text Tracks API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference/put_v2-sites-site-id-media-media-id-text-tracks-track-id-publish">Unpublish Text Track</a>
	 */
	public JSONObject unpublishTextTrack(String siteId, String mediaId, String trackId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(mediaId, "Media ID must not be null!");
		Preconditions.checkNotNull(trackId, "Track ID must not be null!");
		this.path = String.format(this.path, siteId,mediaId) + trackId + "/unpublish/";
		return HttpCalls.request(this.path, bodyParams, false, "PUT", headers);
	}
}

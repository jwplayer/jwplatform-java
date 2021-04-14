package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Playlists API client.
 *
 * <p>
 * An API client for the JW Platform Media API. For the API documentation see:
 * <a href=
 * "https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2">Introduction
 * to api v2</a>
 *
 * <p>
 * Example: PlaylistsClient client = PlaylistsClient.getClient(secret);
 */
public class PlaylistsClient extends JWPlatformClientV2 {

	private String path;
	private final String secret;

	/**
	 * Instantiate a new {@code PlaylistsClient} instance.
	 *
	 * @param secret - your api secret
	 */
	private PlaylistsClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/playlists/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}

	/**
	 * see {@link #PlaylistsClient(String)}.
	 */
	public static PlaylistsClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new PlaylistsClient(secret);
	}

	/**
	 * 
	 * @param siteId - PropertyID
	 * @param params - Parameters to be included in the request
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-playlists">List
	 *                             Playlists</a>
	 */
	public JSONObject listPlaylists(String siteId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @param params     - Parameters to be included in the request
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-playlists-playlist-id-">Retrieve
	 *                             playlist by ID</a>
	 */
	public JSONObject retrievePlaylistById(String siteId, String playlistId, Map<String, String> params)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-playlists-playlist-id-">Delete
	 *                             playlist by ID</a>
	 */
	public JSONObject deletePlaylistById(String siteId, String playlistId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-playlists-manual-playlist">Create
	 *                             manual playlist</a>
	 */
	public JSONObject createManualPlaylist(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId) + "manual_playlist/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @param params     - Parameters to be included in the request
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-playlists-playlist-id-manual-playlist">
	 *                             Retrieve manual playlist by ID</a>
	 */
	public JSONObject retrieveManualPlaylistById(String siteId, String playlistId, Map<String, String> params)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/manual_playlist/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-playlists-playlist-id-manual-playlist">Update
	 *                             manual playlist</a>
	 */
	public JSONObject updateManualPlaylist(String siteId, String playlistId, Map<String, String> bodyParams)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/manual_playlist/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-playlists-playlist-id-manual-playlist">Delete
	 *                             manual playlist</a>
	 */
	public JSONObject deleteManualPlaylist(String siteId, String playlistId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/manual_playlist/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-playlists-dynamic-playlist">
	 *                             Create dynamic playlist</a>
	 */
	public JSONObject createDynamicPlaylist(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId) + "dynamic_playlist/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @param params     - Parameters to be included in the request
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-playlists-playlist-id-dynamic-playlist">Retrieve
	 *                             dynamic playlist by ID</a>
	 */
	public JSONObject retrieveDynamicPlaylistById(String siteId, String playlistId, Map<String, String> params)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/dynamic_playlist/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-playlists-playlist-id-dynamic-playlist">Update
	 *                             dynamic playlist</a>
	 */
	public JSONObject updateDynamicPlaylist(String siteId, String playlistId, Map<String, String> bodyParams)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/dynamic_playlist/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-playlists-playlist-id-dynamic-playlist">Delete
	 *                             dynamic playlist</a>
	 */
	public JSONObject deleteDynamicPlaylist(String siteId, String playlistId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/dynamic_playlist/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-playlists-trending-playlist">Create
	 *                             trending playlist</a>
	 */
	public JSONObject createTrendingPlaylist(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId) + "trending_playlist/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @param params     - Parameters to be included in the request
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-playlists-playlist-id-trending-playlist">Retrieve
	 *                             trending playlist by ID</a>
	 */
	public JSONObject retrieveTrendingPlaylistById(String siteId, String playlistId, Map<String, String> params)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/trending_playlist/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-playlists-playlist-id-trending-playlist">Update
	 *                             trending playlist</a>
	 */
	public JSONObject updateTrendingPlaylist(String siteId, String playlistId, Map<String, String> bodyParams)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/trending_playlist/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-playlists-playlist-id-trending-playlist">Delete
	 *                             trending playlist</a>
	 */
	public JSONObject deleteTrendingPlaylist(String siteId, String playlistId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/trending_playlist/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-playlists-article-matching-playlist">Create
	 *                             article matching playlist</a>
	 */
	public JSONObject createArticleMatchingPlaylist(String siteId, Map<String, String> bodyParams)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId) + "article_matching_playlist/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @param params     - Parameters to be included in the request
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-playlists-playlist-id-article-matching-playlist">Retrieve
	 *                             article matching playlist by ID</a>
	 */
	public JSONObject retrieveArticleMatchingPlaylistById(String siteId, String playlistId, Map<String, String> params)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/article_matching_playlist/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-playlists-playlist-id-article-matching-playlist">Update
	 *                             article matching playlist by ID</a>
	 */
	public JSONObject updateArticleMatchingPlaylist(String siteId, String playlistId, Map<String, String> bodyParams)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/article_matching_playlist/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-playlists-playlist-id-article-matching-playlist">Delete
	 *                             article matching playlist</a>
	 */
	public JSONObject deleteArticleMatchingPlaylist(String siteId, String playlistId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/article_matching_playlist/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-playlists-search-playlist">Create
	 *                             search playlist</a>
	 */
	public JSONObject createSearchPlaylist(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId) + "search_playlist/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @param params     - Parameters to be included in the request
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-playlists-playlist-id-search-playlist">Retrieve
	 *                             search playlist by ID</a>
	 */
	public JSONObject retrieveSearchPlaylistById(String siteId, String playlistId, Map<String, String> params)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/search_playlist/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-playlists-playlist-id-search-playlist">Update
	 *                             search playlist</a>
	 */
	public JSONObject updateSearchPlaylist(String siteId, String playlistId, Map<String, String> bodyParams)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/search_playlist/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-playlists-playlist-id-search-playlist">Delete
	 *                             search playlist</a>
	 */
	public JSONObject deleteSearchPlaylist(String siteId, String playlistId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/search_playlist/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-playlists-recommendations-playlist">Create
	 *                             recommendations playlist</a>
	 */
	public JSONObject createRecommendationsPlaylist(String siteId, Map<String, String> bodyParams)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId) + "recommendations_playlist/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @param params     - Parameters to be included in the request
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-playlists-playlist-id-recommendations-playlist">Retrieve
	 *                             recommendations playlist by ID</a>
	 */
	public JSONObject retrieveRecommendationsPlaylistById(String siteId, String playlistId, Map<String, String> params)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/recommendations_playlist/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-playlists-playlist-id-recommendations-playlist">Update
	 *                             recommendations playlist</a>
	 */
	public JSONObject updateRecommendationsPlaylist(String siteId, String playlistId, Map<String, String> bodyParams)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/recommendations_playlist/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyID
	 * @param playlistId - Alphanumeric Playlist ID
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-playlists-playlist-id-recommendations-playlist">Delete
	 *                             Recommendations Playlist</a>
	 */
	public JSONObject deleteRecommendationsPlaylist(String siteId, String playlistId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId) + playlistId + "/recommendations_playlist/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}

}

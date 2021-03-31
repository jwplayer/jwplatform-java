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
 * <p>An API client for the JW Platform Media API. For the API documentation see:
 * https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2
 *
 * <p>Example:
 *    PlaylistsClient client = PlaylistsClient.getClient(secret);
 */
public class PlaylistsClient {

	private String path;	
	private final String secret;
	private static Map<String,String> headers;
	
	/**
	   * Instantiate a new {@code PlaylistsClient} instance.
	   *
	   * @param secret - your api secret
	   */
	private PlaylistsClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/playlists/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+this.secret);
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
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-playlists
	 */
	public JSONObject listPlaylists(String siteId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @param params
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-playlists-playlist-id-
	 */
	public JSONObject retrievePlaylistById(String siteId, String playlistId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-playlists-playlist-id-
	 */
	public JSONObject deletePlaylistById(String siteId, String playlistId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param bodyParams
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-playlists-manual-playlist
	 */
	public JSONObject createManualPlaylist(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId)+"manual_playlist/";
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}

	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @param params
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-playlists-playlist-id-manual-playlist
	 */
	public JSONObject retrieveManualPlaylistById(String siteId, String playlistId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/manual_playlist/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @param bodyParams
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-playlists-playlist-id-manual-playlist
	 */
	public JSONObject updateManualPlaylist(String siteId, String playlistId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/manual_playlist/";
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-playlists-playlist-id-manual-playlist
	 */
	public JSONObject deleteManualPlaylist(String siteId, String playlistId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/manual_playlist/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param bodyParams
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-playlists-dynamic-playlist
	 */
	public JSONObject createDynamicPlaylist(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId)+"dynamic_playlist/";
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @param params
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-playlists-playlist-id-dynamic-playlist
	 */
	public JSONObject retrieveDynamicPlaylistById(String siteId, String playlistId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/dynamic_playlist/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @param bodyParams
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-playlists-playlist-id-dynamic-playlist
	 */
	public JSONObject updateDynamicPlaylist(String siteId, String playlistId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/dynamic_playlist/";
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-playlists-playlist-id-dynamic-playlist
	 */
	public JSONObject deleteDynamicPlaylist(String siteId, String playlistId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/dynamic_playlist/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param bodyParams
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-playlists-trending-playlist
	 */
	public JSONObject createTrendingPlaylist(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId)+"trending_playlist/";
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @param params
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-playlists-playlist-id-trending-playlist
	 */
	public JSONObject retrieveTrendingPlaylistById(String siteId, String playlistId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/trending_playlist/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @param bodyParams
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-playlists-playlist-id-trending-playlist
	 */
	public JSONObject updateTrendingPlaylist(String siteId, String playlistId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/trending_playlist/";
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-playlists-playlist-id-trending-playlist
	 */
	public JSONObject deleteTrendingPlaylist(String siteId, String playlistId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/trending_playlist/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param bodyParams
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See 	https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-playlists-article-matching-playlist
	 */
	public JSONObject createArticleMatchingPlaylist(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId)+"article_matching_playlist/";
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @param params
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-playlists-playlist-id-article-matching-playlist
	 */
	public JSONObject retrieveArticleMatchingPlaylistById(String siteId, String playlistId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/article_matching_playlist/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @param bodyParams
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-playlists-playlist-id-article-matching-playlist
	 */
	public JSONObject updateArticleMatchingPlaylist(String siteId, String playlistId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/article_matching_playlist/";
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-playlists-playlist-id-article-matching-playlist
	 */
	public JSONObject deleteArticleMatchingPlaylist(String siteId, String playlistId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/article_matching_playlist/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param bodyParams
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-playlists-search-playlist
	 */
	public JSONObject createSearchPlaylist(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId)+"search_playlist/";
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @param params
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-playlists-playlist-id-search-playlist
	 */
	public JSONObject retrieveSearchPlaylistById(String siteId, String playlistId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/search_playlist/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @param bodyParams
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-playlists-playlist-id-search-playlist
	 */
	public JSONObject updateSearchPlaylist(String siteId, String playlistId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/search_playlist/";
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-playlists-playlist-id-search-playlist
	 */
	public JSONObject deleteSearchPlaylist(String siteId, String playlistId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/search_playlist/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param bodyParams
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-playlists-recommendations-playlist
	 */
	public JSONObject createRecommendationsPlaylist(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId)+"recommendations_playlist/";
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @param params
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-playlists-playlist-id-recommendations-playlist
	 */
	public JSONObject retrieveRecommendationsPlaylistById(String siteId, String playlistId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/recommendations_playlist/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @param bodyParams
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-playlists-playlist-id-recommendations-playlist
	 */
	public JSONObject updateRecommendationsPlaylist(String siteId, String playlistId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/recommendations_playlist/";
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param playlistId
	 * @return JSON response from Playlists API
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-playlists-playlist-id-recommendations-playlist
	 */
	public JSONObject deleteRecommendationsPlaylist(String siteId, String playlistId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(playlistId, "Playlist ID must not be null!");
		this.path = String.format(this.path, siteId)+playlistId+"/recommendations_playlist/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}
	
		
}

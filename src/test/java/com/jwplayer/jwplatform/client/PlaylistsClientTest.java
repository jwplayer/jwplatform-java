package com.jwplayer.jwplatform.client;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpCalls.class})
public class PlaylistsClientTest {

	PlaylistsClient playlistsClient = PlaylistsClient.getClient("fakeSecret");
	@Test
	public void testAllMethods() throws JWPlatformException {
		playlistsClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap())).thenReturn(new JSONObject());
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("POST"), anyMap())).thenReturn(new JSONObject());
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("DELETE"), anyMap())).thenReturn(new JSONObject());
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PATCH"), anyMap())).thenReturn(new JSONObject());
		Map<String,String> params = new HashMap<>();
		params.put("title", "test");
		playlistsClient.listPlaylists("siteId", new HashMap<>());
		playlistsClient.createArticleMatchingPlaylist("siteId", new HashMap<>());
		playlistsClient.createDynamicPlaylist("siteId", new HashMap<>());
		playlistsClient.createManualPlaylist("siteId", new HashMap<>());
		playlistsClient.createRecommendationsPlaylist("siteId", new HashMap<>());
		playlistsClient.createSearchPlaylist("siteId", new HashMap<>());
		playlistsClient.createTrendingPlaylist("siteId", new HashMap<>());
		playlistsClient.createArticleMatchingPlaylist("siteId", params);
		playlistsClient.createDynamicPlaylist("siteId", params);
		playlistsClient.createManualPlaylist("siteId", params);
		playlistsClient.createRecommendationsPlaylist("siteId", params);
		playlistsClient.createSearchPlaylist("siteId", params);
		playlistsClient.createTrendingPlaylist("siteId", params);
		
		playlistsClient.deleteArticleMatchingPlaylist("siteId", "playListID");
		playlistsClient.deleteDynamicPlaylist("siteId", "playListID");
		playlistsClient.deleteManualPlaylist("siteId", "playListID");
		playlistsClient.deleteRecommendationsPlaylist("siteId", "playListID");
		playlistsClient.deleteSearchPlaylist("siteId", "playListID");
		playlistsClient.deleteTrendingPlaylist("siteId", "playListID");
		playlistsClient.deletePlaylistById("siteId", "playListID");
		
		playlistsClient.updateArticleMatchingPlaylist("siteId", "playListID", params);
		playlistsClient.updateArticleMatchingPlaylist("siteId", "playListID", new HashMap<>());
		playlistsClient.updateDynamicPlaylist("siteId", "playListID", params);
		playlistsClient.updateDynamicPlaylist("siteId", "playListID", new HashMap<>());
		playlistsClient.updateManualPlaylist("siteId", "playListID", params);
		playlistsClient.updateManualPlaylist("siteId", "playListID", new HashMap<>());
		playlistsClient.updateRecommendationsPlaylist("siteId", "playListID", params);
		playlistsClient.updateRecommendationsPlaylist("siteId", "playListID", new HashMap<>());
		playlistsClient.updateSearchPlaylist("siteId", "playListID", params);
		playlistsClient.updateSearchPlaylist("siteId", "playListID", new HashMap<>());
		playlistsClient.updateTrendingPlaylist("siteId", "playListID", params);
		playlistsClient.updateTrendingPlaylist("siteId", "playListID", new HashMap<>());
		
		playlistsClient.retrieveArticleMatchingPlaylistById("siteId", "playListID", new HashMap<>());
		playlistsClient.retrieveDynamicPlaylistById("siteId", "playListID", new HashMap<>());
		playlistsClient.retrieveManualPlaylistById("siteId", "playListID", new HashMap<>());
		playlistsClient.retrieveRecommendationsPlaylistById("siteId", "playListID", new HashMap<>());
		playlistsClient.retrieveSearchPlaylistById("siteId", "playListID", new HashMap<>());
		playlistsClient.retrieveTrendingPlaylistById("siteId", "playListID", new HashMap<>());
		playlistsClient.retrievePlaylistById("siteId", "playListID", new HashMap<>());
		
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(39));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), anyString(), anyMap());
		playlistsClient.removeHeader("test");
	}
}

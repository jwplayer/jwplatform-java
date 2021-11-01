package com.jwplayer.jwplatform.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
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
@PrepareForTest({ HttpCalls.class })
public class MediaRenditionTest {

	MediaRenditionClient mediaRenditionClient = MediaRenditionClient.getClient("fakeSecret");

	@Test
	public void testAllMethods() throws JWPlatformException {
		mediaRenditionClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap()))
				.thenReturn(new JSONObject("{\"code\":\"success\"}"));
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("POST"), anyMap()))
				.thenReturn(new JSONObject("{\"code\":\"Object creation successful\"}"));
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("DELETE"), anyMap()))
				.thenReturn(new JSONObject("{\"code\":\"Deletion success!\"}"));
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PATCH"), anyMap()))
				.thenReturn(new JSONObject("{\"code\":\"Update successful\"}"));
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PUT"), anyMap()))
				.thenReturn(new JSONObject("{\"code\":\"Put successful\"}"));
		JSONObject listResp = mediaRenditionClient.listMediaRenditions("siteId", "mediaId",new HashMap<>());
		assertEquals(listResp.get("code"), "success");
		JSONObject deleteResp = mediaRenditionClient.deleteRendition("siteId", "mediaId", "renditionId");
		assertEquals(deleteResp.get("code"), "Deletion success!");
		Map<String, String> params = new HashMap<>();
		params.put("title", "test");
		mediaRenditionClient.createRendition("siteId", "mediaId", new HashMap<>());
		mediaRenditionClient.createRendition("siteId", "mediaId", params);
		mediaRenditionClient.listMediaRenditions("siteId","mediaId", params);
		mediaRenditionClient.deleteRendition("siteId","mediaId", "renditionId");
		mediaRenditionClient.getRenditionById("siteId", "mediaId", "renditionId", new HashMap<>());
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(7));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), anyString(), anyMap());
		mediaRenditionClient.removeHeader("test");
	}

	@Test(expected = JWPlatformException.class)
	public void testGetMediaRenditionException() throws JSONException, JWPlatformException {
		mediaRenditionClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap()))
				.thenThrow(new JWPlatformException("some exception occured"));
		mediaRenditionClient.listMediaRenditions("siteId", "mediaId", new HashMap<>());
	}
}

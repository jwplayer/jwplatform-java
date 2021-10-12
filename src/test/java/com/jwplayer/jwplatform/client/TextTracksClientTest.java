package com.jwplayer.jwplatform.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;
import com.mashape.unirest.http.Unirest;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Unirest.class, HttpCalls.class })
public class TextTracksClientTest {
	TextTracksClient textTracksClient = TextTracksClient.getClient("fakeSecret");;

	@Test
	public void testGetTextTrack() throws JWPlatformException {
		mockStatic(HttpCalls.class);
		JSONObject expected = new JSONObject();
		expected.append("page", 1);
		expected.append("media", new JSONObject());
		expected.append("page_length", 1);
		expected.append("total", 1);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap())).thenReturn(expected);
		JSONObject actual = textTracksClient.listTextTracks("siteId", "mediaId", new HashMap<String, String>());
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(1));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap());
		assertEquals(expected, actual);
	}

	@Test
	public void testCreateTextTrack() throws JWPlatformException {
		mockStatic(HttpCalls.class);
		JSONObject expected = new JSONObject("{\"code\":\"Success\"}");
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("POST"), anyMap())).thenReturn(expected);
		JSONObject actual = textTracksClient.createTextTrack("siteId", "mediaId",new HashMap<>());
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(1));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("POST"), anyMap());
		assertEquals(expected, actual);
	}

	@Test
	public void testGetTextTrackById() throws JWPlatformException {
		mockStatic(HttpCalls.class);
		JSONObject expected = new JSONObject();
		expected.append("key", "value");
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap())).thenReturn(expected);
		JSONObject actual = textTracksClient.getTextTrackById("siteId", "mediaId", "ajhsbjdsha", new HashMap<>());
		assertEquals(expected, actual);
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(1));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap());
	}

	@Test
	public void testDeleteTextTrack() throws JWPlatformException {
		mockStatic(HttpCalls.class);
		JSONObject expected = new JSONObject();
		expected.append("key", "value");
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("DELETE"), anyMap())).thenReturn(expected);
		JSONObject actual = textTracksClient.deleteTextTrack("siteId", "mediaId", "ajhsbjdsha");
		assertEquals(expected, actual);
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(1));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("DELETE"), anyMap());
	}

	@Test
	public void testUpdateTextTrack() throws JWPlatformException {
		mockStatic(HttpCalls.class);
		JSONObject expected = new JSONObject();
		expected.append("key", "value");
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PATCH"), anyMap())).thenReturn(expected);
		JSONObject actual = textTracksClient.updateTextTrack("siteId", "mediaId", "ajhsbjdsha", new HashMap<>());
		assertEquals(expected, actual);
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(1));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PATCH"), anyMap());
	}

	@Test
	public void testPublishTextTrack() throws JWPlatformException {
		mockStatic(HttpCalls.class);
		JSONObject expected = new JSONObject();
		expected.append("key", "value");
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PUT"), anyMap())).thenReturn(expected);
		JSONObject actual = textTracksClient.publishTextTrack("siteId", "mediaId", "ajhsbjdsha", new HashMap<>());
		assertEquals(expected, actual);
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(1));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PUT"), anyMap());
	}
	
	@Test
	public void testUnpublishTextTrack() throws JWPlatformException {
		mockStatic(HttpCalls.class);
		JSONObject expected = new JSONObject();
		expected.append("key", "value");
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PUT"), anyMap())).thenReturn(expected);
		JSONObject actual = textTracksClient.unpublishTextTrack("siteId", "mediaId", "ajhsbjdsha", new HashMap<>());
		assertEquals(expected, actual);
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(1));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PUT"), anyMap());
	}
}

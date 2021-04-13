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
public class EventsClientTest {

	EventsClient eventsClient = EventsClient.getClient("fakeSecret");

	@Test
	public void testAllMethods() throws JWPlatformException {
		eventsClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap()))
				.thenReturn(new JSONObject("{\"code\":\"success\"}"));
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PUT"), anyMap()))
				.thenReturn(new JSONObject("{\"code\":\"Object creation successful\"}"));
		JSONObject listResp = eventsClient.listEventsOfChannel("siteId", "channelId", new HashMap<>());
		assertEquals(listResp.get("code"), "success");
		JSONObject createResp = eventsClient.createVODAsset("siteId", "channelId", "eventId", new HashMap<>());
		assertEquals(createResp.get("code"), "Object creation successful");
		eventsClient.downloadMasterAsset("siteId", "channelId", "eventId", new HashMap<>());
		Map<String, String> params = new HashMap<>();
		params.put("title", "test");
		JSONObject updateAdResp = eventsClient.requestMasterDownloadAvailability("siteId", "channelId", "eventId",
				params);
		assertEquals(updateAdResp.get("code"), "Object creation successful");
		eventsClient.requestMasterDownloadAvailability("siteId", "channelId", "eventId", new HashMap<>());
		eventsClient.listEventDetailsOfChannel("siteId", "channelId", "eventId", new HashMap<>());
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(6));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), anyString(), anyMap());
		eventsClient.removeHeader("test");
	}

	@Test(expected = JWPlatformException.class)
	public void testListEventsofChannelException() throws JSONException, JWPlatformException {
		eventsClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap()))
				.thenThrow(new JWPlatformException("some exception occured"));
		eventsClient.listEventsOfChannel("siteId", "channelId", new HashMap<>());
	}
}

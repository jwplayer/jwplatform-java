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
public class ChannelsClientTest {

	ChannelsClient channelsClient = ChannelsClient.getClient("fakeSecret");
	@Test
	public void testAllMethods() throws JWPlatformException {
		channelsClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap())).thenReturn(new JSONObject());
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("POST"), anyMap())).thenReturn(new JSONObject());
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("DELETE"), anyMap())).thenReturn(new JSONObject());
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PATCH"), anyMap())).thenReturn(new JSONObject());
		channelsClient.listChannels("siteId", new HashMap<>());
		channelsClient.createChannel("siteId", new HashMap<>());
		channelsClient.deleteChannel("siteId", "adScheduleId");
		Map<String,String> updateParams = new HashMap<>();
		updateParams.put("title", "test");
		channelsClient.changeSettingsForChannel("siteId", "channelId", updateParams);
		channelsClient.changeSettingsForChannel("siteId", "channelId", new HashMap<>());
		channelsClient.getDetailsById("siteId", "channelId", new HashMap<>());
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(6));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), anyString(), anyMap());
		channelsClient.removeHeader("test");
	}
}

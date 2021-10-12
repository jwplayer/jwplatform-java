package com.jwplayer.jwplatform.client;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.util.HashMap;

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
public class UsageClientTest {

	UsageClient usageClient = UsageClient.getClient("fakeSecret");

	@Test
	public void testAllMethods() throws JWPlatformException {
		usageClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PUT"), anyMap()))
				.thenReturn(new JSONObject("{\"code\":\"success\"}"));
		JSONObject queryAccountResponse = usageClient.queryAccountUsage(new HashMap<>());
		assertEquals(queryAccountResponse.get("code"), "success");
		JSONObject querySiteResponse = usageClient.querySiteUsage("siteId", new HashMap<>());
		assertEquals(querySiteResponse.get("code"), "success");
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(2));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), anyString(), anyMap());
		usageClient.removeHeader("test");
	}

	@Test(expected = JWPlatformException.class)
	public void testTagsException() throws JSONException, JWPlatformException {
		usageClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PUT"), anyMap()))
				.thenThrow(new JWPlatformException("some exception occured"));
		usageClient.querySiteUsage("siteId", new HashMap<>());
	}
}

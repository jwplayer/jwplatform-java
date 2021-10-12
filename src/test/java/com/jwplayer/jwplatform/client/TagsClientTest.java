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
public class TagsClientTest {

	TagsClient tagsClient = TagsClient.getClient("fakeSecret");

	@Test
	public void testAllMethods() throws JWPlatformException {
		tagsClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PUT"), anyMap()))
				.thenReturn(new JSONObject("{\"code\":\"success\"}"));
		JSONObject bulkRemoveResponse = tagsClient.bulkRemoveTag("siteId", new HashMap<>());
		assertEquals(bulkRemoveResponse.get("code"), "success");
		JSONObject bulkRenameResponse = tagsClient.bulkRenameTag("siteId", new HashMap<>());
		assertEquals(bulkRenameResponse.get("code"), "success");
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(2));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), anyString(), anyMap());
		tagsClient.removeHeader("test");
	}

	@Test(expected = JWPlatformException.class)
	public void testTagsException() throws JSONException, JWPlatformException {
		tagsClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PUT"), anyMap()))
				.thenThrow(new JWPlatformException("some exception occured"));
		tagsClient.bulkRemoveTag("siteId", new HashMap<>());
	}
}

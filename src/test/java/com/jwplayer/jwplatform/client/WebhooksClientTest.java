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
public class WebhooksClientTest {

	WebhooksClient webhooksClient = WebhooksClient.getClient("fakeSecret");

	@Test
	public void testAllMethods() throws JWPlatformException {
		webhooksClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap()))
				.thenReturn(new JSONObject("{\"code\":\"success\"}"));
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("POST"), anyMap()))
				.thenReturn(new JSONObject("{\"code\":\"Object creation successful\"}"));
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("DELETE"), anyMap()))
				.thenReturn(new JSONObject("{\"code\":\"Deletion success!\"}"));
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PATCH"), anyMap()))
				.thenReturn(new JSONObject("{\"code\":\"Update successful\"}"));
		Map<String, String> params = new HashMap<>();
		params.put("title", "test");
		JSONObject listResp = webhooksClient.listWebhooks(new HashMap<>());
		assertEquals(listResp.get("code"), "success");
		webhooksClient.listWebhooks(params);
		JSONObject createResp = webhooksClient.createWebhookResource(new HashMap<>());
		assertEquals(createResp.get("code"), "Object creation successful");
		webhooksClient.createWebhookResource(params);
		JSONObject deleteResp = webhooksClient.deleteWebhook("webhookId");
		assertEquals(deleteResp.get("code"), "Deletion success!");
		JSONObject updateResp = webhooksClient.updateWebhook("webhookId", params);
		assertEquals(updateResp.get("code"), "Update successful");
		webhooksClient.updateWebhook("webhookId", new HashMap<>());
		webhooksClient.retrieveWebhookById("webhookId", new HashMap<>());
		webhooksClient.retrieveWebhookById("webhookId", params);
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(9));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), anyString(), anyMap());
		webhooksClient.removeHeader("test");
	}

	@Test(expected = JWPlatformException.class)
	public void testGetException() throws JSONException, JWPlatformException {
		webhooksClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap()))
				.thenThrow(new JWPlatformException("some exception occured"));
		webhooksClient.listWebhooks(new HashMap<>());
	}
}

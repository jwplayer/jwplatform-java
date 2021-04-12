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
@PrepareForTest({HttpCalls.class})
public class VPBConfigsClientTest {

	VPBConfigsClient vpbConfigsClient = VPBConfigsClient.getClient("fakeSecret");
	@Test
	public void testAllMethods() throws JWPlatformException {
		vpbConfigsClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap())).thenReturn(new JSONObject("{\"code\":\"success\"}"));
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("POST"), anyMap())).thenReturn(new JSONObject("{\"code\":\"Object creation successful\"}"));
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("DELETE"), anyMap())).thenReturn(new JSONObject("{\"code\":\"Deletion success!\"}"));
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PATCH"), anyMap())).thenReturn(new JSONObject("{\"code\":\"Update successful\"}"));
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PUT"), anyMap())).thenReturn(new JSONObject("{\"code\":\"Put successful\"}"));
		JSONObject listResp = vpbConfigsClient.listConfigs("siteId", new HashMap<>());
		assertEquals(listResp.get("code"),"success");
		JSONObject deleteResp = vpbConfigsClient.deleteConfig("siteId", "configId");
		assertEquals(deleteResp.get("code"),"Deletion success!");
		Map<String,String> params = new HashMap<>();
		params.put("title", "test");
		vpbConfigsClient.createConfig("siteId", new HashMap<>());
		vpbConfigsClient.createConfig("siteId", params);
		vpbConfigsClient.updateConfig("siteId", "configId", params);
		vpbConfigsClient.updateConfig("siteId", "configId", new HashMap<>());
		vpbConfigsClient.getConfigById("siteId", "configId", new HashMap<>());
		JSONObject updateAdResp = vpbConfigsClient.updateSchedules("siteId", params);
		assertEquals(updateAdResp.get("code"),"Put successful");
		vpbConfigsClient.updateSchedules("siteId", new HashMap<>());
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(9));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), anyString(), anyMap());
		vpbConfigsClient.removeHeader("test");
	}
	
	@Test(expected = JWPlatformException.class)
	public void testGetVpbConfigsException() throws JSONException, JWPlatformException {
		vpbConfigsClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap())).thenThrow(new JWPlatformException("some exception occured"));
		vpbConfigsClient.listConfigs("siteId", new HashMap<>());
	}
}

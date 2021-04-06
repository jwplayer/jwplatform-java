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
public class VPBConfigsClientTest {

	VPBConfigsClient vpbConfigsClient = VPBConfigsClient.getClient("fakeSecret");
	@Test
	public void testAllMethods() throws JWPlatformException {
		vpbConfigsClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap())).thenReturn(new JSONObject());
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("POST"), anyMap())).thenReturn(new JSONObject());
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("DELETE"), anyMap())).thenReturn(new JSONObject());
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PATCH"), anyMap())).thenReturn(new JSONObject());
		vpbConfigsClient.listConfigs("siteId", new HashMap<>());
		vpbConfigsClient.deleteConfig("siteId", "configId");
		Map<String,String> params = new HashMap<>();
		params.put("title", "test");
		vpbConfigsClient.createConfig("siteId", new HashMap<>());
		vpbConfigsClient.createConfig("siteId", params);
		vpbConfigsClient.updateConfig("siteId", "configId", params);
		vpbConfigsClient.updateConfig("siteId", "configId", new HashMap<>());
		vpbConfigsClient.getConfigById("siteId", "configId", new HashMap<>());
		vpbConfigsClient.updateSchedules("siteId", params);
		vpbConfigsClient.updateSchedules("siteId", new HashMap<>());
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(9));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), anyString(), anyMap());
		vpbConfigsClient.removeHeader("test");
	}
}

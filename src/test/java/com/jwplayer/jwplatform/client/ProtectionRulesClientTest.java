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
public class ProtectionRulesClientTest {

	ProtectionRulesClient protectionRulesClient = ProtectionRulesClient.getClient("fakeSecret");
	@Test
	public void testAllMethods() throws JWPlatformException {
		protectionRulesClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap())).thenReturn(new JSONObject());
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("POST"), anyMap())).thenReturn(new JSONObject());
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("DELETE"), anyMap())).thenReturn(new JSONObject());
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PATCH"), anyMap())).thenReturn(new JSONObject());
		protectionRulesClient.listMediaProtectionRules("siteId", new HashMap<>());
		protectionRulesClient.getSiteProtectionRules("siteId", new HashMap<>());
		protectionRulesClient.createMediaProtectionRule("siteId", new HashMap<>());
		protectionRulesClient.deleteMediaProtectionRule("siteId", "mediaPRid");
		Map<String,String> updateParams = new HashMap<>();
		updateParams.put("title", "test");
		protectionRulesClient.updateSiteProtectionRule("siteId", updateParams);
		protectionRulesClient.updateSiteProtectionRule("siteId", new HashMap<>());
		protectionRulesClient.changeSettingsMediaProtectionRule("siteId", "mediaPRid", updateParams);
		protectionRulesClient.changeSettingsMediaProtectionRule("siteId", "mediaPRid", new HashMap<>());
		protectionRulesClient.getMediaProtectionRuleById("siteId", "mediaPRid", new HashMap<>());
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(9));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), anyString(), anyMap());
		protectionRulesClient.removeHeader("test");
	}
}

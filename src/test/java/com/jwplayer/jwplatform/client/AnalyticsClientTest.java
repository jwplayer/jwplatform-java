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

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpCalls.class})
public class AnalyticsClientTest {
	AnalyticsClient analyticsClient = AnalyticsClient.getClient("fakeSecret");
	
	@Test
	public void testRunQueryNoSourceNoFormat() throws JWPlatformException {
		analyticsClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(eq("https://api.jwplayer.com/v2/sites/siteID/analytics/queries/?source=default&format=json"), anyMap(), anyBoolean(), eq("GET"), anyMap())).thenReturn(new JSONObject("{\"code\":\"success\"}"));
		JSONObject resp = analyticsClient.runQuery("siteID", null, null, new HashMap<>());
		assertEquals(resp.get("code"),"success");
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(1));
		HttpCalls.request(eq("https://api.jwplayer.com/v2/sites/siteID/analytics/queries/?source=default&format=json"), anyMap(), anyBoolean(), anyString(), anyMap());
		analyticsClient.removeHeader("test");
	}
	
	@Test
	public void testRunQueryEmptySourceEmptyFormat() throws JWPlatformException {
		analyticsClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(eq("https://api.jwplayer.com/v2/sites/siteID/analytics/queries/?source=default&format=json"), anyMap(), anyBoolean(), eq("GET"), anyMap())).thenReturn(new JSONObject("{\"code\":\"success\"}"));
		JSONObject resp = analyticsClient.runQuery("siteID", "", "", new HashMap<>());
		assertEquals(resp.get("code"),"success");
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(1));
		HttpCalls.request(eq("https://api.jwplayer.com/v2/sites/siteID/analytics/queries/?source=default&format=json"), anyMap(), anyBoolean(), anyString(), anyMap());
	}
	
	@Test
	public void testRunQuery() throws JWPlatformException {
		analyticsClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(eq("https://api.jwplayer.com/v2/sites/siteID/analytics/queries/?source=floatleft&format=csv"), anyMap(), anyBoolean(), eq("GET"), anyMap())).thenReturn(new JSONObject("{\"code\":\"success\"}"));
		JSONObject resp = analyticsClient.runQuery("siteID", "floatleft", "csv", new HashMap<>());
		assertEquals(resp.get("code"),"success");
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(1));
		HttpCalls.request(eq("https://api.jwplayer.com/v2/sites/siteID/analytics/queries/?source=floatleft&format=csv"), anyMap(), anyBoolean(), anyString(), anyMap());
	}
	
	@Test(expected = JWPlatformException.class )
	public void testRunQueryExcption() throws JWPlatformException{
		analyticsClient.addHeader("test", "testVal");
		mockStatic(HttpCalls.class);
		when(HttpCalls.request(eq("https://api.jwplayer.com/v2/sites/siteID/analytics/queries/?source=default&format=json"), anyMap(), anyBoolean(), eq("GET"), anyMap())).thenThrow(new JWPlatformException("some exception occured"));
		analyticsClient.runQuery("siteID", null, null, new HashMap<>());
	}
}

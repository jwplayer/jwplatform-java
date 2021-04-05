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
import org.junit.Before;
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
@PrepareForTest({Unirest.class, HttpCalls.class})
public class MediaClientTest {
	MediaClient mediaClient = MediaClient.getClient("fakeSecret");;
	
	
	@Test
	public void testGetMedia() throws JWPlatformException {
		mockStatic(HttpCalls.class);
		JSONObject expected = new JSONObject();
		expected.append("page", 1);
		expected.append("media", new JSONObject());
		expected.append("page_length", 1);
		expected.append("total", 1);
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap())).thenReturn(expected);
		JSONObject actual = mediaClient.listAllMedia("siteId", new HashMap<String,String>());
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(1));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap());
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCreateMedia() throws JWPlatformException {
		mockStatic(HttpCalls.class);
		JSONObject expected = new JSONObject("{"
				+ "    \"id\": MEDIA_ID,"
				+ "    \"status\": \"ready\","
				+ "    \"media_type\": \"video\","
				+ "    \"hosting_type\": \"hosted\","
				+ "    \"error_message\": \"test message\","
				+ "    \"duration\": 100.0,"
				+ "    \"created\": \"2018-10-02T13:58:15.001887+00:00\","
				+ "    \"last_modified\": \"2018-10-01T13:58:15.001795+00:00\","
				+ "    \"schema\": None,"
				+ "    \"type\": \"media\","
				+ "    \"external_id\": \"1\","
				+ "    \"mime_type\": \"video/mp4\","
				+ "    \"metadata\": {"
				+ "        \"title\": \"test title1\","
				+ "        \"description\": \"test description\","
				+ "        \"author\": \"me\","
				+ "        \"permalink\": \"site1.com\","
				+ "        \"category\": \"Automotive\","
				+ "        \"publish_start_date\": \"2018-10-01T13:58:15.001795+00:00\","
				+ "        \"publish_end_date\": \"2018-10-01T13:58:15.001795+00:00\","
				+ "        \"custom_params\": MEDIA_CUSTOM_PARAMS,"
				+ "        \"tags\": MEDIA_TAGS,"
				+ "    },"
				+ "    \"relationships\": {"
				+ "        \"protection_rule\": {"
				+ "            \"id\": \"protect2\","
				+ "            \"type\": \"protection_rule\","
				+ "        },"
				+ "    },"
				+ "}");
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("POST"), anyMap())).thenReturn(expected);
		JSONObject actual = mediaClient.createMedia("siteId", new HashMap<>());
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(1));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("POST"), anyMap());
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetMediaById() throws JWPlatformException {
		mockStatic(HttpCalls.class);
		JSONObject expected = new JSONObject();
		expected.append("key", "value");
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap())).thenReturn(expected);
		JSONObject actual = mediaClient.retrieveMediaById("siteId", "ajhsbjdsha", new HashMap<>());
		assertEquals(expected, actual);
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(1));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("GET"), anyMap());
	}
	
	@Test
	public void testDeleteMedia() throws JWPlatformException {
		mockStatic(HttpCalls.class);
		JSONObject expected = new JSONObject();
		expected.append("key", "value");
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("DELETE"), anyMap())).thenReturn(expected);
		JSONObject actual = mediaClient.deleteMedia("siteId", "ajhsbjdsha");
		assertEquals(expected, actual);
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(1));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("DELETE"), anyMap());
	}
	
	@Test
	public void testUpdateMedia() throws JWPlatformException {
		mockStatic(HttpCalls.class);
		JSONObject expected = new JSONObject();
		expected.append("key", "value");
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PATCH"), anyMap())).thenReturn(expected);
		JSONObject actual = mediaClient.updateMedia("siteId", "ajhsbjdsha", new HashMap<>());
		assertEquals(expected, actual);
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(1));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PATCH"), anyMap());
	}
	
	@Test
	public void testReupload() throws JWPlatformException {
		mockStatic(HttpCalls.class);
		JSONObject expected = new JSONObject();
		expected.append("key", "value");
		when(HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PUT"), anyMap())).thenReturn(expected);
		JSONObject actual = mediaClient.reuploadMedia("siteId", "ajhsbjdsha", new HashMap<>());
		assertEquals(expected, actual);
		PowerMockito.verifyStatic(HttpCalls.class, Mockito.times(1));
		HttpCalls.request(anyString(), anyMap(), anyBoolean(), eq("PUT"), anyMap());
	}
}

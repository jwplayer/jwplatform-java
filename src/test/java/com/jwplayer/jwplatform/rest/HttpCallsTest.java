package com.jwplayer.jwplatform.rest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
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
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.RequestBodyEntity;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ Unirest.class })
public class HttpCallsTest {

	@Test
	public void testGetRequest() throws JWPlatformException, UnirestException {
		mockStatic(Unirest.class);
		final GetRequest getRequest = PowerMockito.mock(GetRequest.class);
		@SuppressWarnings("unchecked")
		final HttpResponse<JsonNode> response = PowerMockito.mock(HttpResponse.class);
		final JsonNode jsonNode = PowerMockito.mock(JsonNode.class);
		final JSONObject expectedResponse = new JSONObject();
		expectedResponse.put("status", 200);
		when(response.getBody()).thenReturn(jsonNode);
		when(response.getStatus()).thenReturn(200);
		when(jsonNode.getObject()).thenReturn(expectedResponse);
		when(response.getBody()).thenReturn(new JsonNode("[\"Success\"]"));
		when(Unirest.get(anyString())).thenReturn(getRequest);
		when(Unirest.get(anyString()).headers(anyMap())).thenReturn(getRequest);
		when(Unirest.get(anyString()).headers(anyMap()).asJson()).thenReturn(response);
		HttpCalls.request("v2/media/", new HashMap<>(), false, "GET", new HashMap<>());
		PowerMockito.verifyStatic(Unirest.class, Mockito.atLeastOnce());
		Unirest.get(anyString());
	}

	@Test
	public void testPostRequest() throws UnirestException, JWPlatformException {
		mockStatic(Unirest.class);
		final HttpRequestWithBody requestBody = PowerMockito.mock(HttpRequestWithBody.class);
		@SuppressWarnings("unchecked")
		final HttpResponse<JsonNode> response = PowerMockito.mock(HttpResponse.class);
		final JsonNode jsonNode = PowerMockito.mock(JsonNode.class);
		final JSONObject expectedResponse = new JSONObject();
		expectedResponse.put("status", 200);
		when(response.getBody()).thenReturn(jsonNode);
		when(response.getStatus()).thenReturn(200);
		when(jsonNode.getObject()).thenReturn(expectedResponse);
		when(response.getBody()).thenReturn(new JsonNode("[\"Success\"]"));
		when(Unirest.post(anyString())).thenReturn(requestBody);
		when(Unirest.post(anyString()).headers(anyMap())).thenReturn(requestBody);
		when(Unirest.post(anyString()).headers(anyMap()).asJson()).thenReturn(response);
		when(response.getStatus()).thenReturn(200);
		Map<String, String> params = new HashMap<>();
		params.put("key", "value");
		HttpCalls.request("v2/media/", params, false, "POST", new HashMap<>());
		PowerMockito.verifyStatic(Unirest.class, Mockito.times(3));
		Unirest.post(anyString());
	}

	@Test
	public void testPostRequestBodyParams() throws UnirestException, JWPlatformException {
		mockStatic(Unirest.class);
		final HttpRequestWithBody requestWithBody = PowerMockito.mock(HttpRequestWithBody.class);
		mockStatic(Unirest.class);
		when(Unirest.post(anyString())).thenReturn(requestWithBody);
		when(requestWithBody.headers(anyMap())).thenReturn(requestWithBody);
		RequestBodyEntity entity = PowerMockito.mock(RequestBodyEntity.class);
		when(requestWithBody.body(any(JSONObject.class))).thenReturn(entity);
		@SuppressWarnings("unchecked")
		final HttpResponse<JsonNode> response = PowerMockito.mock(HttpResponse.class);
		final JsonNode jsonNode = PowerMockito.mock(JsonNode.class);
		final JSONObject expectedResponse = new JSONObject();
		expectedResponse.put("status", 200);
		when(entity.asJson()).thenReturn(response);
		when(response.getBody()).thenReturn(jsonNode);
		when(response.getStatus()).thenReturn(200);
		when(jsonNode.getObject()).thenReturn(expectedResponse);
		Map<String, String> params = new HashMap<>();
		params.put("key", "value");
		HttpCalls.request("v2/media/", params, true, "POST", new HashMap<>());
		PowerMockito.verifyStatic(Unirest.class, Mockito.atLeastOnce());
		Unirest.post(anyString());
	}

	@Test
	public void testDeleteRequest() throws UnirestException, JWPlatformException {
		mockStatic(Unirest.class);
		final HttpRequestWithBody request = PowerMockito.mock(HttpRequestWithBody.class);
		@SuppressWarnings("unchecked")
		final HttpResponse<JsonNode> response = PowerMockito.mock(HttpResponse.class);
		final JsonNode jsonNode = PowerMockito.mock(JsonNode.class);
		final JSONObject expectedResponse = new JSONObject();
		expectedResponse.put("status", 200);
		when(response.getBody()).thenReturn(jsonNode);
		when(response.getStatus()).thenReturn(200);
		when(jsonNode.getObject()).thenReturn(expectedResponse);
		when(response.getBody()).thenReturn(new JsonNode("[\"Success\"]"));
		when(Unirest.delete(anyString())).thenReturn(request);
		when(Unirest.delete(anyString()).headers(anyMap())).thenReturn(request);
		when(Unirest.delete(anyString()).headers(anyMap()).asJson()).thenReturn(response);
		HttpCalls.request("v2/media/", new HashMap<>(), false, "DELETE", new HashMap<>());
		PowerMockito.verifyStatic(Unirest.class, Mockito.atLeastOnce());
		Unirest.delete(anyString());
	}

	@Test(expected = JWPlatformException.class)
	public void testMethodNotSupported() throws JWPlatformException {
		HttpCalls.request("v2/media/", new HashMap<>(), false, "SOMETHING", new HashMap<>());
	}

	@Test(expected = JWPlatformException.class)
	public void testBadResult() throws UnirestException, JWPlatformException {
		mockStatic(Unirest.class);
		final HttpRequestWithBody requestWithBody = PowerMockito.mock(HttpRequestWithBody.class);
		mockStatic(Unirest.class);
		when(Unirest.post(anyString())).thenReturn(requestWithBody);
		when(requestWithBody.headers(anyMap())).thenReturn(requestWithBody);
		RequestBodyEntity entity = PowerMockito.mock(RequestBodyEntity.class);
		when(requestWithBody.body(any(JSONObject.class))).thenReturn(entity);
		when(entity.asJson()).thenThrow(new UnirestException("some exception"));
		HttpCalls.request("v2/media", new HashMap<>(), true, "POST", new HashMap<>());
	}

	@Test
	public void testPatchRequestBodyParams() throws UnirestException, JWPlatformException {
		mockStatic(Unirest.class);
		final HttpRequestWithBody requestWithBody = PowerMockito.mock(HttpRequestWithBody.class);
		mockStatic(Unirest.class);
		when(Unirest.patch(anyString())).thenReturn(requestWithBody);
		when(requestWithBody.headers(anyMap())).thenReturn(requestWithBody);
		RequestBodyEntity entity = PowerMockito.mock(RequestBodyEntity.class);
		when(requestWithBody.body(any(JSONObject.class))).thenReturn(entity);
		@SuppressWarnings("unchecked")
		final HttpResponse<JsonNode> response = PowerMockito.mock(HttpResponse.class);
		final JsonNode jsonNode = PowerMockito.mock(JsonNode.class);
		final JSONObject expectedResponse = new JSONObject();
		expectedResponse.put("status", 200);
		when(entity.asJson()).thenReturn(response);
		when(response.getBody()).thenReturn(jsonNode);
		when(response.getStatus()).thenReturn(200);
		when(jsonNode.getObject()).thenReturn(expectedResponse);
		Map<String, String> params = new HashMap<>();
		params.put("key", "value");
		HttpCalls.request("v2/media/", params, true, "PATCH", new HashMap<>());
		PowerMockito.verifyStatic(Unirest.class, Mockito.atLeastOnce());
		Unirest.patch(anyString());
	}

	@Test
	public void testPatchRequestNoBodyParams() throws UnirestException, JWPlatformException {
		mockStatic(Unirest.class);
		final HttpRequestWithBody requestBody = PowerMockito.mock(HttpRequestWithBody.class);
		@SuppressWarnings("unchecked")
		final HttpResponse<JsonNode> response = PowerMockito.mock(HttpResponse.class);
		final JsonNode jsonNode = PowerMockito.mock(JsonNode.class);
		final JSONObject expectedResponse = new JSONObject();
		expectedResponse.put("status", 200);
		when(response.getBody()).thenReturn(jsonNode);
		when(response.getStatus()).thenReturn(200);
		when(jsonNode.getObject()).thenReturn(expectedResponse);
		when(response.getBody()).thenReturn(new JsonNode("[\"Success\"]"));
		when(Unirest.patch(anyString())).thenReturn(requestBody);
		when(Unirest.patch(anyString()).headers(anyMap())).thenReturn(requestBody);
		when(Unirest.patch(anyString()).headers(anyMap()).asJson()).thenReturn(response);
		when(response.getStatus()).thenReturn(200);
		Map<String, String> params = new HashMap<>();
		params.put("key", "value");
		HttpCalls.request("v2/media/", params, false, "PATCH", new HashMap<>());
		PowerMockito.verifyStatic(Unirest.class, Mockito.times(3));
		Unirest.patch(anyString());
	}

	@Test
	public void testPutRequestBodyParams() throws UnirestException, JWPlatformException {
		mockStatic(Unirest.class);
		final HttpRequestWithBody requestWithBody = PowerMockito.mock(HttpRequestWithBody.class);
		mockStatic(Unirest.class);
		when(Unirest.put(anyString())).thenReturn(requestWithBody);
		when(requestWithBody.headers(anyMap())).thenReturn(requestWithBody);
		RequestBodyEntity entity = PowerMockito.mock(RequestBodyEntity.class);
		when(requestWithBody.body(any(JSONObject.class))).thenReturn(entity);
		@SuppressWarnings("unchecked")
		final HttpResponse<JsonNode> response = PowerMockito.mock(HttpResponse.class);
		final JsonNode jsonNode = PowerMockito.mock(JsonNode.class);
		final JSONObject expectedResponse = new JSONObject();
		expectedResponse.put("status", 200);
		when(entity.asJson()).thenReturn(response);
		when(response.getBody()).thenReturn(jsonNode);
		when(response.getStatus()).thenReturn(200);
		when(jsonNode.getObject()).thenReturn(expectedResponse);
		Map<String, String> params = new HashMap<>();
		params.put("key", "value");
		HttpCalls.request("v2/media/", params, true, "PUT", new HashMap<>());
		PowerMockito.verifyStatic(Unirest.class, Mockito.atLeastOnce());
		Unirest.put(anyString());
	}

	@Test
	public void testPutRequestNoBodyParams() throws UnirestException, JWPlatformException {
		mockStatic(Unirest.class);
		final HttpRequestWithBody requestBody = PowerMockito.mock(HttpRequestWithBody.class);
		@SuppressWarnings("unchecked")
		final HttpResponse<JsonNode> response = PowerMockito.mock(HttpResponse.class);
		final JsonNode jsonNode = PowerMockito.mock(JsonNode.class);
		final JSONObject expectedResponse = new JSONObject();
		expectedResponse.put("status", 200);
		when(response.getBody()).thenReturn(jsonNode);
		when(response.getStatus()).thenReturn(200);
		when(jsonNode.getObject()).thenReturn(expectedResponse);
		when(response.getBody()).thenReturn(new JsonNode("[\"Success\"]"));
		when(Unirest.put(anyString())).thenReturn(requestBody);
		when(Unirest.put(anyString()).headers(anyMap())).thenReturn(requestBody);
		when(Unirest.put(anyString()).headers(anyMap()).asJson()).thenReturn(response);
		when(response.getStatus()).thenReturn(200);
		Map<String, String> params = new HashMap<>();
		params.put("key", "value");
		HttpCalls.request("v2/media/", params, false, "PUT", new HashMap<>());
		PowerMockito.verifyStatic(Unirest.class, Mockito.times(3));
		Unirest.put(anyString());
	}
}

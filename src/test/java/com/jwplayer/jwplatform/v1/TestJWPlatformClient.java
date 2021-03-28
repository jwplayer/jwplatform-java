package com.jwplayer.jwplatform.v1;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.contains;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.exception.JWPlatformUnknownException;
import com.jwplayer.jwplatform.v1.JWPlatformClient;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import com.mashape.unirest.request.body.MultipartBody;
import com.mashape.unirest.request.body.RequestBodyEntity;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Unirest.class, HttpResponse.class, HttpRequestWithBody.class, MultipartBody.class})
public class TestJWPlatformClient {

  private final String apiKey = "fakeApiKey";
  private final String apiSecret = "fakeApiSecret";
  private final String path = "/v1/videos/create";

  @Test
  @SuppressWarnings("unchecked")
  public void testGetRequestWithSpecialCharacterUrlEncoding() throws Exception {
    final HashMap<String, String> params =
        new HashMap<String, String>() {
          {
            put("url", "media.com +!~");
          }
        };
    final String expectedUrlString = "url=media.com%20%2B%21~";
    final JSONObject expectedResponse = new JSONObject();
    expectedResponse.put("status", 200);

    final JWPlatformClient mediaAPIClient = JWPlatformClient.create(apiKey, apiSecret);

    final HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
    final JsonNode jsonNode = PowerMockito.mock(JsonNode.class);

    when(httpResponse.getBody()).thenReturn(jsonNode);
    when(httpResponse.getStatus()).thenReturn(200);
    when(jsonNode.getObject()).thenReturn(expectedResponse);
    mockStatic(Unirest.class);

    final GetRequest getRequest = PowerMockito.mock(GetRequest.class);
    try {
      // if the string isn't encoded properly,
      // this "contains" check will fail and the test will throw a NullPointerException
      when(Unirest.get(contains(expectedUrlString))).thenReturn(getRequest);
      when(getRequest.headers(anyMap())).thenReturn(getRequest);
      when(getRequest.asJson()).thenReturn(httpResponse);

      final JSONObject actualResponse = mediaAPIClient.request(path, params);

      assertEquals(expectedResponse.getInt("status"), actualResponse.getInt("status"));
    } catch (NullPointerException e) {
      fail("The URL Parameter was not encoded properly. Expected: " + expectedUrlString);
    }
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testPostRequestWithSpecialCharacterUrlEncoding() throws Exception {
    final HashMap<String, String> params =
        new HashMap<String, String>() {
          {
            put("url", "media.com +!~");
          }
        };
    final JSONObject expectedResponse = new JSONObject();
    expectedResponse.put("status", 200);

    final HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
    final JsonNode jsonNode = PowerMockito.mock(JsonNode.class);
    when(httpResponse.getBody()).thenReturn(jsonNode);
    when(jsonNode.getObject()).thenReturn(expectedResponse);
    when(httpResponse.getStatus()).thenReturn(200);
    mockStatic(Unirest.class);

    final HttpRequestWithBody requestWithBody = PowerMockito.mock(HttpRequestWithBody.class);
    final RequestBodyEntity requestBodyEntity = PowerMockito.mock(RequestBodyEntity.class);
    when(Unirest.post(anyString())).thenReturn(requestWithBody);
    when(Unirest.post(anyString()).headers(anyMap())).thenReturn(requestWithBody);
    when(requestWithBody.body(any(JSONObject.class))).thenReturn(requestBodyEntity);
    when(requestBodyEntity.asJson()).thenReturn(httpResponse);

    final JWPlatformClient mediaAPIClient = JWPlatformClient.create(apiKey, apiSecret);
    final JSONObject actualResponse = mediaAPIClient.request(path, params, true, "POST");

    assertEquals(expectedResponse.getInt("status"), actualResponse.getInt("status"));
  }

  @Test(expected = JWPlatformUnknownException.class)
  public void testGetRequestNon200ResponseUnknownException() throws Exception {
    final JSONObject expectedResponse = new JSONObject();
    final JWPlatformClient mediaAPIClient = JWPlatformClient.create(apiKey, apiSecret, "aHostGoesHere");
    final HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
    final JsonNode jsonNode = PowerMockito.mock(JsonNode.class);
    final GetRequest getRequest = PowerMockito.mock(GetRequest.class);
    mockStatic(Unirest.class);

    when(Unirest.get(anyString())).thenReturn(getRequest);
    when(getRequest.headers(anyMap())).thenReturn(getRequest);
    when(httpResponse.getBody()).thenReturn(jsonNode);
    when(getRequest.asJson()).thenReturn(httpResponse);
    when(jsonNode.getObject()).thenReturn(expectedResponse);
    when(httpResponse.getStatus()).thenReturn(418);

    mediaAPIClient.request(path);
  }

  @Test(expected = JWPlatformUnknownException.class)
  public void testGetRequestUnirestException() throws Exception {
    final JWPlatformClient mediaAPIClient = JWPlatformClient.create(apiKey, apiSecret);
    final GetRequest getRequest = PowerMockito.mock(GetRequest.class);
    mockStatic(Unirest.class);

    when(Unirest.get(anyString())).thenReturn(getRequest);
    when(getRequest.headers(anyMap())).thenReturn(getRequest);
    when(getRequest.asJson()).thenThrow(new UnirestException("some exception"));

    mediaAPIClient.request(path);
  }

  @Test(expected = JWPlatformUnknownException.class)
  public void testPostRequestNon200ResponseUnknownException() throws Exception {
    final JSONObject expectedResponse = new JSONObject();
    final JWPlatformClient mediaAPIClient = JWPlatformClient.create(apiKey, apiSecret);
    final HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
    final JsonNode jsonNode = PowerMockito.mock(JsonNode.class);
    final HttpRequestWithBody requestWithBody = PowerMockito.mock(HttpRequestWithBody.class);
    mockStatic(Unirest.class);

    when(Unirest.post(anyString())).thenReturn(requestWithBody);
    when(requestWithBody.asJson()).thenReturn(httpResponse);
    when(jsonNode.getObject()).thenReturn(expectedResponse);
    when(httpResponse.getStatus()).thenReturn(418);
    when(httpResponse.getBody()).thenReturn(jsonNode);
    when(jsonNode.getObject()).thenReturn(expectedResponse);

    mediaAPIClient.request(path, "POST");
  }

  @Test(expected = JWPlatformUnknownException.class)
  public void testPostRequestUnirestException() throws Exception {
    final JWPlatformClient mediaAPIClient = JWPlatformClient.create(apiKey, apiSecret);
    final HttpRequestWithBody requestWithBody = PowerMockito.mock(HttpRequestWithBody.class);
    mockStatic(Unirest.class);

    when(Unirest.post(anyString())).thenReturn(requestWithBody);
    when(requestWithBody.asJson()).thenThrow(new UnirestException("some exception"));

    mediaAPIClient.request(path, "post");
  }

  @Test(expected = JWPlatformException.class)
  public void testExceptionForUnsupportRequestType() throws Exception {
    final JWPlatformClient mediaAPIClient = JWPlatformClient.create(apiKey, apiSecret);
    mediaAPIClient.request(path, "PUT");
  }

  @Test
  public void testSuccessfulUpload() throws Exception {
    final JWPlatformClient mediaAPIClient = JWPlatformClient.create(apiKey, apiSecret);
    final HttpRequestWithBody requestWithBody = PowerMockito.mock(HttpRequestWithBody.class);
    final MultipartBody multipartBody = PowerMockito.mock(MultipartBody.class);
    final HttpResponse<InputStream> response = PowerMockito.mock(HttpResponse.class);
    mockStatic(Unirest.class);

    final String xmlResponse =
        "\"<response>\n"
            + "  <redirect_link></redirect_link>\n"
            + "  <video>\n"
            + "  \t<size>1245108</size>\n"
            + "  \t<key>ghi</key>\n"
            + "  \t<md5>b73c2094ad142f452312d3f8712c75f1</md5>\n"
            + "  </video>\n"
            + "  <status>ok</status>\n"
            + "  </response>\"";
    final InputStream streamResponse = new ByteArrayInputStream(xmlResponse.getBytes());

    when(multipartBody.asBinary()).thenReturn(response);
    when(response.getBody()).thenReturn(streamResponse);
    when(response.getStatus()).thenReturn(200);
    when(requestWithBody.field(eq("file"), any(File.class))).thenReturn(multipartBody);
    when(Unirest.post(anyString())).thenReturn(requestWithBody);
    when(Unirest.post(anyString()).headers(anyMap())).thenReturn(requestWithBody);

    final Map<String, String> queryBlock = new HashMap<>();
    queryBlock.put("key", "abc");
    queryBlock.put("token", "def");

    final Map<String, Object> linkBlock = new HashMap<>();
    linkBlock.put("path", "/v1/videos/upload");
    linkBlock.put("protocol", "http");
    linkBlock.put("address", "upload-portal.jwplatform.com");
    linkBlock.put("query", queryBlock);

    final Map<String, Object> videoCreateResponseMap = new HashMap<>();
    videoCreateResponseMap.put("link", linkBlock);
    videoCreateResponseMap.put("status", "ok");

    final JSONObject videoCreateResponse = new JSONObject(videoCreateResponseMap);

    final Map<String, Object> expectedVideoBlock = new HashMap<>();
    expectedVideoBlock.put("size", 1245108);
    expectedVideoBlock.put("key", "ghi");
    expectedVideoBlock.put("md5", "b73c2094ad142f452312d3f8712c75f1");

    final Map<String, Object> expectedResponseBlock = new HashMap<>();
    expectedResponseBlock.put("redirect_link", "");
    expectedResponseBlock.put("video", expectedVideoBlock);
    expectedResponseBlock.put("status", "ok");
    final Map<String, Object> expectedResponseMap = new HashMap<>();
    expectedResponseMap.put("response", expectedResponseBlock);

    final JSONObject expectedResponse = new JSONObject(expectedResponseMap);

    final JSONObject actualResponse = mediaAPIClient.upload(videoCreateResponse, "/some/path");
    PowerMockito.verifyStatic(VerificationModeFactory.times(1));
    assertEquals(expectedResponse.toString(), actualResponse.toString());
  }
}

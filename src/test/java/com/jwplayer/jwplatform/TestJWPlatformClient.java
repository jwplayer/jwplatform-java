package com.jwplayer.jwplatform;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.contains;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import com.jwplayer.jwplatform.exception.MediaAPIExceptionFactory;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import java.util.HashMap;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Unirest.class, HttpResponse.class})
public class TestJWPlatformClient {

  private final String apiKey = "fakeApiKey";
  private final String apiSecret = "fakeApiSecret";
  private final String path = "/v1/videos/create";

  @Test
  @SuppressWarnings("unchecked")
  public void testSpecialCharacterUrlEncoding() throws Exception {
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

  @Test(expected = MediaAPIExceptionFactory.JWPlatformUnknownException.class)
  public void testRequestNon200ResponseUnknownException() throws Exception {
    final JSONObject expectedResponse = new JSONObject();
    final JWPlatformClient mediaAPIClient = JWPlatformClient.create(apiKey, apiSecret);
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

  @Test(expected = MediaAPIExceptionFactory.JWPlatformUnknownException.class)
  public void testRequestUnirestException() throws Exception {
    final JWPlatformClient mediaAPIClient = JWPlatformClient.create(apiKey, apiSecret);
    final GetRequest getRequest = PowerMockito.mock(GetRequest.class);
    mockStatic(Unirest.class);

    when(Unirest.get(anyString())).thenReturn(getRequest);
    when(getRequest.headers(anyMap())).thenReturn(getRequest);
    when(getRequest.asJson()).thenThrow(new UnirestException("some exception"));

    mediaAPIClient.request(path);
  }
}

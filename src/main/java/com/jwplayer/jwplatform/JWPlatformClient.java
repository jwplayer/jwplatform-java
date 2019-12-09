package com.jwplayer.jwplatform;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.exception.MediaAPIExceptionFactory;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * JW Platform API client.
 *
 * <p>An API client for the JW Platform. For the API documentation see:
 * https://developer.jwplayer.com/jw-platform/reference/v1/index.html
 *
 * <p>Example: >>> JWPlatformClient client = JWPlatformClient.create(apiKey, apiSecret);
 */
public class JWPlatformClient {

  private final String host = "https://api.jwplatform.com/v1/";

  private final String apiSecret;
  private final String apiKey;

  private JWPlatformClient(final String apiKey, final String apiSecret) {
    this.apiKey = apiKey;
    this.apiSecret = apiSecret;
  }

  /**
   * Instantiate a new {@code JWPlatformClient} instance.
   *
   * @param apiSecret - your api key
   * @param apiKey - your api secret
   * @return a {@code JWPlatformClient} instance
   */
  public static JWPlatformClient create(final String apiKey, final String apiSecret) {
    Preconditions.checkNotNull(apiKey, "API Key must not be null!");
    Preconditions.checkNotNull(apiSecret, "API Secret must not be null!");

    return new JWPlatformClient(apiKey, apiSecret);
  }

  /**
   * Returns the current unix timestamp in seconds.
   *
   * @return - unix timestamp in seconds
   */
  private String getCurrentUnixTimestampInSeconds() {
    return Long.toString((new Date()).getTime() / 1000);
  }

  /**
   * Generate a random 8 digit {@code Integer} as a {@code String}.
   *
   * @return - random 8 digit {@code Integer}
   */
  private String getRandomNonce() {
    return Integer.toString(ThreadLocalRandom.current().nextInt(10000000, 100000000));
  }

  /**
   * URL encodes a {@code String}, then modifies it to be compliant with the JW Platform API.
   *
   * @param stringToEncode - the {@code String} to be URL Encoded.
   * @return - JW Platform API compliant encoded {@code String}
   * @throws JWPlatformException - an exception occurred trying to encode the requested
   *     {@code String}
   */
  private String encodeStringForJWPlatformAPI(final String stringToEncode)
          throws JWPlatformException {
    try {
      final String encodedValue = URLEncoder.encode(stringToEncode, "utf-8");

      // string replacements to align with the API
      return encodedValue.replace("%7E", "~").replace("*", "%2A").replace("+", "%20");
    } catch (final UnsupportedEncodingException e) {
      throw new MediaAPIExceptionFactory.JWPlatformUnknownException(
              String.format("Exception thrown encoding URL parameter %s", e.toString()));
    }
  }

  /**
   * Make an API call to the JWPlatform API.
   *
   * @param request - a {@code GetRequest} object
   * @return - {@code HTTPResponse} if request was successful
   * @throws JWPlatformException - API returned an exception
   */
  private HttpResponse<JsonNode> buildResponse(final GetRequest request) throws JWPlatformException {
    HttpResponse<JsonNode> response;
    try {
      response = request.asJson();
    } catch (final UnirestException e) {
      throw new MediaAPIExceptionFactory.JWPlatformUnknownException(
              String.format("Non-JSON response from server: %s", e.toString()));
    }
    if (response.getStatus() != 200) {
      try {
        final String errorType = response.getBody().getObject().get("code").toString();
        final String message = response.getBody().getObject().toString(2);
        MediaAPIExceptionFactory.throwJWPlatformException(
            StringUtils.stripEnd(errorType, "Error"), message);
      } catch (final JSONException e) {
        throw new MediaAPIExceptionFactory.JWPlatformUnknownException(
                String.format("Unknown JSONException thrown: %s", e.toString()));
      }
    }

    return response;
  }

  /**
   * Generates fully formed URL for api request.
   *
   * @param path - endpoint to be used in API request
   * @param params - Parameters to be included in the request
   * @return - Fully formed request URL for an API request with api signature
   * @throws JWPlatformException - an exception occurred during encoding
   */
  private String buildRequestUrl(
      final String host, final String path, final Map<String, String> params)
      throws JWPlatformException {
    final TreeMap<String, String> orderedParams = new TreeMap<>(params);
    orderedParams.put("api_key", this.apiKey);
    orderedParams.put("api_format", "json");
    orderedParams.put("api_nonce", this.getRandomNonce());
    orderedParams.put("api_timestamp", this.getCurrentUnixTimestampInSeconds());

    final StringBuilder encodedParams = new StringBuilder();
    for (final String param : orderedParams.keySet()) {
      if (encodedParams.length() != 0) {
        encodedParams.append("&");
      }
      final String encodedValue = encodeStringForJWPlatformAPI(orderedParams.get(param));
      encodedParams.append(param).append("=").append(encodedValue);
    }

    // We need to keep appending to encodedParams to get the hex digest, but
    // we branch off here to keep the params we'll use directly in the request
    final String paramsNoSignature = encodedParams.toString();
    encodedParams.append(this.apiSecret);
    final String hexDigest = DigestUtils.sha1Hex(encodedParams.toString());

    return host + path + "?" + paramsNoSignature + "&api_signature=" + hexDigest;
  }

  /**
   * Send a request to the Management API of the JWPlatform.
   *
   * <p>This function generates an API signature, makes request to JWPlatform API and returns
   * result.
   *
   * @param path - endpoint to be used in API request
   * @return - JSON response from JW Platform API
   * @throws JWPlatformException - JWPlatform API returned an exception. Because we dynamically
   *     build our exceptions, if you wish to retrieve the error message, you must call it
   *     from the cause, not the exception directly. The exception's message will be {@code null}.
   *
   *     Example: this will be {@code null}
   *         {@code e.getMessage()}
   *
   *     Example: this will contain error message
   *         {@code e.getCause().getMessage()}
   */
  public JSONObject request(final String path) throws JWPlatformException {
    return this.request(path, new HashMap<>());
  }

  /**
   * Send a request to the Management API of the JWPlatform.
   *
   * <p>This function generates an API signature, makes request to JWPlatform API and returns
   * result.
   *
   * @param path - endpoint to be used in API request
   * @param params - Parameters to be included in the request
   * @return - JSON response from JW Platform API
   * @throws JWPlatformException - JWPlatform API returned an exception. Because we dynamically
   *     build our exceptions, if you wish to retrieve the error message, you must call it
   *     from the cause, not the exception directly. The exception's message will be {@code null}.
   *
   *     Example: this will be {@code null}
   *         {@code e.getMessage()}
   *
   *     Example: this will contain error message
   *         {@code e.getCause().getMessage()}
   */
  public JSONObject request(final String path, final Map<String, String> params) throws JWPlatformException {
    final String requestUrl = this.buildRequestUrl(host, path, params);
    final GetRequest request = Unirest.get(requestUrl);

    return buildResponse(request).getBody().getObject();
  }
}

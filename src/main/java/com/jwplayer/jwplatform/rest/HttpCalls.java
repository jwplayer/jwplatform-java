package com.jwplayer.jwplatform.rest;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.exception.JWPlatformUnknownException;
import com.jwplayer.jwplatform.exception.MediaAPIExceptionFactory;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class HttpCalls {

	public static JSONObject request(final String path, final Map<String, String> params, final boolean isBodyParams,
			final String requestType, final Map<String, String> headers) throws JWPlatformException {
		final String requestUrl;
		final HttpResponse<JsonNode> response;

		try {
			switch (requestType.toUpperCase()) {
			case "GET":
				//requestUrl = buildRequestUrl(path, params);
				response = Unirest.get(path).headers(headers).asJson();
				break;
			case "POST":
				if (isBodyParams) {
					requestUrl = buildRequestUrl(path, Collections.emptyMap());
					response = Unirest.post(requestUrl).headers(headers).body(new JSONObject(params)).asJson();
				} else {
					requestUrl = buildRequestUrl(path, params);
					response = Unirest.post(requestUrl).asJson();
				}
				break;
			default:
				throw new JWPlatformException(String.format("%s is not a supported request type.", requestType));
			}
			final JSONObject responseBlock = response.getBody().getObject();
			checkForNon200Response(response.getStatus(), responseBlock);

			return responseBlock;
		} catch (final UnirestException e) {
			throw new JWPlatformUnknownException(String.format("Non-JSON response from server: %s", e.toString()));
		}
	}
	
	/**
	   * Generates fully formed URL for api request.
	   *
	   * @param path - endpoint to be used in API request which includes a leading slash (ie /my/path not my/path)
	   * @param params - Parameters to be included in the request
	   * @return - Fully formed request URL for an API request with api signature
	   * @throws JWPlatformException - an exception occurred during encoding
	   */
	  private static String buildRequestUrl(
	      final String path, final Map<String, String> params)
	      throws JWPlatformException {
	    final TreeMap<String, String> orderedParams = new TreeMap<>(params);
	    orderedParams.put("api_format", "json");
	    orderedParams.put("api_nonce", getRandomNonce());
	    orderedParams.put("api_timestamp", getCurrentUnixTimestampInSeconds());

	    final StringBuilder encodedParams = new StringBuilder();
	    for (final String param : orderedParams.keySet()) {
	      if (encodedParams.length() != 0) {
	        encodedParams.append("&");
	      }
	      final String encodedValue = encodeStringForJWPlatformAPI(orderedParams.get(param));
	      encodedParams.append(param).append("=").append(encodedValue);
	    }

	    return path + "?" + encodedParams.toString();
	  }
	  
	  /**
	   * URL encodes a {@code String}, then modifies it to be compliant with the JW Platform API.
	   *
	   * @param stringToEncode - the {@code String} to be URL Encoded
	   * @return - JW Platform API compliant encoded {@code String}
	   * @throws JWPlatformException - an exception occurred trying to encode the requested
	   *     {@code String}
	   */
	  private static String encodeStringForJWPlatformAPI(final String stringToEncode)
	          throws JWPlatformException {
	    try {
	      final String encodedValue = URLEncoder.encode(stringToEncode, "utf-8");

	      // string replacements to align with the API
	      return encodedValue.replace("%7E", "~").replace("*", "%2A").replace("+", "%20");
	    } catch (final UnsupportedEncodingException e) {
	      throw new JWPlatformUnknownException(
	              String.format("Exception thrown encoding URL parameter %s", e.toString()));
	    }
	  }
	  
	  /**
	   * Check if the API response is an non-200. If so, throw the
	   * appropriate JWPlatformException exception based on the
	   * error message.
	   *
	   * @param statusCode - the response status code
	   * @param response - a {@code JSONObject} object with the API response block
	   * @throws JWPlatformException - API returned an exception
	   */
	  private static void checkForNon200Response(final int statusCode, final JSONObject response)
	          throws JWPlatformException {
	    if (statusCode != 200) {
	      try {
	        MediaAPIExceptionFactory.throwJWPlatformException(
	                StringUtils.stripEnd(response.getString("code"), "Error"), response.toString());
	      } catch (final JSONException e) {
	        throw new JWPlatformUnknownException(
	                String.format("Unknown JSONException thrown: %s", e.toString()));
	      }
	    }
	  }
	  
	  /**
	   * Returns the current unix timestamp in seconds.
	   *
	   * @return - unix timestamp in seconds
	   */
	  private static String getCurrentUnixTimestampInSeconds() {
	    return Long.toString((new Date()).getTime() / 1000);
	  }

	  /**
	   * Generate a random 8 digit {@code Integer} as a {@code String}.
	   *
	   * @return - random 8 digit {@code Integer}
	   */
	  private static String getRandomNonce() {
	    return Integer.toString(ThreadLocalRandom.current().nextInt(10000000, 100000000));
	  }

}

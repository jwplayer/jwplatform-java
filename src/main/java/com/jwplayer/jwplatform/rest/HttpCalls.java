package com.jwplayer.jwplatform.rest;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.json.JSONObject;

import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.exception.JWPlatformUnknownException;
import com.jwplayer.jwplatform.utils.Util;
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
			case "PATCH":
				if (isBodyParams) {
					requestUrl = buildRequestUrl(path, Collections.emptyMap());
					response = Unirest.patch(requestUrl).headers(headers).body(new JSONObject(params)).asJson();
				} else {
					requestUrl = buildRequestUrl(path, params);
					response = Unirest.patch(requestUrl).asJson();
				}
				break;
			case "DELETE":
				response = Unirest.delete(path).headers(headers).asJson();
				if(response.getStatus()== 201 || response.getStatus()== 200 || response.getStatus() == 204) return new JSONObject("{\"message\":\"Deletion successful\"}");
				break;
			default:
				throw new JWPlatformException(String.format("%s is not a supported request type.", requestType));
			}
			final JSONObject responseBlock = response.getBody().getObject();
			Util.checkForNon200Response(response.getStatus(), responseBlock);
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
	    orderedParams.put("api_nonce", Util.getRandomNonce());
	    orderedParams.put("api_timestamp", Util.getCurrentUnixTimestampInSeconds());

	    final StringBuilder encodedParams = new StringBuilder();
	    for (final String param : orderedParams.keySet()) {
	      if (encodedParams.length() != 0) {
	        encodedParams.append("&");
	      }
	      final String encodedValue = Util.encodeStringForJWPlatformAPI(orderedParams.get(param));
	      encodedParams.append(param).append("=").append(encodedValue);
	    }

	    return path + "?" + encodedParams.toString();
	  }
	  
	  

}

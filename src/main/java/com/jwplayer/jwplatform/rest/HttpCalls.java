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

/**
 * HttpCalls class
 * 
 * HttpCalls is used internally by client classes for management API V2 to make
 * Http calls. Additionally, it is also used to build the request to be used for
 * the Http call. Detailed description of each method is provided below
 */
public class HttpCalls {

	/**
	 * Send a request to the Management API V2 of the JWPlatform.
	 *
	 * <p>
	 * This function generates an API signature, makes request to JWPlatform API V2
	 * and returns result.
	 *
	 * @param path         - endpoint to be used in API request which includes a
	 *                     leading slash (ie /my/path not my/path)
	 * @param params       - Parameters to be included in the request
	 * @param isBodyParams - Whether the parameters are to be included as query
	 *                     params or in the body of the request. This is only
	 *                     relevant for POST requests.
	 * @param requestType  - The type of HTTP. Valid values are ["GET", "POST"].
	 * @param headers      - Map of headers to add to the request
	 * @return - JSON response from JW Platform API
	 * @throws JWPlatformException - JWPlatform API returned an exception.
	 */
	public static JSONObject request(final String path, final Map<String, String> params, final boolean isBodyParams,
			final String requestType, final Map<String, String> headers) throws JWPlatformException {
		final String requestUrl;
		final HttpResponse<JsonNode> response;

		try {
			switch (requestType.toUpperCase()) {
			case "GET":
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
			case "PUT":
				if (isBodyParams) {
					requestUrl = buildRequestUrl(path, Collections.emptyMap());
					response = Unirest.put(requestUrl).headers(headers).body(new JSONObject(params)).asJson();
				} else {
					requestUrl = buildRequestUrl(path, params);
					response = Unirest.put(requestUrl).asJson();
				}
				break;
			case "DELETE":
				response = Unirest.delete(path).headers(headers).asJson();
				int firstDigit = Integer.parseInt(Integer.toString(response.getStatus()).substring(0, 1));
				if (firstDigit == 2)
					return new JSONObject("{\"message\":\"Deletion successful for the request" + path + " \"}");
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
	 * @param path   - endpoint to be used in API request which includes a leading
	 *               slash (ie /my/path not my/path)
	 * @param params - Parameters to be included in the request
	 * @return - Fully formed request URL for an API request with api signature
	 * @throws JWPlatformException - an exception occurred during encoding
	 */
	private static String buildRequestUrl(final String path, final Map<String, String> params)
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

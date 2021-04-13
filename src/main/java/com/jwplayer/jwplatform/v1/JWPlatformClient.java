package com.jwplayer.jwplatform.v1;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONObject;
import org.json.XML;

import com.google.common.base.Preconditions;
import com.google.common.io.CharStreams;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.exception.JWPlatformUnknownException;
import com.jwplayer.jwplatform.utils.Util;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

/**
 * JW Platform API client.
 *
 * <p>
 * An API client for the JW Platform. For the API documentation see:
 * https://developer.jwplayer.com/jw-platform/reference/v1/index.html
 *
 * <p>
 * Example: JWPlatformClient client = JWPlatformClient.create(apiKey,
 * apiSecret);
 */
public class JWPlatformClient {

	private final String host;
	private final String apiSecret;
	private final String apiKey;

	/**
	 * Instantiate a new {@code JWPlatformClient} instance.
	 *
	 * @param apiSecret - your api key
	 * @param apiKey    - your api secret
	 * @param host      - url for the Media API
	 */
	private JWPlatformClient(final String apiKey, final String apiSecret, final String host) {
		this.apiKey = apiKey;
		this.apiSecret = apiSecret;
		this.host = host;
	}

	/**
	 * see {@link #JWPlatformClient(String, String, String)}.
	 */
	public static JWPlatformClient create(final String apiKey, final String apiSecret) {
		return create(apiKey, apiSecret, "https://api.jwplatform.com/v1/");
	}

	/**
	 * see {@link #JWPlatformClient(String, String, String)}.
	 */
	public static JWPlatformClient create(final String apiKey, final String apiSecret, final String host) {
		Preconditions.checkNotNull(apiKey, "API Key must not be null!");
		Preconditions.checkNotNull(apiSecret, "API Secret must not be null!");
		Preconditions.checkNotNull(host, "Host must not be null!");

		return new JWPlatformClient(apiKey, apiSecret, host);
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
	private String buildRequestUrl(final String host, final String path, final Map<String, String> params)
			throws JWPlatformException {
		final TreeMap<String, String> orderedParams = new TreeMap<>(params);
		orderedParams.put("api_key", this.apiKey);
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

		// We need to keep appending to encodedParams to get the hex digest, but
		// we branch off here to keep the params we'll use directly in the request
		final String paramsNoSignature = encodedParams.toString();
		encodedParams.append(this.apiSecret);
		final String hexDigest = DigestUtils.sha1Hex(encodedParams.toString());

		return host + path + "?" + paramsNoSignature + "&api_signature=" + hexDigest;
	}

	/**
	 * Upload a video file from the local file system.
	 *
	 * @param uploadPath    - the fully constructed upload url which includes a
	 *                      leading slash (ie /my/path not my/path)
	 * @param localFilePath - the path to the video file on the local file system
	 * @param headers       - Map of headers to add to the request
	 * @return - JSON response from JW Platform API
	 * @throws JWPlatformException - API returned an exception
	 */
	private JSONObject uploadVideo(final String uploadPath, final String localFilePath,
			final Map<String, String> headers) throws JWPlatformException {
		JSONObject response;
		try {
			HttpResponse<InputStream> r = Unirest.post(uploadPath).headers(headers)
					.field("file", new File(localFilePath)).asBinary();

			final Reader reader = new InputStreamReader(r.getBody());
			response = XML.toJSONObject(CharStreams.toString(reader));

			Util.checkForNon200Response(r.getStatus(), response.getJSONObject("response"));
		} catch (final UnirestException | IOException e) {
			throw new JWPlatformUnknownException(String.format("Non-JSON response from server: %s", e.toString()));
		}

		return response;
	}

	/**
	 * see {@link #request(String, Map, boolean, String)}.
	 */
	public JSONObject request(final String path) throws JWPlatformException {
		return this.request(path, new HashMap<>());
	}

	/**
	 * see {@link #request(String, Map, boolean, String)}
	 */
	public JSONObject request(final String path, final Map<String, String> params) throws JWPlatformException {
		return this.request(path, params, false, "Get", new HashMap<>());
	}

	/**
	 * see {@link #request(String, Map, boolean, String)}
	 */
	public JSONObject request(final String path, final String requestType) throws JWPlatformException {
		return this.request(path, new HashMap<>(), false, requestType, new HashMap<>());
	}

	/**
	 * see {@link #request(String, Map, boolean, String)}
	 */
	public JSONObject request(final String path, final Map<String, String> params, final boolean isBodyParams,
			final String requestType) throws JWPlatformException {
		return this.request(path, params, isBodyParams, requestType, new HashMap<>());
	}

	/**
	 * Send a request to the Management API of the JWPlatform.
	 *
	 * <p>
	 * This function generates an API signature, makes request to JWPlatform API and
	 * returns result.
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
	public JSONObject request(final String path, final Map<String, String> params, final boolean isBodyParams,
			final String requestType, final Map<String, String> headers) throws JWPlatformException {
		final String requestUrl;
		final HttpResponse<JsonNode> response;

		try {
			switch (requestType.toUpperCase()) {
			case "GET":
				requestUrl = this.buildRequestUrl(host, path, params);
				response = Unirest.get(requestUrl).headers(headers).asJson();
				break;
			case "POST":
				if (isBodyParams) {
					requestUrl = this.buildRequestUrl(host, path, Collections.emptyMap());
					response = Unirest.post(requestUrl).headers(headers).body(new JSONObject(params)).asJson();
				} else {
					requestUrl = this.buildRequestUrl(host, path, params);
					response = Unirest.post(requestUrl).asJson();
				}
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
	 * see {@link #upload(JSONObject, String, Map)}.
	 */
	public JSONObject upload(final JSONObject videosCreateResponse, final String localFilePath)
			throws JWPlatformException {
		return this.upload(videosCreateResponse, localFilePath, new HashMap<>());
	}

	/**
	 * Upload a video file for a video created with `sourcetype: file`.
	 *
	 * @param videosCreateResponse - the response object from a '/videos/create' API
	 *                             call.
	 * @param localFilePath        - path to the video file on the local file
	 *                             system.
	 * @param headers              - map of headers for the request
	 * @return - JSON response from JW Platform API
	 * @throws JWPlatformException - JWPlatform API returned an exception.
	 */
	public JSONObject upload(final JSONObject videosCreateResponse, final String localFilePath,
			final Map<String, String> headers) throws JWPlatformException {
		final JSONObject link = videosCreateResponse.getJSONObject("link");
		final String path = link.getString("path");
		final String protocol = link.getString("protocol");
		final String address = link.getString("address");
		final JSONObject query = link.getJSONObject("query");
		final String key = query.getString("key");
		final String token = query.getString("token");
		final String uploadUrl = protocol + "://" + address + path + "?api_format=xml&key=" + key + "&token=" + token;

		return this.upload(uploadUrl, localFilePath, headers);
	}

	/**
	 * Upload a video file for a video created with `sourcetype: file`.
	 *
	 * @param uploadPath    - the fully constructed upload url. Refer to the
	 *                      JWPlatform documentation for instructions on how to
	 *                      build the url
	 * @param localFilePath - path to the video file on the local file system.
	 * @param headers       - map of headers for the request
	 * @return - JSON response from JW Platform API
	 * @throws JWPlatformException - JWPlatform API returned an exception.
	 */
	public JSONObject upload(final String uploadPath, final String localFilePath, final Map<String, String> headers)
			throws JWPlatformException {
		return uploadVideo(uploadPath, localFilePath, headers);
	}
}

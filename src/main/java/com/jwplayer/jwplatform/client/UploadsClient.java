package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Events API client.
 *
 * <p>An API client for the JW Platform Uploads API. For the API documentation see:
 * https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2
 *
 * <p>Example:
 *    UploadsClient client = UploadsClient.getClient(secret);
 */
public class UploadsClient {

	private String path;
	private final String secret;
	private static Map<String,String> headers;
	
	/**
	   * Instantiate a new {@code UploadsClient} instance.
	   *
	   * @param secret - your api secret
	   */
	private UploadsClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/uploads/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}
	
	/**
	   * see {@link #UploadsClient(String)}.
	   */
	public static UploadsClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new UploadsClient(secret);
	}
	
	/**
	 * Add custom/additional headers
	 * @param key
	 * @param value
	 */
	public void addHeader(String key, String value) {
		headers.put(key, value);
	}
	
	/**
	 * Remove a header
	 * @param key
	 */
	public void removeHeader(String key) {
		headers.remove(key);
	}
	
	/**
	 * 
	 * @param uploadId
	 * @param params
	 * @return JSONObject response from api call
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#uploads
	 */
	public JSONObject listCompleteIncompleteParts(String uploadId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(uploadId, "Upload ID must not be null!");
		String reqPath = this.path+uploadId+"/parts/";
		return HttpCalls.request(reqPath, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param uploadId
	 * @param bodyParams
	 * @return JSONObject response from api call
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#put_v2-uploads-upload-id-complete
	 */
	public JSONObject completeUpload(String uploadId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(uploadId, "Upload ID must not be null!");
		String reqPath = this.path+uploadId+"/complete/";
		return HttpCalls.request(reqPath, bodyParams, false, "PUT", headers);
	}
}

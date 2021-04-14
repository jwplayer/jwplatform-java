package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Uploads API client.
 *
 * <p>
 * An API client for the JW Platform Uploads API. For the API documentation see:
 * <a href=
 * "https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2">Introduction
 * to api v2</a>
 *
 * <p>
 * Example: UploadsClient client = UploadsClient.getClient(secret);
 */
public class UploadsClient extends JWPlatformClientV2 {

	private String path;
	private final String secret;

	/**
	 * Instantiate a new {@code UploadsClient} instance.
	 *
	 * @param secret - your api secret
	 */
	private UploadsClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/uploads/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + this.secret);
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
	 * 
	 * @param uploadId - Unique identifier for a resource
	 * @param params   - Parameters to be included in the request
	 * @return JSONObject response from api call
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#uploads">List
	 *                             completed and uncompleted parts</a>
	 */
	public JSONObject listCompleteIncompleteParts(String uploadId, Map<String, String> params)
			throws JWPlatformException {
		Preconditions.checkNotNull(uploadId, "Upload ID must not be null!");
		String reqPath = this.path + uploadId + "/parts/";
		return HttpCalls.request(reqPath, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param uploadId   - Unique identifier for a resource
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSONObject response from api call
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#put_v2-uploads-upload-id-complete">Complete
	 *                             upload</a>
	 */
	public JSONObject completeUpload(String uploadId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(uploadId, "Upload ID must not be null!");
		String reqPath = this.path + uploadId + "/complete/";
		return HttpCalls.request(reqPath, bodyParams, false, "PUT", headers);
	}
}

package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Imports API client.
 *
 * <p>
 * An API client for the JW Platform Imports API. For the API documentation see:
 * <a href=
 * "https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2">Introduction
 * to api v2</a>
 *
 * <p>
 * Example: ImportsClient client = ImportsClient.getClient(secret);
 */
public class ImportsClient extends JWPlatformClientV2 {

	private String path;
	private final String secret;

	/**
	 * Instantiate a new {@code ImportsClient} instance.
	 *
	 * @param secret - your api secret
	 */
	private ImportsClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/imports/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}

	/**
	 * see {@link #ImportsClient(String)}.
	 */
	public static ImportsClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new ImportsClient(secret);
	}

	/**
	 * 
	 * @param siteId - Property ID
	 * @param params - Parameters to be included in the request
	 * @return JSONObject response from api call
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-imports-1">
	 *                             List Imports </a>
	 */
	public JSONObject listImports(String siteId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId     - Property ID
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSONObject response from api call
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-imports-1">Add
	 *                             Import</a>
	 */
	public JSONObject addImport(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}

	/**
	 * 
	 * @param siteId   - Property ID
	 * @param importId - Unique alphanumeric ID of the import source
	 * @param params   - Parameters to be included in the request
	 * @return JSONObject response from api call
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-import-import-id--1">Get
	 *                             Import By ID</a>
	 */
	public JSONObject getImportById(String siteId, String importId, Map<String, String> params)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(importId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId) + importId + "/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId     - Property ID
	 * @param importId   - Unique alphanumeric ID of the import source
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSONObject response from api call
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-import-import-id--1">Update
	 *                             Import</a>
	 */
	public JSONObject updateImport(String siteId, String importId, Map<String, String> bodyParams)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(importId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId) + importId + "/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}

	/**
	 * 
	 * @param siteId   - Property ID
	 * @param importId - Unique alphanumeric ID of the import source
	 * @return JSONObject response from api call
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-import-import-id--1">Delete
	 *                             Import </a>
	 */
	public JSONObject deleteImport(String siteId, String importId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(importId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId) + importId + "/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}
}

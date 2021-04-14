package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Protection Rules API client.
 *
 * <p>
 * An API client for the JW Platform ProtectionRules API. For the API
 * documentation see: <a href=
 * "https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2">Introduction
 * to api v2</a>
 *
 * <p>
 * Example: ProtectionRulesClient client =
 * ProtectionRulesClient.getClient(secret);
 */
public class ProtectionRulesClient extends JWPlatformClientV2 {
	private String path;
	private final String secret;

	/**
	 * Instantiate a new {@code ProtectionRulesClient} instance.
	 *
	 * @param secret - your api secret
	 */
	private ProtectionRulesClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer " + this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}

	/**
	 * see {@link #ProtectionRulesClient(String)}.
	 */
	public static ProtectionRulesClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new ProtectionRulesClient(secret);
	}

	/**
	 * 
	 * @param siteId - PropertyId
	 * @param params - Parameters to be included in the request
	 * @return JSON response from ProtectionRules API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-media-protection-rules">List
	 *                             media protection rules</a>
	 */
	public JSONObject listMediaProtectionRules(String siteId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId) + "media_protection_rules/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyId
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from ProtectionRules API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-media-protection-rules">Create
	 *                             media protection rule</a>
	 */
	public JSONObject createMediaProtectionRule(String siteId, Map<String, String> bodyParams)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId) + "media_protection_rules/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}

	/**
	 * 
	 * @param siteId           - PropertyId
	 * @param protectionRuleId - Unique identifier for a protection rule
	 * @param params           - Parameters to be included in the request
	 * @return JSON response from ProtectionRules API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-media-protection-rules-protection-rule-id-">Get
	 *                             media protection rule by ID</a>
	 */
	public JSONObject getMediaProtectionRuleById(String siteId, String protectionRuleId, Map<String, String> params)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(protectionRuleId, "ProtectionRule ID must not be null!");
		this.path = String.format(this.path, siteId) + "media_protection_rules/" + protectionRuleId + "/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId           - PropertyId
	 * @param protectionRuleId - Unique identifier for a protection rule
	 * @return JSON response from ProtectionRules API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-media-protection-rules-protection-rule-id-">Delete
	 *                             media protection rule</a>
	 */
	public JSONObject deleteMediaProtectionRule(String siteId, String protectionRuleId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(protectionRuleId, "ProtectionRule ID must not be null!");
		this.path = String.format(this.path, siteId) + "media_protection_rules/" + protectionRuleId + "/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}

	/**
	 * 
	 * @param siteId           - PropertyId
	 * @param protectionRuleId - Unique identifier for a protection rule
	 * @param bodyParams       - Parameters to be included in the request body
	 * @return JSON response from ProtectionRules API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-media-protection-rules-protection-rule-id-">Change
	 *                             media protection rule's settings</a>
	 */
	public JSONObject changeSettingsMediaProtectionRule(String siteId, String protectionRuleId,
			Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(protectionRuleId, "ProtectionRule ID must not be null!");
		this.path = String.format(this.path, siteId) + "media_protection_rules/" + protectionRuleId + "/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}

	/**
	 * 
	 * @param siteId - PropertyId
	 * @param params - Parameters to be included in the request
	 * @return JSON response from ProtectionRules API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-site-protection-rule">Get
	 *                             site protection rules</a>
	 */
	public JSONObject getSiteProtectionRules(String siteId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId) + "site_protection_rule/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	/**
	 * 
	 * @param siteId     - PropertyId
	 * @param bodyParams - Parameters to be included in the request body
	 * @return JSON response from ProtectionRules API
	 * @throws JWPlatformException See <a href=
	 *                             "https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-site-protection-rule">Update
	 *                             site protection rule</a>
	 */
	public JSONObject updateSiteProtectionRule(String siteId, Map<String, String> bodyParams)
			throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId) + "site_protection_rule/";
		final boolean isBodyParams = bodyParams.size() > 0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}

}

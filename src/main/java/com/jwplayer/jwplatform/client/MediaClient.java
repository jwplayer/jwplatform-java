package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

public class MediaClient{

	private String path;
	private String secret;
	
	public MediaClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/media/";
	}
	
	private String format(String siteId, Map<String,String> headers) {
		headers.put("Authorization", "Bearer "+secret);
		headers.put("accept", "application/json");
		return String.format(this.path, siteId);
	}

	public JSONObject listAllMedia(String siteId, Map<String, String> params) throws JWPlatformException {
		Map<String, String> headers = new HashMap<>();
		this.path = format(siteId, headers);
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}

	public JSONObject createMedia(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Map<String, String> headers = new HashMap<>();
		this.path = format(siteId, headers);
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}
	
	public JSONObject retrieveMediaById(String siteId, String mediaId, Map<String, String> params) throws JWPlatformException {
		Map<String, String> headers = new HashMap<>();
		this.path = format(siteId, headers)+"media/"+mediaId+"/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
}

package com.jwplayer.jwplatform;

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
	
	private String format(String siteId) {
		return String.format(this.path, siteId);
	}

	public JSONObject listAllMedia(String siteId, Map<String, String> params) throws JWPlatformException {
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+secret);
		headers.put("accept", "application/json");
		this.path = format(siteId);
		System.out.println(this.path);
		return HttpCalls.request(this.path, new HashMap<>(), false, "GET", headers);
	}

}

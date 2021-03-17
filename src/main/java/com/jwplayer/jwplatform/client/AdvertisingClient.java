package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Preconditions;

public class AdvertisingClient{

	private String path;
	private final String secret;
	private static Map<String,String> headers;
	
	private AdvertisingClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/advertising/schedules";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}
	
	public static AdvertisingClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new AdvertisingClient(secret);
	}

}

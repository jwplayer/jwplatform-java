package com.jwplayer.jwplatform.client;

public class AdvertisingClient{

	private String path;
	private String secret;
	
	public AdvertisingClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/advertising/schedules";
	}

}

package com.jwplayer.jwplatform;

import com.google.common.base.Preconditions;

public class AdvertisingClient implements JWPlatformClient {

	private String path;
	private String secret;
	
	private AdvertisingClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplatform.com/v2/sites/%s/advertising/schedules";
	}

	@Override
	public JWPlatformClient create(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new AdvertisingClient(secret);
	}

}

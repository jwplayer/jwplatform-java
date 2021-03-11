package com.jwplayer.jwplatform;

import com.google.common.base.Preconditions;

public class MediaClient implements JWPlatformClient {

	private String path;
	private String secret;
	
	private MediaClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplatform.com/v2/sites/%s/media";
	}

	@Override
	public JWPlatformClient create(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new MediaClient(secret);
	}
	
	private void format(String siteId) {
		this.path = String.format(this.path, siteId);
	}


}

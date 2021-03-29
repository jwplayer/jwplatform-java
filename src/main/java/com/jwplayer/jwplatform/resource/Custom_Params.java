package com.jwplayer.jwplatform.resource;

import java.util.LinkedHashMap;

import org.json.JSONObject;

public class Custom_Params {
	LinkedHashMap<String, String> params;
	public Custom_Params(JSONObject object) {
		this.params = new LinkedHashMap<>();
		for(String key: object.keySet()) {
			params.put(key, object.getString(key));
		}
	}

}

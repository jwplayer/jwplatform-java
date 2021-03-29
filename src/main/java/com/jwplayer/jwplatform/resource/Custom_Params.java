package com.jwplayer.jwplatform.resource;

import java.util.LinkedHashMap;

import org.json.JSONObject;

/**
 * 
 * @author smurthy
 * JW Platform Custom_Params resource object.
 *
 * <p>An object class for the Custom_Params object. This object contains user defined custom key-value pairs.
 */
public class Custom_Params {
	LinkedHashMap<String, String> params;
	public Custom_Params(JSONObject object) {
		this.params = new LinkedHashMap<>();
		for(String key: object.keySet()) {
			params.put(key, object.getString(key));
		}
	}

}

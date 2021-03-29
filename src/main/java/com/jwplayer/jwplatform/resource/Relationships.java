package com.jwplayer.jwplatform.resource;

import org.json.JSONObject;

/**
 * 
 * @author smurthy
 * JW Platform Relationships resource object.
 *
 * <p>An object class for the Relationships object. For the documentation see:
 * https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#section-get-v2-sites-site_id-media-media-relationships
 */
public class Relationships {
	
	Protection_Rule protection_rule;
	
	public Relationships(JSONObject relationships) {
		if(!relationships.isNull("id")) this.setProtection_Rule(new Protection_Rule(relationships.getString("id")));
	}

	private void setProtection_Rule(Protection_Rule protection_rule) {
		this.protection_rule = protection_rule;
	}
	
	private Protection_Rule getProtection_Rule() {
		return this.protection_rule;
	}

	@Override
	public String toString() {
		return "Relationships [getProtection_Rule()=" + getProtection_Rule() + "]";
	}
}

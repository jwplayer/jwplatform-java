package com.jwplayer.jwplatform.resource;

import org.json.JSONObject;

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

package com.jwplayer.jwplatform.resource;

import java.util.Arrays;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * 
 * @author smurthy
 * JW Platform Metadata resource object.
 *
 * <p>An object class for the Metadata object. For the documentation see:
 * https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#section-get-v2-sites-site_id-media-media-metadata
 *
 */
public class Metadata {
	Custom_Params custom_params;
	Protection_Rule protection_rule;
	String publish_start_date;
	String author;
	String description;
	String category;
	String permalink;
	String title;
	Date publish_end_date;
	String[] tags;
	

	public Metadata() {
	}
	public Metadata(JSONObject data) {
		Metadata metadata = new Metadata();
		if(!data.isNull("author")) this.setAuthor(data.getString("author"));
		if(!data.isNull("category")) metadata.setCategory(data.getString("category"));
		if(!data.isNull("custom_params")) metadata.setCustom_params(new Custom_Params(data.getJSONObject("custom_params")));
		if(!data.isNull("description")) metadata.setDescription(data.getString("description"));
		if(!data.isNull("permalink")) metadata.setPermalink(data.getString("permalink"));
		if(!data.isNull("protection_rule")) metadata.setProtection_rule(new Protection_Rule((String) data.get("protection_rule_key")));
		if(!data.isNull("publish_end_date")) metadata.setPublish_end_date((Date) data.get("publish_end_date"));
		if(!data.isNull("publish_start_date")) metadata.setPublish_start_date(data.getString("publish_start_date"));
		if(!data.isNull("tags")) metadata.setTags((data.getJSONArray("tags")));
		if(!data.isNull("title")) metadata.setTitle(data.getString("title"));
	}
	@Override
	public String toString() {
		return "Metadata [getCustom_params()=" + getCustom_params() + ", getProtection_rule()=" + getProtection_rule()
				+ ", getPublish_start_data()=" + getPublish_start_date() + ", getAuthor()=" + getAuthor()
				+ ", getDescription()=" + getDescription() + ", getCategory()=" + getCategory() + ", getPermalink()="
				+ getPermalink() + ", getTitle()=" + getTitle() + ", getPublish_end_date()=" + getPublish_end_date()
				+ ", getTags()=" + Arrays.toString(getTags()) + "]";
	}
	public Custom_Params getCustom_params() {
		return custom_params;
	}
	public void setCustom_params(Custom_Params custom_params) {
		this.custom_params = custom_params;
	}
	public Protection_Rule getProtection_rule() {
		return protection_rule;
	}
	public void setProtection_rule(Protection_Rule protection_rule) {
		this.protection_rule = protection_rule;
	}
	public String getPublish_start_date() {
		return publish_start_date;
	}
	public void setPublish_start_date(String publish_start_data) {
		this.publish_start_date = publish_start_data;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPermalink() {
		return permalink;
	}
	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getPublish_end_date() {
		return publish_end_date;
	}
	public void setPublish_end_date(Date publish_end_date) {
		this.publish_end_date = publish_end_date;
	}
	public String[] getTags() {
		return tags;
	}
	public void setTags(JSONArray jsonArray) {
		this.tags = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            tags[i] = jsonArray.getString(i);
        }
	}
}

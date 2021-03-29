package com.jwplayer.jwplatform.resource;

import java.util.Date;

import org.json.JSONObject;

/**
 * 
 * @author smurthy
 * JW Platform Media resource object.
 *
 * <p>An object class for the Media object. For the documentation see:
 * https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#section-get-v2-sites-site_id-media-media
 *
 */
public class Media {
	Date created;
	int duration;
	String error_message;
	String hosting_type;
	String id;
	Date last_modified;
	String media_type;
	Metadata metadata;
	String mime_type;
	Relationships relationships;
	String status;
	String trim_in_point;
	String trim_out_point;
	String type;
	
	public Media(JSONObject media) {
		if(!media.isNull("created")) this.setCreated((Date) media.get("created"));
		if(!media.isNull("duration")) this.setDuration(media.getInt("duration"));
		if(!media.isNull("error_message")) this.setError_message(media.getString("error_message"));
		if(!media.isNull("hosting_type")) this.setHosting_type(media.getString("hosting_type"));
		if(!media.isNull("id")) this.setId(media.getString("id"));
		if(!media.isNull("last_modified")) this.setLast_modified((Date) media.get("last_modified"));
		if(!media.isNull("media_type")) this.setMedia_type(media.getString("media_type"));
		if(!media.isNull("metadata")) this.setMetadata(new Metadata(media.getJSONObject("metadata")));
		if(!media.isNull("mime_type")) this.setMime_type(media.getString("mime_type"));
		if(!media.isNull("relationships")) this.setRelationships(new Relationships(media.getJSONObject("relationships")));
		if(!media.isNull("status")) this.setStatus(media.getString("status"));
		if(!media.isNull("trim_in_point")) this.setTrim_in_point(media.getString("trim_in_point"));
		if(!media.isNull("trim_out_point")) this.setTrim_out_point(media.getString("trim_out_point"));
		if(!media.isNull("type")) this.setType(media.getString("type"));
	}
	@Override
	public String toString() {
		return "Media [getCreated()=" + getCreated() + ", getDuration()=" + getDuration() + ", getError_message()="
				+ getError_message() + ", getHosting_type()=" + getHosting_type() + ", getId()=" + getId()
				+ ", getLast_modified()=" + getLast_modified() + ", getMedia_type()=" + getMedia_type()
				+ ", getMetadata()=" + getMetadata() + ", getMime_type()=" + getMime_type() + ", getRelationships()="
				+ getRelationships() + ", getStatus()=" + getStatus() + ", getTrim_in_point()=" + getTrim_in_point()
				+ ", getTrim_out_point()=" + getTrim_out_point() + ", getType()=" + getType() + "]";
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getError_message() {
		return error_message;
	}
	public void setError_message(String error_message) {
		this.error_message = error_message;
	}
	public String getHosting_type() {
		return hosting_type;
	}
	public void setHosting_type(String hosting_type) {
		this.hosting_type = hosting_type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getLast_modified() {
		return last_modified;
	}
	public void setLast_modified(Date last_modified) {
		this.last_modified = last_modified;
	}
	public String getMedia_type() {
		return media_type;
	}
	public void setMedia_type(String media_type) {
		this.media_type = media_type;
	}
	public Metadata getMetadata() {
		return metadata;
	}
	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}
	public String getMime_type() {
		return mime_type;
	}
	public void setMime_type(String mime_type) {
		this.mime_type = mime_type;
	}
	public Relationships getRelationships() {
		return relationships;
	}
	public void setRelationships(Relationships relationships) {
		this.relationships = relationships;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTrim_in_point() {
		return trim_in_point;
	}
	public void setTrim_in_point(String trim_in_point) {
		this.trim_in_point = trim_in_point;
	}
	public String getTrim_out_point() {
		return trim_out_point;
	}
	public void setTrim_out_point(String trim_out_point) {
		this.trim_out_point = trim_out_point;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}

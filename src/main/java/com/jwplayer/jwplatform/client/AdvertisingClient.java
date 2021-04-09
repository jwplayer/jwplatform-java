package com.jwplayer.jwplatform.client;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.google.common.base.Preconditions;
import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.rest.HttpCalls;

/**
 * JW Platform Advertising API client.
 *
 * <p>An API client for the JW Platform Advertising API. For the API documentation see:
 * https://developer.jwplayer.com/jwplayer/reference#introduction-to-api-v2
 *
 * <p>Example:
 *    AdvertisingClient client = AdvertisingClient.getClient(secret);
 */
public class AdvertisingClient extends JWplatformClientV2{

	private String path;
	private final String secret;
	
	/**
	   * Instantiate a new {@code AdvertisingClient} instance.
	   *
	   * @param secret - your api secret
	   */
	private AdvertisingClient(String secret) {
		this.secret = secret;
		this.path = "https://api.jwplayer.com/v2/sites/%s/advertising/schedules";
		headers = new HashMap<>();
		headers.put("Authorization", "Bearer "+this.secret);
		headers.put("accept", "application/json");
		headers.put("Content-Type", "application/json");
	}
	
	/**
	   * see {@link #AdvertisingClient(String)}.
	   */
	public static AdvertisingClient getClient(String secret) {
		Preconditions.checkNotNull(secret, "API Secret must not be null!");
		return new AdvertisingClient(secret);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param params
	 * @return response object from listAdvertisingSchedules API call 
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-advertising-schedules
	 */
	public JSONObject listAdvertisingSchedules(String siteId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param bodyParams
	 * @return response object from createAdvertisingSchedule API call 
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#post_v2-sites-site-id-advertising-schedules
	 */
	public JSONObject createAdvertisingSchedule(String siteId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		this.path = String.format(this.path, siteId);
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "POST", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param adScheduleId
	 * @param params
	 * @return response object from retrieveAdvertisingScheduleById API call 
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#get_v2-sites-site-id-advertising-schedules-ad-schedule-id-
	 */
	public JSONObject retrieveAdvertisingScheduleById(String siteId, String adScheduleId, Map<String, String> params) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(adScheduleId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId)+adScheduleId+"/";
		return HttpCalls.request(this.path, params, false, "GET", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param adScheduleId
	 * @param bodyParams
	 * @return response object from updateAdvertisingSchedule API call
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#patch_v2-sites-site-id-advertising-schedules-ad-schedule-id-
	 */
	public JSONObject updateAdvertisingSchedule(String siteId, String adScheduleId, Map<String, String> bodyParams) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(adScheduleId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId)+adScheduleId+"/";
		boolean isBodyParams = bodyParams.size()>0;
		return HttpCalls.request(this.path, bodyParams, isBodyParams, "PATCH", headers);
	}
	
	/**
	 * 
	 * @param siteId
	 * @param adScheduleId
	 * @return response object from deleteAdvertisingScheduleById API call
	 * @throws JWPlatformException
	 * See https://developer.jwplayer.com/jwplayer/reference?showHidden=93052#delete_v2-sites-site-id-advertising-schedules-ad-schedule-id-
	 */
	public JSONObject deleteAdvertisingScheduleById(String siteId, String adScheduleId) throws JWPlatformException {
		Preconditions.checkNotNull(siteId, "Site ID must not be null!");
		Preconditions.checkNotNull(adScheduleId, "Media ID must not be null!");
		this.path = String.format(this.path, siteId)+adScheduleId+"/";
		return HttpCalls.request(this.path, new HashMap<>(), false, "DELETE", headers);
	}

}

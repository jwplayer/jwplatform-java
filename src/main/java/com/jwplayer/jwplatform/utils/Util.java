package com.jwplayer.jwplatform.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.jwplayer.jwplatform.exception.JWPlatformException;
import com.jwplayer.jwplatform.exception.JWPlatformUnknownException;
import com.jwplayer.jwplatform.exception.MediaAPIExceptionFactory;

/**
 * Util class.
 *
 * <p>
 * A utility for the JW Platform API clients. This class contains of methods
 * that can be accessed separately as shown in the example below. Detailed
 * documentation for each method is provided below.
 *
 * <p>
 * Example: Util.encodeStringForJWPlatformAPI("StringToEncode");
 */
public class Util {
	/**
	 * URL encodes a {@code String}, then modifies it to be compliant with the JW
	 * Platform API.
	 *
	 * @param stringToEncode - the {@code String} to be URL Encoded
	 * @return - JW Platform API compliant encoded {@code String}
	 * @throws JWPlatformException - an exception occurred trying to encode the
	 *                             requested {@code String}
	 */
	public static String encodeStringForJWPlatformAPI(final String stringToEncode) throws JWPlatformException {
		try {
			final String encodedValue = URLEncoder.encode(stringToEncode, "utf-8");

			// string replacements to align with the API
			return encodedValue.replace("%7E", "~").replace("*", "%2A").replace("+", "%20");
		} catch (final UnsupportedEncodingException e) {
			throw new JWPlatformUnknownException(
					String.format("Exception thrown encoding URL parameter %s", e.toString()));
		}
	}

	/**
	 * Check if the API response is an non-200. If so, throw the appropriate
	 * JWPlatformException exception based on the error message.
	 *
	 * @param statusCode - the response status code
	 * @param response   - a {@code JSONObject} object with the API response block
	 * @throws JWPlatformException - API returned an exception
	 */
	public static void checkForNon200Response(final int statusCode, final JSONObject response)
			throws JWPlatformException {
		if (statusCode != 200 && statusCode != 201) {
			try {
				MediaAPIExceptionFactory.throwJWPlatformException(
						StringUtils.stripEnd(response.getString("code"), "Error"), response.toString());
			} catch (final JSONException e) {
				throw new JWPlatformUnknownException(String.format("Unknown JSONException thrown: %s", e.toString()));
			}
		}
	}

	/**
	 * Returns the current unix timestamp in seconds.
	 *
	 * @return - unix timestamp in seconds
	 */
	public static String getCurrentUnixTimestampInSeconds() {
		return Long.toString((new Date()).getTime() / 1000);
	}

	/**
	 * Generate a random 8 digit {@code Integer} as a {@code String}.
	 *
	 * @return - random 8 digit {@code Integer}
	 */
	public static String getRandomNonce() {
		return Integer.toString(ThreadLocalRandom.current().nextInt(10000000, 100000000));
	}
}

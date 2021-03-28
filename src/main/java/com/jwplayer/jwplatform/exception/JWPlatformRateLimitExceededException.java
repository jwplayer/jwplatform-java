package com.jwplayer.jwplatform.exception;

public class JWPlatformRateLimitExceededException extends JWPlatformException {

  /**
   * Instance of an RateLimitExceededException.
   *
   * @param message the exception message
   */
  public JWPlatformRateLimitExceededException(final String message) {
    super(message);
  }
}

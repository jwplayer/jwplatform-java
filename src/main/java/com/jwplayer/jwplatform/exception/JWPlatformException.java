package com.jwplayer.jwplatform.exception;

/**
 * Base class for JWPlatform Exceptions.
 */
public class JWPlatformException extends Exception {

  /**
   * Instance of an UnknownException.
   *
   * @param message the exception message
   */
  public JWPlatformException(final String message) {
    super(message);
  }
}

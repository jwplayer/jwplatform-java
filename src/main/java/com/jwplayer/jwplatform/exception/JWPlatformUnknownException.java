package com.jwplayer.jwplatform.exception;

public class JWPlatformUnknownException extends JWPlatformException {

    public JWPlatformUnknownException(){
    }

    /**
     * Instance of an UnknownException.
     *
     * @param message the exception message
     */
    public JWPlatformUnknownException(final String message) {
        super(message);
    }
}

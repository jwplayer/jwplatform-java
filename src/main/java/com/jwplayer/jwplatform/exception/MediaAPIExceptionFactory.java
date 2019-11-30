package com.jwplayer.jwplatform.exception;

/** Factory for creating and throwing JWPlatform Exceptions. */
public class MediaAPIExceptionFactory {

  /**
   * A case statement for finding and throwing the appropriate exception give an error from the
   * JWPlatform API.
   *
   * @param errorType - the error type
   * @param message - error message
   * @throws JWPlatformException - an instance of a JWPlatformException exception
   */
  public static void throwJWPlatformException(final String errorType, final String message)
      throws JWPlatformException {
    switch (errorType) {
      case "NotFound":
        throw new JWPlatformNotFoundException(message);
      case "NoMethod":
        throw new JWPlatformNoMethodException(message);
      case "NotImplemented":
        throw new JWPlatformNotImplementedException(message);
      case "NotSupported":
        throw new JWPlatformNotSupportedException(message);
      case "CallFailed":
        throw new JWPlatformCallFailedException(message);
      case "CallUnavailable":
        throw new JWPlatformCallUnavailableException(message);
      case "CallInvalid":
        throw new JWPlatformCallInvalidException(message);
      case "ParameterMissing":
        throw new JWPlatformParameterMissingException(message);
      case "ParameterEmpty":
        throw new JWPlatformParameterEmptyException(message);
      case "ParameterEncoding":
        throw new JWPlatformParameterEncodingException(message);
      case "ParameterInvalid":
        throw new JWPlatformParameterInvalidException(message);
      case "PreconditionFailed":
        throw new JWPlatformPreconditionFailedException(message);
      case "ItemAlreadyExists":
        throw new JWPlatformItemAlreadyExistsException(message);
      case "PermissionDenied":
        throw new JWPlatformPermissionDeniedException(message);
      case "Database":
        throw new JWPlatformDatabaseException(message);
      case "Integrity":
        throw new JWPlatformIntegrityException(message);
      case "DigestMissing":
        throw new JWPlatformDigestMissingException(message);
      case "DigestInvalid":
        throw new JWPlatformDigestInvalidException(message);
      case "FileUploadFailed":
        throw new JWPlatformFileUploadFailedException(message);
      case "FileSizeMissing":
        throw new JWPlatformFileSizeMissingException(message);
      case "FileSizeInvalid":
        throw new JWPlatformFileSizeInvalidException(message);
      case "Internal":
        throw new JWPlatformInternalException(message);
      case "ApiKeyMissing":
        throw new JWPlatformApiKeyMissingException(message);
      case "ApiKeyInvalid":
        throw new JWPlatformApiKeyInvalidException(message);
      case "TimestampMissing":
        throw new JWPlatformTimestampMissingException(message);
      case "TimestampInvalid":
        throw new JWPlatformTimestampInvalidException(message);
      case "NonceInvalid":
        throw new JWPlatformNonceInvalidException(message);
      case "SignatureMissingEr":
        throw new JWPlatformSignatureMissingErException(message);
      case "SignatureInvalid":
        throw new JWPlatformSignatureInvalidException(message);
      case "RateLimitExceeded":
        throw new JWPlatformRateLimitExceededException(message);
      default:
        throw new JWPlatformUnknownException(message);
    }
  }

  /** Class for an unknown exception. */
  public static class JWPlatformUnknownException extends JWPlatformException {

    /**
     * Instance of an unknown exception.
     *
     * @param message - exception message
     */
    public JWPlatformUnknownException(final String message) {
      super(message);
    }
  }

  /** Class for an not found exception. */
  public static class JWPlatformNotFoundException extends JWPlatformException {

    /**
     * Instance of a not found exception.
     *
     * @param message - exception message
     */
    public JWPlatformNotFoundException(final String message) {
      super(message);
    }
  }

  /** Class for a no method exception. */
  public static class JWPlatformNoMethodException extends JWPlatformException {

    /**
     * Instance of a no method exception.
     *
     * @param message - exception message
     */
    public JWPlatformNoMethodException(final String message) {
      super(message);
    }
  }

  /** Class for a not implemented exception. */
  public static class JWPlatformNotImplementedException extends JWPlatformException {

    /**
     * Instance of a not implemented exception.
     *
     * @param message - exception message
     */
    public JWPlatformNotImplementedException(final String message) {
      super(message);
    }
  }

  /** Class for a not supported exception. */
  public static class JWPlatformNotSupportedException extends JWPlatformException {

    /**
     * Instance of a not implemented exception.
     *
     * @param message - exception message
     */
    public JWPlatformNotSupportedException(final String message) {
      super(message);
    }
  }

  /** Class for a call failed exception. */
  public static class JWPlatformCallFailedException extends JWPlatformException {

    /**
     * Instance of a call failed exception.
     *
     * @param message - exception message
     */
    public JWPlatformCallFailedException(final String message) {
      super(message);
    }
  }

  /** Class for a call unavailable exception. */
  public static class JWPlatformCallUnavailableException extends JWPlatformException {

    /**
     * Instance of a call unavailable exception.
     *
     * @param message - exception message
     */
    public JWPlatformCallUnavailableException(final String message) {
      super(message);
    }
  }

  /** Class for a call invalid exception. */
  public static class JWPlatformCallInvalidException extends JWPlatformException {

    /**
     * Instance of a call invalid exception.
     *
     * @param message - exception message
     */
    public JWPlatformCallInvalidException(final String message) {
      super(message);
    }
  }

  /** Class for a parameter missing exception. */
  public static class JWPlatformParameterMissingException extends JWPlatformException {

    /**
     * Instance of a parameter missing exception.
     *
     * @param message - exception message
     */
    public JWPlatformParameterMissingException(final String message) {
      super(message);
    }
  }

  /** Class for a parameter empty exception. */
  public static class JWPlatformParameterEmptyException extends JWPlatformException {

    /**
     * Instance of a parameter empty exception.
     *
     * @param message - exception message
     */
    public JWPlatformParameterEmptyException(final String message) {
      super(message);
    }
  }

  /** Class for a parameter encoding exception. */
  public static class JWPlatformParameterEncodingException extends JWPlatformException {

    /**
     * Instance of a parameter encoding exception.
     *
     * @param message - exception message
     */
    public JWPlatformParameterEncodingException(final String message) {
      super(message);
    }
  }

  /** Class for a parameter invalid exception. */
  public static class JWPlatformParameterInvalidException extends JWPlatformException {

    /**
     * Instance of a parameter invalid exception.
     *
     * @param message - exception message
     */
    public JWPlatformParameterInvalidException(final String message) {
      super(message);
    }
  }

  /** Class for a precondition failed exception. */
  public static class JWPlatformPreconditionFailedException extends JWPlatformException {

    /**
     * Instance of a precondition failed exception.
     *
     * @param message - exception message
     */
    public JWPlatformPreconditionFailedException(final String message) {
      super(message);
    }
  }

  /** Class for an item already exists exception. */
  public static class JWPlatformItemAlreadyExistsException extends JWPlatformException {

    /**
     * Instance of an item already exists exception.
     *
     * @param message - exception message
     */
    public JWPlatformItemAlreadyExistsException(final String message) {
      super(message);
    }
  }

  /** Class for a permission denied exception. */
  public static class JWPlatformPermissionDeniedException extends JWPlatformException {

    /**
     * Instance of a permission denied exception.
     *
     * @param message - exception message
     */
    public JWPlatformPermissionDeniedException(final String message) {
      super(message);
    }
  }

  /** Class for a database exception. */
  public static class JWPlatformDatabaseException extends JWPlatformException {

    /**
     * Instance of a database exception.
     *
     * @param message - exception message
     */
    public JWPlatformDatabaseException(final String message) {
      super(message);
    }
  }

  /** Class for an integrity exception. */
  public static class JWPlatformIntegrityException extends JWPlatformException {

    /**
     * Instance of an integrity exception.
     *
     * @param message - exception message
     */
    public JWPlatformIntegrityException(final String message) {
      super(message);
    }
  }

  /** Class for a digest missing exception. */
  public static class JWPlatformDigestMissingException extends JWPlatformException {

    /**
     * Instance of a digest missing exception.
     *
     * @param message - exception message
     */
    public JWPlatformDigestMissingException(final String message) {
      super(message);
    }
  }

  /** Class for a digest invalid exception. */
  public static class JWPlatformDigestInvalidException extends JWPlatformException {

    /**
     * Instance of a digest invalid exception.
     *
     * @param message - exception message
     */
    public JWPlatformDigestInvalidException(final String message) {
      super(message);
    }
  }

  /** Class for a file upload failed exception. */
  public static class JWPlatformFileUploadFailedException extends JWPlatformException {

    /**
     * Instance of a file upload failed exception.
     *
     * @param message - exception message
     */
    public JWPlatformFileUploadFailedException(final String message) {
      super(message);
    }
  }

  /** Class for a file size missing exception. */
  public static class JWPlatformFileSizeMissingException extends JWPlatformException {

    /**
     * Instance of a file size missing exception.
     *
     * @param message - exception message
     */
    public JWPlatformFileSizeMissingException(final String message) {
      super(message);
    }
  }

  /** Class for a file size invalid exception. */
  public static class JWPlatformFileSizeInvalidException extends JWPlatformException {

    /**
     * Instance of a file size invalid exception.
     *
     * @param message - exception message
     */
    public JWPlatformFileSizeInvalidException(final String message) {
      super(message);
    }
  }

  /** Class for a internal exception. */
  public static class JWPlatformInternalException extends JWPlatformException {

    /**
     * Instance of a internal exception.
     *
     * @param message - exception message
     */
    public JWPlatformInternalException(final String message) {
      super(message);
    }
  }

  /** Class for an api key missing exception. */
  public static class JWPlatformApiKeyMissingException extends JWPlatformException {

    /**
     * Instance of an api key missing exception.
     *
     * @param message - exception message
     */
    public JWPlatformApiKeyMissingException(final String message) {
      super(message);
    }
  }

  /** Class for an api key invalid exception. */
  public static class JWPlatformApiKeyInvalidException extends JWPlatformException {

    /**
     * Instance of an api key invalid exception.
     *
     * @param message - exception message
     */
    public JWPlatformApiKeyInvalidException(final String message) {
      super(message);
    }
  }

  /** Class for a timestamp missing exception. */
  public static class JWPlatformTimestampMissingException extends JWPlatformException {

    /**
     * Instance of a timestamp missing exception.
     *
     * @param message - exception message
     */
    public JWPlatformTimestampMissingException(final String message) {
      super(message);
    }
  }

  /** Class for a timestamp invalid exception. */
  public static class JWPlatformTimestampInvalidException extends JWPlatformException {

    /**
     * Instance of a timestamp invalid exception.
     *
     * @param message - exception message
     */
    public JWPlatformTimestampInvalidException(final String message) {
      super(message);
    }
  }

  /** Class for a nonce invalid exception. */
  public static class JWPlatformNonceInvalidException extends JWPlatformException {

    /**
     * Instance of a nonce invalid exception.
     *
     * @param message - exception message
     */
    public JWPlatformNonceInvalidException(final String message) {
      super(message);
    }
  }

  /** Class for a signature missing exception. */
  public static class JWPlatformSignatureMissingErException extends JWPlatformException {

    /**
     * Instance of a signature missing exception.
     *
     * @param message - exception message
     */
    public JWPlatformSignatureMissingErException(final String message) {
      super(message);
    }
  }

  /** Class for a signature invalid exception. */
  public static class JWPlatformSignatureInvalidException extends JWPlatformException {

    /**
     * Instance of a signature invalid exception.
     *
     * @param message - exception message
     */
    public JWPlatformSignatureInvalidException(final String message) {
      super(message);
    }
  }

  /** Class for a rate limit exceeded exception. */
  public static class JWPlatformRateLimitExceededException extends JWPlatformException {

    /**
     * Instance of a rate limit exceeded exception.
     *
     * @param message - exception message
     */
    public JWPlatformRateLimitExceededException(final String message) {
      super(message);
    }
  }
}

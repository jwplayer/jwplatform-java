package com.jwplayer.jwplatform.exception;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

/** Factory for creating and throwing JWPlatform Exceptions. */
public class MediaAPIExceptionFactory {

  private static final Map<String, JWPlatformException> exceptions = ImmutableMap.<String, JWPlatformException>builder()
          .put("Unknown", new MediaAPIExceptionFactory.JWPlatformUnknownException())
          .put("NotFound", new MediaAPIExceptionFactory.JWPlatformNotFoundException())
          .put("NoMethod", new MediaAPIExceptionFactory.JWPlatformNoMethodException())
          .put("NotImplemented", new MediaAPIExceptionFactory.JWPlatformNotImplementedException())
          .put("NotSupported", new MediaAPIExceptionFactory.JWPlatformNotSupportedException())
          .put("CallFailed", new MediaAPIExceptionFactory.JWPlatformCallFailedException())
          .put("CallUnavailable", new MediaAPIExceptionFactory.JWPlatformCallUnavailableException())
          .put("CallInvalid", new MediaAPIExceptionFactory.JWPlatformCallInvalidException())
          .put("ParameterMissing", new MediaAPIExceptionFactory.JWPlatformParameterMissingException())
          .put("ParameterEmpty", new MediaAPIExceptionFactory.JWPlatformParameterEmptyException())
          .put("ParameterEncoding", new MediaAPIExceptionFactory.JWPlatformParameterEncodingException())
          .put("ParameterInvalid", new MediaAPIExceptionFactory.JWPlatformParameterInvalidException())
          .put("PreconditionFailed", new MediaAPIExceptionFactory.JWPlatformPreconditionFailedException())
          .put("ItemAlreadyExists", new MediaAPIExceptionFactory.JWPlatformItemAlreadyExistsException())
          .put("PermissionDenied", new MediaAPIExceptionFactory.JWPlatformPermissionDeniedException())
          .put("Database", new MediaAPIExceptionFactory.JWPlatformDatabaseException())
          .put("Integrity", new MediaAPIExceptionFactory.JWPlatformIntegrityException())
          .put("DigestMissing", new MediaAPIExceptionFactory.JWPlatformDigestMissingException())
          .put("DigestInvalid", new MediaAPIExceptionFactory.JWPlatformDigestInvalidException())
          .put("FileUploadFailed", new MediaAPIExceptionFactory.JWPlatformFileUploadFailedException())
          .put("FileSizeMissing", new MediaAPIExceptionFactory.JWPlatformFileSizeMissingException())
          .put("FileSizeInvalid", new MediaAPIExceptionFactory.JWPlatformFileSizeInvalidException())
          .put("Internal", new MediaAPIExceptionFactory.JWPlatformInternalException())
          .put("ApiKeyMissing", new MediaAPIExceptionFactory.JWPlatformApiKeyMissingException())
          .put("ApiKeyInvalid", new MediaAPIExceptionFactory.JWPlatformApiKeyInvalidException())
          .put("TimestampMissing", new MediaAPIExceptionFactory.JWPlatformTimestampMissingException())
          .put("TimestampInvalid", new MediaAPIExceptionFactory.JWPlatformTimestampInvalidException())
          .put("NonceInvalid", new MediaAPIExceptionFactory.JWPlatformNonceInvalidException())
          .put("SignatureMissing", new MediaAPIExceptionFactory.JWPlatformSignatureMissingException())
          .put("SignatureInvalid", new MediaAPIExceptionFactory.JWPlatformSignatureInvalidException())
          .put("RateLimitExceeded", new MediaAPIExceptionFactory.JWPlatformRateLimitExceededException())
          .build();

  /**
   * A case statement for finding and throwing the appropriate exception give an error from the
   * JWPlatform API.
   *
   * @param errorType - the error type
   * @param message - error message
   * @throws JWPlatformException - JWPlatform API returned exception
   */
  public static void throwJWPlatformException(final String errorType, final String message) throws JWPlatformException {
    final JWPlatformException exception =
            exceptions.containsKey(errorType) ? exceptions.get(errorType) : exceptions.get("Unknown");
    exception.initCause(new Exception(message));

    throw exception;
  }

  /** Class for an not found exception. */
  public static class JWPlatformUnknownException extends JWPlatformException {

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

  /** Class for an not found exception. */
  public static class JWPlatformNotFoundException extends JWPlatformException {
  }

  /** Class for a no method exception. */
  public static class JWPlatformNoMethodException extends JWPlatformException {
  }

  /** Class for a not implemented exception. */
  public static class JWPlatformNotImplementedException extends JWPlatformException {
  }

  /** Class for a not supported exception. */
  public static class JWPlatformNotSupportedException extends JWPlatformException {
  }

  /** Class for a call failed exception. */
  public static class JWPlatformCallFailedException extends JWPlatformException {
  }

  /** Class for a call unavailable exception. */
  public static class JWPlatformCallUnavailableException extends JWPlatformException {
  }

  /** Class for a call invalid exception. */
  public static class JWPlatformCallInvalidException extends JWPlatformException {
  }

  /** Class for a parameter missing exception. */
  public static class JWPlatformParameterMissingException extends JWPlatformException {
  }

  /** Class for a parameter empty exception. */
  public static class JWPlatformParameterEmptyException extends JWPlatformException {
  }

  /** Class for a parameter encoding exception. */
  public static class JWPlatformParameterEncodingException extends JWPlatformException {
  }

  /** Class for a parameter invalid exception. */
  public static class JWPlatformParameterInvalidException extends JWPlatformException {
  }

  /** Class for a precondition failed exception. */
  public static class JWPlatformPreconditionFailedException extends JWPlatformException {
  }

  /** Class for an item already exists exception. */
  public static class JWPlatformItemAlreadyExistsException extends JWPlatformException {
  }

  /** Class for a permission denied exception. */
  public static class JWPlatformPermissionDeniedException extends JWPlatformException {
  }

  /** Class for a database exception. */
  public static class JWPlatformDatabaseException extends JWPlatformException {
  }

  /** Class for an integrity exception. */
  public static class JWPlatformIntegrityException extends JWPlatformException {
  }

  /** Class for a digest missing exception. */
  public static class JWPlatformDigestMissingException extends JWPlatformException {
  }

  /** Class for a digest invalid exception. */
  public static class JWPlatformDigestInvalidException extends JWPlatformException {
  }

  /** Class for a file upload failed exception. */
  public static class JWPlatformFileUploadFailedException extends JWPlatformException {
  }

  /** Class for a file size missing exception. */
  public static class JWPlatformFileSizeMissingException extends JWPlatformException {
  }

  /** Class for a file size invalid exception. */
  public static class JWPlatformFileSizeInvalidException extends JWPlatformException {
  }

  /** Class for a internal exception. */
  public static class JWPlatformInternalException extends JWPlatformException {
  }

  /** Class for an api key missing exception. */
  public static class JWPlatformApiKeyMissingException extends JWPlatformException {
  }

  /** Class for an api key invalid exception. */
  public static class JWPlatformApiKeyInvalidException extends JWPlatformException {
  }

  /** Class for a timestamp missing exception. */
  public static class JWPlatformTimestampMissingException extends JWPlatformException {
  }

  /** Class for a timestamp invalid exception. */
  public static class JWPlatformTimestampInvalidException extends JWPlatformException {
  }

  /** Class for a nonce invalid exception. */
  public static class JWPlatformNonceInvalidException extends JWPlatformException {
  }

  /** Class for a signature missing exception. */
  public static class JWPlatformSignatureMissingException extends JWPlatformException {
  }

  /** Class for a signature invalid exception. */
  public static class JWPlatformSignatureInvalidException extends JWPlatformException {
  }

  /** Class for a rate limit exceeded exception. */
  public static class JWPlatformRateLimitExceededException extends JWPlatformException {
  }
}

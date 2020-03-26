package com.jwplayer.jwplatform.exception;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

/** Factory for creating and throwing JWPlatform Exceptions. */
public class MediaAPIExceptionFactory {

  private static final Map<String, Class> exceptions = ImmutableMap.<String, Class>builder()
          .put("Unknown", JWPlatformUnknownException.class)
          .put("NotFound", JWPlatformNotFoundException.class)
          .put("NoMethod", JWPlatformNoMethodException.class)
          .put("NotImplemented", JWPlatformNotImplementedException.class)
          .put("NotSupported", JWPlatformNotSupportedException.class)
          .put("CallFailed", JWPlatformCallFailedException.class)
          .put("CallUnavailable", JWPlatformCallUnavailableException.class)
          .put("CallInvalid", JWPlatformCallInvalidException.class)
          .put("ParameterMissing", JWPlatformParameterMissingException.class)
          .put("ParameterEmpty", JWPlatformParameterEmptyException.class)
          .put("ParameterEncoding", JWPlatformParameterEncodingException.class)
          .put("ParameterInvalid", JWPlatformParameterInvalidException.class)
          .put("PreconditionFailed", JWPlatformPreconditionFailedException.class)
          .put("ItemAlreadyExists", JWPlatformItemAlreadyExistsException.class)
          .put("PermissionDenied", JWPlatformPermissionDeniedException.class)
          .put("Database", JWPlatformDatabaseException.class)
          .put("Integrity", JWPlatformIntegrityException.class)
          .put("DigestMissing", JWPlatformDigestMissingException.class)
          .put("DigestInvalid", JWPlatformDigestInvalidException.class)
          .put("FileUploadFailed", JWPlatformFileUploadFailedException.class)
          .put("FileSizeMissing", JWPlatformFileSizeMissingException.class)
          .put("FileSizeInvalid", JWPlatformFileSizeInvalidException.class)
          .put("Internal", JWPlatformInternalException.class)
          .put("ApiKeyMissing", JWPlatformApiKeyMissingException.class)
          .put("ApiKeyInvalid", JWPlatformApiKeyInvalidException.class)
          .put("TimestampMissing", JWPlatformTimestampMissingException.class)
          .put("TimestampInvalid", JWPlatformTimestampInvalidException.class)
          .put("NonceInvalid", JWPlatformNonceInvalidException.class)
          .put("SignatureMissing", JWPlatformSignatureMissingException.class)
          .put("SignatureInvalid", JWPlatformSignatureInvalidException.class)
          .put("RateLimitExceeded", JWPlatformRateLimitExceededException.class)
          .build();

  /**
   * A case statement for finding and throwing the appropriate exception give an error from the
   * JWPlatform API.
   *
   * @param errorType - the error type
   * @param message - error message
   * @throws JWPlatformException - JWPlatform API returned exception
   */
  @SuppressWarnings("unchecked")
  public static void throwJWPlatformException(final String errorType, final String message)
          throws JWPlatformException {
    final Class<JWPlatformException> clazz =
            exceptions.containsKey(errorType) ? exceptions.get(errorType) : exceptions.get("Unknown");

    try {
      final JWPlatformException exception = clazz.newInstance();
      exception.initCause(new JWPlatformException(message));
      throw exception;
    } catch (InstantiationException | IllegalAccessException e) {
      throw new JWPlatformUnknownException(message);
    }
  }
}

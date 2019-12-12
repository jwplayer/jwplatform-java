package com.jwplayer.jwplatform.exception;

import com.google.common.collect.ImmutableMap;
import java.util.Map;

/** Factory for creating and throwing JWPlatform Exceptions. */
public class MediaAPIExceptionFactory {

  private static final Map<String, JWPlatformException> exceptions = ImmutableMap.<String, JWPlatformException>builder()
          .put("Unknown", new JWPlatformUnknownException())
          .put("NotFound", new JWPlatformNotFoundException())
          .put("NoMethod", new JWPlatformNoMethodException())
          .put("NotImplemented", new JWPlatformNotImplementedException())
          .put("NotSupported", new JWPlatformNotSupportedException())
          .put("CallFailed", new JWPlatformCallFailedException())
          .put("CallUnavailable", new JWPlatformCallUnavailableException())
          .put("CallInvalid", new JWPlatformCallInvalidException())
          .put("ParameterMissing", new JWPlatformParameterMissingException())
          .put("ParameterEmpty", new JWPlatformParameterEmptyException())
          .put("ParameterEncoding", new JWPlatformParameterEncodingException())
          .put("ParameterInvalid", new JWPlatformParameterInvalidException())
          .put("PreconditionFailed", new JWPlatformPreconditionFailedException())
          .put("ItemAlreadyExists", new JWPlatformItemAlreadyExistsException())
          .put("PermissionDenied", new JWPlatformPermissionDeniedException())
          .put("Database", new JWPlatformDatabaseException())
          .put("Integrity", new JWPlatformIntegrityException())
          .put("DigestMissing", new JWPlatformDigestMissingException())
          .put("DigestInvalid", new JWPlatformDigestInvalidException())
          .put("FileUploadFailed", new JWPlatformFileUploadFailedException())
          .put("FileSizeMissing", new JWPlatformFileSizeMissingException())
          .put("FileSizeInvalid", new JWPlatformFileSizeInvalidException())
          .put("Internal", new JWPlatformInternalException())
          .put("ApiKeyMissing", new JWPlatformApiKeyMissingException())
          .put("ApiKeyInvalid", new JWPlatformApiKeyInvalidException())
          .put("TimestampMissing", new JWPlatformTimestampMissingException())
          .put("TimestampInvalid", new JWPlatformTimestampInvalidException())
          .put("NonceInvalid", new JWPlatformNonceInvalidException())
          .put("SignatureMissing", new JWPlatformSignatureMissingException())
          .put("SignatureInvalid", new JWPlatformSignatureInvalidException())
          .put("RateLimitExceeded", new JWPlatformRateLimitExceededException())
          .build();

  /**
   * A case statement for finding and throwing the appropriate exception give an error from the
   * JWPlatform API.
   *
   * @param errorType - the error type
   * @param message - error message
   * @throws JWPlatformException - JWPlatform API returned exception
   */
  public static void throwJWPlatformException(final String errorType, final String message)
          throws JWPlatformException {
    final JWPlatformException exception =
            exceptions.containsKey(errorType) ? exceptions.get(errorType) : exceptions.get("Unknown");
    exception.initCause(new JWPlatformException(message));

    throw exception;
  }
}

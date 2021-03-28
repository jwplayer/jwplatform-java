package com.jwplayer.jwplatform.exception;

/**
 * Factory for creating and throwing JWPlatform Exceptions.
 */
public class MediaAPIExceptionFactory {

  /**
   * A case statement for finding and throwing the appropriate exception give an error from the
   * JWPlatform API.
   *
   * @param errorType - the error type
   * @param message   - error message
   * @throws JWPlatformException - JWPlatform API returned exception
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
      case "SignatureMissing":
        throw new JWPlatformSignatureMissingException(message);
      case "SignatureInvalid":
        throw new JWPlatformSignatureInvalidException(message);
      case "RateLimitExceeded":
        throw new JWPlatformRateLimitExceededException(message);
      default:
        throw new JWPlatformUnknownException(message);
    }
  }
}

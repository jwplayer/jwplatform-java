package com.jwplayer.jwplatform;

import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import com.jwplayer.jwplatform.exception.JWPlatformApiKeyInvalidException;
import com.jwplayer.jwplatform.exception.JWPlatformApiKeyMissingException;
import com.jwplayer.jwplatform.exception.JWPlatformCallFailedException;
import com.jwplayer.jwplatform.exception.JWPlatformCallInvalidException;
import com.jwplayer.jwplatform.exception.JWPlatformCallUnavailableException;
import com.jwplayer.jwplatform.exception.JWPlatformDatabaseException;
import com.jwplayer.jwplatform.exception.JWPlatformDigestInvalidException;
import com.jwplayer.jwplatform.exception.JWPlatformDigestMissingException;
import com.jwplayer.jwplatform.exception.JWPlatformFileSizeInvalidException;
import com.jwplayer.jwplatform.exception.JWPlatformFileSizeMissingException;
import com.jwplayer.jwplatform.exception.JWPlatformFileUploadFailedException;
import com.jwplayer.jwplatform.exception.JWPlatformIntegrityException;
import com.jwplayer.jwplatform.exception.JWPlatformInternalException;
import com.jwplayer.jwplatform.exception.JWPlatformItemAlreadyExistsException;
import com.jwplayer.jwplatform.exception.JWPlatformNoMethodException;
import com.jwplayer.jwplatform.exception.JWPlatformNonceInvalidException;
import com.jwplayer.jwplatform.exception.JWPlatformNotFoundException;
import com.jwplayer.jwplatform.exception.JWPlatformNotImplementedException;
import com.jwplayer.jwplatform.exception.JWPlatformNotSupportedException;
import com.jwplayer.jwplatform.exception.JWPlatformParameterEmptyException;
import com.jwplayer.jwplatform.exception.JWPlatformParameterEncodingException;
import com.jwplayer.jwplatform.exception.JWPlatformParameterInvalidException;
import com.jwplayer.jwplatform.exception.JWPlatformParameterMissingException;
import com.jwplayer.jwplatform.exception.JWPlatformPermissionDeniedException;
import com.jwplayer.jwplatform.exception.JWPlatformPreconditionFailedException;
import com.jwplayer.jwplatform.exception.JWPlatformRateLimitExceededException;
import com.jwplayer.jwplatform.exception.JWPlatformSignatureInvalidException;
import com.jwplayer.jwplatform.exception.JWPlatformSignatureMissingException;
import com.jwplayer.jwplatform.exception.JWPlatformTimestampInvalidException;
import com.jwplayer.jwplatform.exception.JWPlatformTimestampMissingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.request.GetRequest;
import java.util.Arrays;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(Parameterized.class)
@PrepareForTest({Unirest.class, HttpResponse.class})
public class TestJWPlatformClientExceptions {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Parameterized.Parameter
    public String exceptionName;
    @Parameterized.Parameter(1)
    public Class expectedException;

    @Parameterized.Parameters(name = "{index}: testCorrectJWPlatformExceptionRaised({0})={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(
                new Object[][] {
                        {"NotFoundError", JWPlatformNotFoundException.class},
                        {"NoMethod", JWPlatformNoMethodException.class},
                        {"NotImplemented", JWPlatformNotImplementedException.class},
                        {"NotSupported", JWPlatformNotSupportedException.class},
                        {"CallFailed", JWPlatformCallFailedException.class},
                        {"CallUnavailable", JWPlatformCallUnavailableException.class},
                        {"CallInvalid", JWPlatformCallInvalidException.class},
                        {"ParameterMissing", JWPlatformParameterMissingException.class},
                        {"ParameterEmpty", JWPlatformParameterEmptyException.class},
                        {"ParameterEncoding", JWPlatformParameterEncodingException.class},
                        {"ParameterInvalid", JWPlatformParameterInvalidException.class},
                        {"PreconditionFailed", JWPlatformPreconditionFailedException.class},
                        {"ItemAlreadyExists", JWPlatformItemAlreadyExistsException.class},
                        {"PermissionDenied", JWPlatformPermissionDeniedException.class},
                        {"Database", JWPlatformDatabaseException.class},
                        {"Integrity", JWPlatformIntegrityException.class},
                        {"DigestMissing", JWPlatformDigestMissingException.class},
                        {"DigestInvalid", JWPlatformDigestInvalidException.class},
                        {"FileUploadFailed", JWPlatformFileUploadFailedException.class},
                        {"FileSizeMissing", JWPlatformFileSizeMissingException.class},
                        {"FileSizeInvalid", JWPlatformFileSizeInvalidException.class},
                        {"Internal", JWPlatformInternalException.class},
                        {"ApiKeyMissing", JWPlatformApiKeyMissingException.class},
                        {"ApiKeyInvalid", JWPlatformApiKeyInvalidException.class},
                        {"TimestampMissing", JWPlatformTimestampMissingException.class},
                        {"TimestampInvalid", JWPlatformTimestampInvalidException.class},
                        {"NonceInvalid", JWPlatformNonceInvalidException.class},
                        {"SignatureMissing", JWPlatformSignatureMissingException.class},
                        {"SignatureInvalid", JWPlatformSignatureInvalidException.class},
                        {"RateLimitExceeded", JWPlatformRateLimitExceededException.class}
                });
    }

    @Test
    public void testCorrectJWPlatformExceptionRaised() throws Exception {
        exceptionRule.expect(expectedException);

        final JWPlatformClient mediaAPIClient = JWPlatformClient.create("fakeApiKey", "fakeApiSecret");
        final HttpResponse httpResponse = PowerMockito.mock(HttpResponse.class);
        final GetRequest getRequest = PowerMockito.mock(GetRequest.class);
        final JsonNode non200ResponseBody = new JsonNode("{\"code\":\""+ exceptionName + "\"}");
        mockStatic(Unirest.class);

        when(Unirest.get(anyString())).thenReturn(getRequest);
        when(getRequest.headers(anyMap())).thenReturn(getRequest);
        when(getRequest.asJson()).thenReturn(httpResponse);
        when(httpResponse.getStatus()).thenReturn(418);
        when(httpResponse.getBody()).thenReturn(non200ResponseBody);

        mediaAPIClient.request("/v1/videos/create");
    }
}

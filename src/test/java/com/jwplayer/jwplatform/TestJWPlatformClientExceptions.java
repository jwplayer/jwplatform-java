package com.jwplayer.jwplatform;

import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import com.jwplayer.jwplatform.exception.MediaAPIExceptionFactory;
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
                        {"NotFoundError", MediaAPIExceptionFactory.JWPlatformNotFoundException.class},
                        {"NoMethod", MediaAPIExceptionFactory.JWPlatformNoMethodException.class},
                        {"NotImplemented", MediaAPIExceptionFactory.JWPlatformNotImplementedException.class},
                        {"NotSupported", MediaAPIExceptionFactory.JWPlatformNotSupportedException.class},
                        {"CallFailed", MediaAPIExceptionFactory.JWPlatformCallFailedException.class},
                        {"CallUnavailable", MediaAPIExceptionFactory.JWPlatformCallUnavailableException.class},
                        {"CallInvalid", MediaAPIExceptionFactory.JWPlatformCallInvalidException.class},
                        {"ParameterMissing", MediaAPIExceptionFactory.JWPlatformParameterMissingException.class},
                        {"ParameterEmpty", MediaAPIExceptionFactory.JWPlatformParameterEmptyException.class},
                        {"ParameterEncoding", MediaAPIExceptionFactory.JWPlatformParameterEncodingException.class},
                        {"ParameterInvalid", MediaAPIExceptionFactory.JWPlatformParameterInvalidException.class},
                        {"PreconditionFailed", MediaAPIExceptionFactory.JWPlatformPreconditionFailedException.class},
                        {"ItemAlreadyExists", MediaAPIExceptionFactory.JWPlatformItemAlreadyExistsException.class},
                        {"PermissionDenied", MediaAPIExceptionFactory.JWPlatformPermissionDeniedException.class},
                        {"Database", MediaAPIExceptionFactory.JWPlatformDatabaseException.class},
                        {"Integrity", MediaAPIExceptionFactory.JWPlatformIntegrityException.class},
                        {"DigestMissing", MediaAPIExceptionFactory.JWPlatformDigestMissingException.class},
                        {"DigestInvalid", MediaAPIExceptionFactory.JWPlatformDigestInvalidException.class},
                        {"FileUploadFailed", MediaAPIExceptionFactory.JWPlatformFileUploadFailedException.class},
                        {"FileSizeMissing", MediaAPIExceptionFactory.JWPlatformFileSizeMissingException.class},
                        {"FileSizeInvalid", MediaAPIExceptionFactory.JWPlatformFileSizeInvalidException.class},
                        {"Internal", MediaAPIExceptionFactory.JWPlatformInternalException.class},
                        {"ApiKeyMissing", MediaAPIExceptionFactory.JWPlatformApiKeyMissingException.class},
                        {"ApiKeyInvalid", MediaAPIExceptionFactory.JWPlatformApiKeyInvalidException.class},
                        {"TimestampMissing", MediaAPIExceptionFactory.JWPlatformTimestampMissingException.class},
                        {"TimestampInvalid", MediaAPIExceptionFactory.JWPlatformTimestampInvalidException.class},
                        {"NonceInvalid", MediaAPIExceptionFactory.JWPlatformNonceInvalidException.class},
                        {"SignatureMissing", MediaAPIExceptionFactory.JWPlatformSignatureMissingErException.class},
                        {"SignatureInvalid", MediaAPIExceptionFactory.JWPlatformSignatureInvalidException.class},
                        {"RateLimitExceeded", MediaAPIExceptionFactory.JWPlatformRateLimitExceededException.class}
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

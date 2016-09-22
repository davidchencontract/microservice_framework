package uk.gov.justice.services.test.utils.core.http;

import static java.util.Optional.empty;
import static java.util.Optional.of;

import uk.gov.justice.services.test.utils.core.rest.RestClient;

import java.util.Optional;

import javax.ws.rs.core.Response;

import com.google.common.annotations.VisibleForTesting;

public class ValidatingRestClient {

    private final RestClient restClient;
    private final ResponseValidator responseValidator;

    public ValidatingRestClient() {
        this(new RestClient(), new ResponseValidator());
    }

    @VisibleForTesting
    public ValidatingRestClient(final RestClient restClient, final ResponseValidator responseValidator) {
        this.restClient = restClient;
        this.responseValidator = responseValidator;
    }

    public Optional<ResponseDetails> get(final PollingRequestParams pollingRequestParams) {

        final Response response = restClient.query(
                pollingRequestParams.getUrl(),
                pollingRequestParams.getMediaType(),
                pollingRequestParams.getHeaders());


        final int status = response.getStatus();
        final String responseBody = response.readEntity(String.class);

        if (responseValidator.isValid(responseBody, status, pollingRequestParams)) {
            return of(new ResponseDetails(status, responseBody));
        }

        return empty();
    }
}

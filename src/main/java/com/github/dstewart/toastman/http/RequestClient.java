package com.github.dstewart.toastman.http;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class RequestClient {

    private static final int TIMEOUT_SECONDS = 10;

    private final HttpClient httpClient;

    public RequestClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Response sendRequest(String uri, Method method, String body) throws RequestException {
        try {
            var httpRequestBuilder = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .timeout(Duration.ofSeconds(TIMEOUT_SECONDS));
            if (method == Method.GET) {
                httpRequestBuilder = httpRequestBuilder.GET();
            } else if (method == Method.POST) {
                httpRequestBuilder = httpRequestBuilder.POST(HttpRequest.BodyPublishers.ofString(body));
            } else {
                throw new IllegalArgumentException("Invalid HTTP method " + method);
            }

            HttpRequest httpRequest = httpRequestBuilder.build();
            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String contentTypeHeader = response.headers().firstValue("Content-Type").orElse("");
            return new Success(response.statusCode(), response.body(), ContentType.fromString(contentTypeHeader));
        } catch (Exception ex) {
            var rootCause = ExceptionUtils.getRootCause(ex);
            throw new RequestException(rootCause.getClass().getSimpleName());
        }
    }
}

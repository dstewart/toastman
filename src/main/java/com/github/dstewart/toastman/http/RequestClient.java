package com.github.dstewart.toastman.http;

import org.apache.commons.lang3.exception.ExceptionUtils;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestClient {

    private final HttpClient httpClient;

    public RequestClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Response sendRequest(String uri, String method) throws RequestException {
        try {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .GET()
                    .header("Accept", "application/json")
                    .build();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return new Success(response.statusCode(), response.body());
        } catch (Exception ex) {
            var rootCause = ExceptionUtils.getRootCause(ex);
            throw new RequestException(rootCause.getClass().getSimpleName());
        }
    }
}

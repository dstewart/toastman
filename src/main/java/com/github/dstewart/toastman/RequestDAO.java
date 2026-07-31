package com.github.dstewart.toastman;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class RequestDAO {
    public int makeHttpRequest(RequestDTO request) {
        try (var httpClient = HttpClient.newHttpClient()) {
            HttpRequest httpRequest = HttpRequest.newBuilder()
                        .uri(URI.create(request.getUri()))
                        .GET()
                        .header("Accept", "application/json")
                        .build();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            return response.statusCode();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}

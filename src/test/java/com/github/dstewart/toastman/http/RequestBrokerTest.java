package com.github.dstewart.toastman.http;

import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestBrokerTest {

    private final RequestBroker broker = new RequestBroker(new RequestDAO(new RequestClient(HttpClient.newHttpClient())));

    @Test
    public void createRequestDTOGetURI() {
        Request request = new Request();
        request.setUri("https://www.google.com");
        request.setMethod("GET");
        assertEquals("https://www.google.com", broker.createRequestDTO(request).getUri());
    }

    @Test
    public void createRequestDTOGetMethod() {
        Request request = new Request();
        request.setUri("https://www.google.com");
        request.setMethod("GET");
        assertEquals("GET", broker.createRequestDTO(request).getMethod());
    }
}

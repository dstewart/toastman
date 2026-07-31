package com.github.dstewart.toastman;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestBrokerTest {

    private final RequestBroker broker = new RequestBroker(new RequestDAO(new RequestClient()));

    @Test
    public void createRequestDTOGetURI() {
        Request request = new Request();
        request.setUri("http://www.google.com");
        request.setMethod("GET");
        assertEquals("http://www.google.com", broker.createRequestDTO(request).getUri());
    }

    @Test
    public void createRequestDTOGetMethod() {
        Request request = new Request();
        request.setUri("http://www.google.com");
        request.setMethod("GET");
        assertEquals("GET", broker.createRequestDTO(request).getMethod());
    }
}

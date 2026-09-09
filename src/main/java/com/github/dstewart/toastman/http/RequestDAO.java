package com.github.dstewart.toastman.http;

public class RequestDAO {
    private final RequestClient client;

    public RequestDAO(RequestClient client) {
        this.client = client;
    }

    public Response makeHttpRequest(RequestDTO request) {
        try {
            return client.sendRequest(request.getUri(), request.getMethod(), request.getBody());
        }  catch (RequestException e) {
            return new Failure(e.getMessage());
        }
    }
}

package com.github.dstewart.toastman.http;

public class RequestDAO {
    private final RequestClient client;

    public RequestDAO(RequestClient client) {
        this.client = client;
    }

    public Response makeHttpRequest(RequestDTO request) {
        return client.sendRequest(request.getUri(), request.getMethod());
    }
}

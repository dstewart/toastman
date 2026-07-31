package com.github.dstewart.toastman;

public class RequestDAO {
    private final RequestClient client;

    public RequestDAO(RequestClient client) {
        this.client = client;
    }

    public int makeHttpRequest(RequestDTO request) {
        return client.sendRequest(request.getUri(),  request.getMethod());
    }
}

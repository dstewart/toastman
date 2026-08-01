package com.github.dstewart.toastman.http;

public class RequestBroker {

    private final RequestDAO dao;

    public RequestBroker(RequestDAO dao) {
        this.dao = dao;
    }

    public Response makeRequest(Request request) {
        return dao.makeHttpRequest(createRequestDTO(request));
    }

    RequestDTO createRequestDTO(Request request) {
        return new RequestDTO(request.getUri(), request.getMethod());
    }
}

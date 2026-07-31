package com.github.dstewart.toastman;

public class RequestBroker {

    private final RequestDAO dao;

    public RequestBroker(RequestDAO dao) {
        this.dao = dao;
    }

    public int makeRequest(Request request) {
        return dao.makeHttpRequest(createRequestDTO(request));
    }

    RequestDTO createRequestDTO(Request request) {
        return new RequestDTO(request.getUri(), request.getMethod());
    }
}

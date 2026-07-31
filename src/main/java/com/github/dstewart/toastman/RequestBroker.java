package com.github.dstewart.toastman;

public class RequestBroker {
    private final RequestDAO dao = new RequestDAO();

    public int makeRequest(Request request) {
        return dao.makeHttpRequest(createRequestDTO(request));
    }

    RequestDTO createRequestDTO(Request request) {
        return new RequestDTO(request.getUri(), request.getMethod());
    }
}

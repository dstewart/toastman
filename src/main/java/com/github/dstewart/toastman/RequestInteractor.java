package com.github.dstewart.toastman;

public class RequestInteractor {

    private final RequestModel model;
    private final RequestBroker broker;

    public RequestInteractor(RequestModel model) {
        this.model = model;
        this.broker = new RequestBroker(new RequestDAO(new RequestClient()));
    }

    public void sendHttp() {
        int statusCode = broker.makeRequest(createRequestFromModel());
        System.out.println(model.getHttpMethod() + " " + model.getUriAddress() + ": " + statusCode);
    }

    Request createRequestFromModel() {
        Request request = new Request();
        request.setMethod(model.getHttpMethod());
        request.setUri(model.getUriAddress());
        return request;
    }
}

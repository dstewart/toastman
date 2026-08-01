package com.github.dstewart.toastman;

import javafx.beans.binding.Bindings;

public class RequestInteractor {

    private final RequestModel model;
    private final RequestBroker broker;

    public RequestInteractor(RequestModel model) {
        this.model = model;
        this.broker = new RequestBroker(new RequestDAO(new RequestClient()));

        RequestValidator validator = new RequestValidator(model);
        model.isValidProperty().bind(Bindings.createBooleanBinding(validator::validate, model.uriAddressProperty(), model.httpMethodProperty()));
    }

    public Response sendHttp() {
        return broker.makeRequest(createRequestFromModel());
    }

    public void updateLastResponse(Response lastResponse) {
        String message = String.valueOf(lastResponse.statusCode());
        model.setLastResponse(message);
    }

    Request createRequestFromModel() {
        Request request = new Request();
        request.setMethod(model.getHttpMethod());
        request.setUri(model.getUriAddress());
        return request;
    }
}

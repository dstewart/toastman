package com.github.dstewart.toastman.gui;

import com.github.dstewart.toastman.http.*;
import com.github.dstewart.toastman.util.RequestValidator;
import javafx.beans.binding.Bindings;

import java.net.http.HttpClient;

public class RequestInteractor {

    private final RequestModel model;
    private final RequestBroker broker;

    public RequestInteractor(RequestModel model) {
        this.model = model;
        this.broker = new RequestBroker(new RequestDAO(new RequestClient(HttpClient.newHttpClient())));

        RequestValidator validator = new RequestValidator(model);
        model.isValidProperty().bind(Bindings.createBooleanBinding(validator::validate, model.uriAddressProperty(), model.httpMethodProperty()));
    }

    public Response sendHttp() {
        return broker.makeRequest(createRequestFromModel());
    }

    public void updateLastResponse(Response lastResponse) {
        model.setLastStatus(lastResponse.status());
        model.setStatusColor(lastResponse.color());
        model.setLastBody(lastResponse.body());
    }

    Request createRequestFromModel() {
        Request request = new Request();
        request.setMethod(model.getHttpMethod());
        request.setUri(model.getUriAddress());
        return request;
    }
}

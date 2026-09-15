package com.github.dstewart.toastman.gui;

import com.github.dstewart.toastman.http.*;
import com.github.dstewart.toastman.util.prettifier.Prettifier;
import com.github.dstewart.toastman.util.RequestValidator;
import com.github.dstewart.toastman.util.prettifier.PrettifyException;
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
        model.setLastBody(lastResponse.body());
        try {
            String prettifiedBody = Prettifier.prettify(lastResponse.body(), lastResponse.contentType());
            model.setLastBodyPrettified(prettifiedBody);
        } catch (PrettifyException ex) {
            System.err.println("Error prettifying response body: " + ex.getMessage());
        }
    }

    Request createRequestFromModel() {
        Request request = new Request();
        request.setMethod(model.getHttpMethod());
        request.setUri(model.getUriAddress());
        request.setBody(model.getInputBody());
        return request;
    }
}

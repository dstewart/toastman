package com.github.dstewart.toastman.gui;

import com.github.dstewart.toastman.http.Response;
import com.github.dstewart.toastman.http.Success;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RequestInteractorTest {
    @Test
    public void createRequestGetURI() {
        RequestModel model = new RequestModel();
        RequestInteractor interactor = new RequestInteractor(model);
        model.setUriAddress("https://www.github.com");
        model.setHttpMethod("POST");
        assertEquals("https://www.github.com", interactor.createRequestFromModel().getUri(), "Check request URI");
    }

    @Test
    public void createRequestGetMethod() {
        RequestModel model = new RequestModel();
        RequestInteractor interactor = new RequestInteractor(model);
        model.setUriAddress("https://www.github.com");
        model.setHttpMethod("POST");
        assertEquals("POST", interactor.createRequestFromModel().getMethod(), "Check request method");
    }

    @Test
    public void updateLastResponse() {
        RequestModel model = new RequestModel();
        RequestInteractor interactor = new RequestInteractor(model);
        Response response = new Success(404, "Not Found");
        interactor.updateLastResponse(response);
        assertEquals("404", model.getLastStatus());
        assertEquals(Color.GREEN, model.getStatusColor());
        assertEquals("Not Found", model.getLastBody());
    }
}

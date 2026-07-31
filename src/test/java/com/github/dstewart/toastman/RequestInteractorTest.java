package com.github.dstewart.toastman;

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
}

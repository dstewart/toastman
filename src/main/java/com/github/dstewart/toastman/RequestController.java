package com.github.dstewart.toastman;

import javafx.scene.layout.Region;
import javafx.util.Builder;

public class RequestController {
    private final Builder<Region> viewBuilder;
    private final RequestInteractor interactor;

    public RequestController() {
        RequestModel model = new RequestModel();

        interactor = new RequestInteractor(model);
        viewBuilder = new RequestViewBuilder(model, interactor::sendHttp);
    }

    public Region getView() {
        return viewBuilder.build();
    }
}

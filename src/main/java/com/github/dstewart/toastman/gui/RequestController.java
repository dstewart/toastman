package com.github.dstewart.toastman.gui;

import com.github.dstewart.toastman.http.Response;
import javafx.concurrent.Task;
import javafx.scene.layout.Region;
import javafx.util.Builder;

public class RequestController {
    private final Builder<Region> viewBuilder;
    private final RequestInteractor interactor;

    public RequestController() {
        RequestModel model = new RequestModel();

        interactor = new RequestInteractor(model);
        viewBuilder = new RequestViewBuilder(model, this::makeRequest);
    }

    private void makeRequest(Runnable afterTaskGuiUpdate) {
        Task<Response> sendTask = new Task<>() {
            @Override
            protected Response call() {
                return interactor.sendHttp();
            }
        };
        sendTask.setOnSucceeded(evt -> {
            interactor.updateLastResponse(sendTask.getValue());
            afterTaskGuiUpdate.run();
        });

        new Thread(sendTask).start();
    }

    public Region getView() {
        return viewBuilder.build();
    }
}

package com.github.dstewart.toastman;

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
        Task<Void> sendTask = new Task<>() {
            @Override
            protected Void call() {
                interactor.sendHttp();
                return null;
            }
        };
        sendTask.setOnSucceeded(evt -> afterTaskGuiUpdate.run());

        new Thread(sendTask).start();
    }

    public Region getView() {
        return viewBuilder.build();
    }
}

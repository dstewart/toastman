package com.github.dstewart.toastman;

import com.github.dstewart.toastman.gui.RequestController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(new RequestController().getView()));
        primaryStage.show();
    }
}
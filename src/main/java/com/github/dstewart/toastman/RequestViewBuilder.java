package com.github.dstewart.toastman;

import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

import java.util.Objects;
import java.util.function.Consumer;

public record RequestViewBuilder(RequestModel model, Consumer<Runnable> sendHandler) implements Builder<Region> {
    @Override
    public Region build() {
        BorderPane content = new BorderPane();
        content.getStylesheets().add(
                Objects.requireNonNull(this.getClass().getResource(Constants.STYLESHEET_PATH))
                        .toExternalForm());
        content.setTop(createHeader());
        content.setCenter(createCenter());
        content.setBottom(createButtons());
        return content;
    }

    private Node createHeader() {
        HBox content = new HBox(6, headingLabel(Constants.TITLE));
        content.setAlignment(Pos.CENTER);
        return content;
    }


    private Node createCenter() {
        VBox content = new VBox(6, accountBox(), nameBox());
        content.setPadding(new Insets(20));
        return content;
    }

    private Node accountBox() {
        return new HBox(6, promptLabel("URI:"), boundTextField(model.uriAddressProperty()));
    }

    private Node nameBox() {
        return new HBox(6, promptLabel("Method:"), boundTextField(model.httpMethodProperty()));
    }

    private Node createButtons() {
        Button sendButton = new Button("Send");
        sendButton.setOnAction(evt -> {
            sendButton.setDisable(true);
            sendHandler.accept(() -> sendButton.setDisable(false));
        });

        HBox content = new HBox(10, sendButton);
        content.setAlignment(Pos.CENTER_RIGHT);
        return content;
    }

    private Node boundTextField(StringProperty boundProperty) {
        TextField textField = new TextField();
        textField.textProperty().bindBidirectional(boundProperty);
        return textField;
    }

    private Node promptLabel(String text) {
        return styledLabel(text, Constants.PROMPT_LABEL_CLASS);
    }

    private Node headingLabel(String text) {
        return styledLabel(text, Constants.HEADING_LABEL_CLASS);
    }

    private Node styledLabel(String text, String classSelector) {
        Label label = new Label(text);
        label.getStyleClass().add(classSelector);

        return label;
    }
}

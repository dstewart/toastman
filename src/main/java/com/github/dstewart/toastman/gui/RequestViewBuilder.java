package com.github.dstewart.toastman.gui;

import com.github.dstewart.toastman.http.Method;
import com.github.dstewart.toastman.util.Constants;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.util.Builder;

import java.util.Arrays;
import java.util.List;
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
        content.setBottom(createFooter());
        return content;
    }

    private Node createHeader() {
        HBox content = new HBox(6, headingLabel(Constants.TITLE));
        content.setAlignment(Pos.CENTER);
        return content;
    }

    private Node createCenter() {
        VBox content = new VBox(6, accountBox(), methodBox());
        content.setPadding(new Insets(20));
        return content;
    }

    private Node accountBox() {
        return new HBox(6, promptLabel("URI:"), boundTextField(model.uriAddressProperty()));
    }

    private Node methodBox() {
        return new HBox(6, promptLabel("Method:"), radioButtonGroup());
    }

    private Node createFooter() {
        Label statusLabel = new Label();
        statusLabel.textProperty().bind(model.lastResponseProperty());

        Button sendButton = new Button("Send");
        sendButton.disableProperty().bind(model.isValidProperty().not());
        sendButton.setOnAction(_ -> {
            sendButton.disableProperty().unbind();
            sendButton.setDisable(true);
            statusLabel.textProperty().unbind();
            statusLabel.setText("Sending...");
            sendHandler.accept(() -> {
                sendButton.disableProperty().bind(model.isValidProperty().not());
                statusLabel.textProperty().bind(model.lastResponseProperty());
            });
        });

        HBox content = new HBox(10, statusLabel, sendButton);
        content.setAlignment(Pos.CENTER_RIGHT);
        return content;
    }

    private Node boundTextField(StringProperty boundProperty) {
        TextField textField = new TextField();
        textField.textProperty().bindBidirectional(boundProperty);
        return textField;
    }

    private Node radioButtonGroup() {
        HBox content = new HBox(10);

        ToggleGroup toggleGroup = new ToggleGroup();
        List<RadioButton> radioButtons = Arrays.stream(Method.values())
                                            .map(method -> new RadioButton(method.name()))
                                            .toList();
        radioButtons.forEach(radioButton -> radioButton.setToggleGroup(toggleGroup));

        toggleGroup.selectedToggleProperty().addListener((_, _, newValue) -> {
            if (newValue != null) {
                model.setHttpMethod(((RadioButton) newValue).getText());
            } else {
                model.setHttpMethod(null);
            }
        });
        content.setAlignment(Pos.CENTER_LEFT);
        content.getChildren().addAll(radioButtons);

        return content;
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

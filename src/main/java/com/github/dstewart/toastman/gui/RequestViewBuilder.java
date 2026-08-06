package com.github.dstewart.toastman.gui;

import com.github.dstewart.toastman.http.Method;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Builder;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public record RequestViewBuilder(RequestModel model, Consumer<Runnable> sendHandler) implements Builder<Region> {
    private static final String TITLE = "Toastman";
    private static final String STYLESHEET_PATH = "/css/stylesheet.css";
    private static final String PROMPT_LABEL_CLASS = "prompt-label";
    private static final String HEADING_LABEL_CLASS = "heading-label";

    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int TEXT_AREA_WIDTH = 300;
    private static final int TEXT_AREA_HEIGHT = 400;

    @Override
    public Region build() {
        BorderPane content = new BorderPane();
        content.getStylesheets().add(
                Objects.requireNonNull(this.getClass().getResource(STYLESHEET_PATH))
                        .toExternalForm());
        content.setTop(createHeader());
        content.setCenter(createCenter());
        content.setBottom(createFooter());
        content.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        return content;
    }

    private Node createHeader() {
        HBox content = new HBox(6, headingLabel(TITLE));
        content.setAlignment(Pos.CENTER);
        return content;
    }

    private Node createCenter() {
        VBox content = new VBox(6, accountBox(), methodBox(), inputOutputBox());
        content.setPadding(new Insets(20));
        return content;
    }

    private Node accountBox() {
        return new HBox(6, promptLabel("URI:"), boundTextField(model.uriAddressProperty()));
    }

    private Node methodBox() {
        return new HBox(6, promptLabel("Method:"), radioButtonGroup());
    }

    private Node inputOutputBox() {
        return new HBox(6, promptLabel("Input:"), boundScrollableTextArea(model.inputBodyProperty(), true),
                promptLabel("Output:"), boundScrollableTextArea(model.lastBodyProperty(), false));
    }

    private Node createFooter() {
        Label statusLabel = new Label();
        statusLabel.textProperty().bind(model.lastStatusProperty());
        statusLabel.textFillProperty().bind(model.statusColorProperty());

        Button sendButton = new Button("Send");
        sendButton.disableProperty().bind(model.isValidProperty().not());
        sendButton.setOnAction(_ -> {
            sendButton.disableProperty().unbind();
            sendButton.setDisable(true);
            statusLabel.textProperty().unbind();
            statusLabel.setText("Sending...");
            statusLabel.textFillProperty().unbind();
            statusLabel.setTextFill(Color.BLACK);
            sendHandler.accept(() -> {
                sendButton.disableProperty().bind(model.isValidProperty().not());
                statusLabel.textProperty().bind(model.lastStatusProperty());
                statusLabel.textFillProperty().bind(model.statusColorProperty());
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

    private Node boundTextArea(StringProperty boundProperty, boolean editable) {
        TextArea textArea = new TextArea();
        textArea.setEditable(editable);
        textArea.textProperty().bindBidirectional(boundProperty);
        return textArea;
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

    private Node boundScrollableTextArea(StringProperty boundProperty, boolean editable) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(boundTextArea(boundProperty, editable));
        scrollPane.setPrefSize(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        return scrollPane;
    }

    private Node promptLabel(String text) {
        return styledLabel(text, PROMPT_LABEL_CLASS);
    }

    private Node headingLabel(String text) {
        return styledLabel(text, HEADING_LABEL_CLASS);
    }

    private Node styledLabel(String text, String classSelector) {
        Label label = new Label(text);
        label.getStyleClass().add(classSelector);

        return label;
    }
}

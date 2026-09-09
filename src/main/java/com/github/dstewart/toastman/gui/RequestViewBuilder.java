package com.github.dstewart.toastman.gui;

import com.github.dstewart.toastman.http.Method;
import javafx.beans.binding.Bindings;
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
    private static final int URI_INPUT_WIDTH = 600;

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
        HBox content = new HBox(6, headingLabel());
        content.setAlignment(Pos.CENTER);
        return content;
    }

    private Node createCenter() {
        VBox content = new VBox(6, uriBox(), methodBox(), inputOutputBox());
        content.setPadding(new Insets(20));
        return content;
    }

    private Node uriBox() {
        return new HBox(6, promptLabel("URI:"), boundTextField(model.uriAddressProperty()));
    }

    private Node methodBox() {
        return new HBox(6, promptLabel("Method:"), radioButtonGroup());
    }

    private Node inputOutputBox() {
        var inputArea = boundScrollableTextArea(model.inputBodyProperty(), true);
        inputArea.disableProperty().bind(Bindings.notEqual(Method.POST, model.httpMethodProperty()));
        var outputArea = boundScrollableTextArea(model.lastBodyProperty(), false);
        var prettifyCheckBox = prettifyCheckBox((ScrollPane) outputArea);
        var upperBox = new HBox(6, promptLabel("Input:"), inputArea, promptLabel("Output:"), outputArea);
        var lowerBox = new HBox(6, prettifyCheckBox);
        return new VBox(6, upperBox, lowerBox);
    }

    private Node prettifyCheckBox(ScrollPane outputArea) {
        var prettifyCheckBox = new CheckBox("Prettify");
        prettifyCheckBox.setOnAction(evt -> {
            TextArea textArea = (TextArea) outputArea.getContent();
            if (prettifyCheckBox.isSelected()) {
                textArea.textProperty().bind(model.lastBodyPrettifiedProperty());
            } else {
                textArea.textProperty().bind(model.lastBodyProperty());
            }
        });
        return prettifyCheckBox;
    }

    private Node createFooter() {
        Label statusLabel = new Label();
        statusLabel.textProperty().bind(model.lastStatusProperty());
        statusLabel.textFillProperty().bind(model.statusColorProperty());

        Button sendButton = new Button("Send");
        sendButton.setDefaultButton(true);
        sendButton.disableProperty().bind(model.isValidProperty().not());
        sendButton.setOnAction(evt -> {
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
        textField.setPrefWidth(URI_INPUT_WIDTH);
        return textField;
    }

    private Node boundInputArea(StringProperty boundProperty) {
        TextArea textArea = new TextArea();
        textArea.setEditable(true);
        textArea.textProperty().bindBidirectional(boundProperty);
        return textArea;
    }

    private Node boundOutputArea(StringProperty boundProperty) {
        TextArea textArea = new TextArea();
        textArea.textProperty().bind(boundProperty);
        return textArea;
    }

    private Node radioButtonGroup() {
        HBox content = new HBox(10);

        ToggleGroup toggleGroup = new ToggleGroup();
        List<RadioButton> radioButtons = Arrays.stream(Method.values())
                                            .map(method -> new RadioButton(method.name()))
                                            .toList();
        radioButtons.forEach(radioButton -> radioButton.setToggleGroup(toggleGroup));

        toggleGroup.selectedToggleProperty().addListener((
                observable,
                oldValue,
                newValue) -> {
            if (newValue != null) {
                var buttonText = ((RadioButton) newValue).getText();
                model.setHttpMethod(Method.fromString(buttonText));
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
        Node content = editable ? boundInputArea(boundProperty) : boundOutputArea(boundProperty);
        scrollPane.setContent(content);
        scrollPane.setPrefSize(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        return scrollPane;
    }

    private Node promptLabel(String text) {
        return styledLabel(text, PROMPT_LABEL_CLASS);
    }

    private Node headingLabel() {
        return styledLabel(TITLE, HEADING_LABEL_CLASS);
    }

    private Node styledLabel(String text, String classSelector) {
        Label label = new Label(text);
        label.getStyleClass().add(classSelector);

        return label;
    }
}

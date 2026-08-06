package com.github.dstewart.toastman.gui;

import javafx.beans.property.*;
import javafx.scene.paint.Color;

public class RequestModel {
    private final StringProperty uriAddress = new SimpleStringProperty("");
    private final StringProperty httpMethod = new SimpleStringProperty("");
    private final StringProperty inputBody = new SimpleStringProperty("");
    private final BooleanProperty isValid = new SimpleBooleanProperty(false);

    private final ObjectProperty<Color> statusColor = new SimpleObjectProperty<>(Color.GREEN);
    private final StringProperty lastBody = new SimpleStringProperty("");
    private final StringProperty lastResponse = new SimpleStringProperty("");

    public String getUriAddress() {
        return uriAddress.get();
    }

    public void setUriAddress(String uriAddress) {
        this.uriAddress.set(uriAddress);
    }

    public String getHttpMethod() {
        return httpMethod.get();
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod.set(httpMethod);
    }

    public void setLastResponse(String lastResponse) {
        this.lastResponse.set(lastResponse);
    }

    public void setStatusColor(Color color) {
        this.statusColor.set(color);
    }

    public StringProperty httpMethodProperty() {
        return httpMethod;
    }

    public StringProperty uriAddressProperty() {
        return uriAddress;
    }

    public BooleanProperty isValidProperty() {
        return isValid;
    }

    public StringProperty lastResponseProperty() {
        return lastResponse;
    }

    public StringProperty inputBodyProperty() {
        return inputBody;
    }

    public StringProperty lastBodyProperty() {
        return lastBody;
    }

    public ObjectProperty<Color> statusColorProperty() {
        return statusColor;
    }
}

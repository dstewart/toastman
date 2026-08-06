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
    private final StringProperty lastStatus = new SimpleStringProperty("");

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

    public String getLastStatus() {
        return this.lastStatus.get();
    }

    public void setLastStatus(String lastStatus) {
        this.lastStatus.set(lastStatus);
    }

    public Color getStatusColor() {
        return this.statusColor.get();
    }

    public void setStatusColor(Color color) {
        this.statusColor.set(color);
    }

    public String getLastBody() {
        return this.lastBody.get();
    }

    public void setLastBody(String body) {
        this.lastBody.set(body);
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

    public StringProperty lastStatusProperty() {
        return lastStatus;
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

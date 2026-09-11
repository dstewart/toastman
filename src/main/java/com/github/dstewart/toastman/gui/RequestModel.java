package com.github.dstewart.toastman.gui;

import com.github.dstewart.toastman.http.Method;
import javafx.beans.property.*;

public class RequestModel {
    private final StringProperty uriAddress = new SimpleStringProperty("");
    private final ObjectProperty<Method> httpMethod = new SimpleObjectProperty<>(null);
    private final StringProperty inputBody = new SimpleStringProperty("");
    private final BooleanProperty isValid = new SimpleBooleanProperty(false);

    private final StringProperty lastBody = new SimpleStringProperty("");
    private final StringProperty lastBodyPrettified = new SimpleStringProperty("");
    private final StringProperty lastStatus = new SimpleStringProperty("");

    public String getUriAddress() {
        return uriAddress.get();
    }

    public void setUriAddress(String uriAddress) {
        this.uriAddress.set(uriAddress);
    }

    public Method getHttpMethod() {
        return httpMethod.get();
    }

    public void setHttpMethod(Method httpMethod) {
        this.httpMethod.set(httpMethod);
    }

    public String getInputBody() {
        return inputBody.get();
    }

    public String getLastStatus() {
        return this.lastStatus.get();
    }

    public void setLastStatus(String lastStatus) {
        this.lastStatus.set(lastStatus);
    }

    public String getLastBody() {
        return this.lastBody.get();
    }

    public void setLastBody(String body) {
        this.lastBody.set(body);
    }

    public String getLastBodyPrettified() {
        return this.lastBodyPrettified.get();
    }

    public void setLastBodyPrettified(String prettifiedBody) {
        this.lastBodyPrettified.set(prettifiedBody);
    }

    public ObjectProperty<Method> httpMethodProperty() {
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

    public StringProperty lastBodyPrettifiedProperty() {
        return lastBodyPrettified;
    }
}

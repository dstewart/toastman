package com.github.dstewart.toastman;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RequestModel {
    private final StringProperty uriAddress = new SimpleStringProperty("");
    private final StringProperty httpMethod = new SimpleStringProperty("");

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

    public StringProperty httpMethodProperty() {
        return httpMethod;
    }

    public StringProperty uriAddressProperty() {
        return uriAddress;
    }
}

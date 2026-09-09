package com.github.dstewart.toastman.http;

public class RequestDTO {
    private String uri;
    private Method method;
    private String body;

    public RequestDTO(String uri, Method method, String body) {
        this.uri = uri;
        this.method = method;
        this.body = body;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

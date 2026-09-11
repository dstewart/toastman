package com.github.dstewart.toastman.http;

public record Success(int statusCode, String body, ContentType contentType) implements Response {
    @Override
    public String status() {
        return String.valueOf(statusCode);
    }

    @Override
    public String body() {
        return body;
    }

    @Override
    public ContentType contentType() {
        return contentType;
    }
}

package com.github.dstewart.toastman.http;

public record Failure(String errorMessage) implements Response {
    @Override
    public String status() {
        return errorMessage;
    }

    @Override
    public String body() {
        return "";
    }

    @Override
    public ContentType contentType() {
        return ContentType.TEXT;
    }
}

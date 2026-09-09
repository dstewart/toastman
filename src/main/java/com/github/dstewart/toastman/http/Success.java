package com.github.dstewart.toastman.http;

import javafx.scene.paint.Color;

public record Success(int statusCode, String body, ContentType contentType) implements Response {
    @Override
    public String status() {
        return String.valueOf(statusCode);
    }

    @Override
    public Color color() {
        return Color.GREEN;
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

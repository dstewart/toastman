package com.github.dstewart.toastman.http;

import javafx.scene.paint.Color;

public record Success(int statusCode, String body) implements Response {
    @Override
    public String display() {
        return String.valueOf(statusCode);
    }

    @Override
    public Color color() {
        return Color.GREEN;
    }
}

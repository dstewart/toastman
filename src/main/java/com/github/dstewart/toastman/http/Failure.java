package com.github.dstewart.toastman.http;

import javafx.scene.paint.Color;

public record Failure(String errorMessage) implements Response {
    @Override
    public String display() {
        return errorMessage;
    }

    @Override
    public Color color() {
        return Color.RED;
    }
}

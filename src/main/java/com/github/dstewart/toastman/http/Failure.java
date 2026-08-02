package com.github.dstewart.toastman.http;

public record Failure(String errorMessage) implements Response {
    @Override
    public String display() {
        return "Error: " + errorMessage;
    }
}

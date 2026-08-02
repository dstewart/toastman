package com.github.dstewart.toastman.http;

public record Success(int statusCode, String body) implements Response {
    @Override
    public String display() {
        return String.valueOf(statusCode);
    }
}

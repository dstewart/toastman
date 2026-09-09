package com.github.dstewart.toastman.http;

public enum ContentType {
    JSON, HTML, TEXT;

    public static ContentType fromString(String contentType) {
        if (contentType.contains("text/html")) {
            return ContentType.HTML;
        }

        if (contentType.contains("application/json")) {
            return ContentType.JSON;
        }

        return ContentType.TEXT;
    }
}

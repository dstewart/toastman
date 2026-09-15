package com.github.dstewart.toastman.util.prettifier;

import com.github.dstewart.toastman.http.ContentType;

public abstract class Prettifier {
    public abstract String prettify(String body) throws PrettifyException;

    public static String prettify(String body, ContentType contentType) throws PrettifyException {
        return getPrettifier(contentType).prettify(body);
    }

    private static Prettifier getPrettifier(ContentType contentType) {
        return switch (contentType) {
            case JSON -> new JsonPrettifier();
            case HTML -> new HtmlPrettifier();
            case TEXT -> new TextPrettifier();
        };
    }
}

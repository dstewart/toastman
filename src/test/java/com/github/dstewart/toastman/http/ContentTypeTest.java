package com.github.dstewart.toastman.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContentTypeTest {
    @Test
    public void getContentTypeFromString() {
        assertEquals(ContentType.HTML, ContentType.fromString("text/html; charset=ISO-8859-1"));
        assertEquals(ContentType.JSON, ContentType.fromString("application/json; charset=UTF-8"));
        assertEquals(ContentType.TEXT, ContentType.fromString("plain/text"));
        assertEquals(ContentType.TEXT, ContentType.fromString(""));
    }
}

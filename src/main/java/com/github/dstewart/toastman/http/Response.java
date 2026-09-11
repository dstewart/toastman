package com.github.dstewart.toastman.http;

public interface Response {
    String status();
    String body();
    ContentType contentType();
}

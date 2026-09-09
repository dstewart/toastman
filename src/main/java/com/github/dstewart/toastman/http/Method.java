package com.github.dstewart.toastman.http;

public enum Method {
    GET, POST;

    public static Method fromString(String methodName) {
        if (methodName.equals("GET")) {
            return Method.GET;
        }

        if (methodName.equals("POST")) {
            return Method.POST;
        }

        throw new IllegalArgumentException("Invalid method name " + methodName);
    }
}

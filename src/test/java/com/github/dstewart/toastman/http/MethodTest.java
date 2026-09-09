package com.github.dstewart.toastman.http;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MethodTest {
    @Test
    public void getMethodFromString() {
        assertEquals(Method.GET, Method.fromString("GET"));
        assertEquals(Method.POST, Method.fromString("POST"));
        assertThrows(IllegalArgumentException.class, () -> Method.fromString(""));
    }
}

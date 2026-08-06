package com.github.dstewart.toastman.http;

import javafx.scene.paint.Color;

public interface Response {
    String status();
    Color color();
    String body();
}

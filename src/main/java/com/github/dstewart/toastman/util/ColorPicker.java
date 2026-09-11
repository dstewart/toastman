package com.github.dstewart.toastman.util;

import javafx.scene.paint.Color;

public class ColorPicker {
    public static Color fromString(String status) {
        try {
            int statusCode = Integer.parseInt(status);
            if (statusCode < 400) {
                return Color.GREEN;
            } else if (statusCode < 500) {
                return Color.YELLOW;
            } else {
                return Color.ORANGE;
            }
        } catch (NumberFormatException ex) {
            return Color.RED;
        }
    }
}

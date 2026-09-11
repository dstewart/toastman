package com.github.dstewart.toastman.util;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ColorPickerTest {
    @Test
    public void testFromString() {
        assertEquals(Color.GREEN, ColorPicker.fromString("200"));
        assertEquals(Color.YELLOW, ColorPicker.fromString("401"));
        assertEquals(Color.ORANGE, ColorPicker.fromString("504"));
        assertEquals(Color.RED, ColorPicker.fromString("UnresolvedAddressException"));
    }
}

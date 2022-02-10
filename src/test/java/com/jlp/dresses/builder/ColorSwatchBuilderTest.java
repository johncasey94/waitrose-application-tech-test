package com.jlp.dresses.builder;


import com.jlp.dresses.domain.ColorSwatch;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ColorSwatchBuilderTest {

    @Test
    public void testFromMap() {
        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put("color", "Dark Blue");
        inputMap.put("basicColor", "Blue");
        inputMap.put("skuId", "ABC123");

        ColorSwatch colorSwatch = ColorSwatchBuilder.fromMap(inputMap);

        assertEquals("Dark Blue", colorSwatch.getColor());
        assertEquals("#0000FF", colorSwatch.getRgbColor());
        assertEquals("ABC123", colorSwatch.getSkuid());
    }

}

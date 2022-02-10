package com.jlp.dresses.builder;

import com.google.common.collect.ImmutableMap;
import com.jlp.dresses.domain.ColorSwatch;

import java.util.Map;

public class ColorSwatchBuilder {

    private final static ImmutableMap<String, String> COLOR_TO_RGB_COLOR_MAP =
            ImmutableMap.<String, String>builder()
                    .put("Red", "#FF0000")
                    .put("Pink", "#FFC0CB")
                    .put("Yellow", "#FFFF00")
                    .put("Blue", "#0000FF")
                    .put("Purple", "A020F0")
                    .put("Black", "#000000")
                    .put("Green", "#00FF00")
                    .put("Grey", "#808080")
                    // intentionally leave hex value blank here as value is ambiguous
                    .put("Neutrals", "")
                    .put("Multi", "")
                    .build();

    public static ColorSwatch fromMap(Map<String, Object> map) {
        ColorSwatch colorSwatch = new ColorSwatch();

        colorSwatch.setColor((String) map.get("color"));
        colorSwatch.setRgbColor(COLOR_TO_RGB_COLOR_MAP.get((String) map.get("basicColor")));
        colorSwatch.setSkuid((String) map.get("skuId"));

        return colorSwatch;
    }

}

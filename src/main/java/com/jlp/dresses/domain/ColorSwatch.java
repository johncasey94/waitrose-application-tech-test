package com.jlp.dresses.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Java bean for storing information about a color swatch
 *
 * @author John Casey
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ColorSwatch {

    private String color;

    private String rgbColor;

    private String skuid;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getRgbColor() {
        return rgbColor;
    }

    public void setRgbColor(String rgbColor) {
        this.rgbColor = rgbColor;
    }

    public String getSkuid() {
        return skuid;
    }

    public void setSkuid(String skuid) {
        this.skuid = skuid;
    }

}

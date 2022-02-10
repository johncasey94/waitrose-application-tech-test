package com.jlp.dresses.api;

import com.jlp.dresses.domain.ColorSwatch;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductInfo {

    private String title;

    private BigDecimal nowPrice;

    private BigDecimal then1Price;

    private BigDecimal then2Price;

    private BigDecimal wasPrice;

    private boolean discounted;

    private String currency;

    private List<ColorSwatch> colorSwatches = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getNowPrice() {
        return nowPrice;
    }

    public void setNowPrice(BigDecimal nowPrice) {
        this.nowPrice = nowPrice;
    }

    public void setWasPrice(BigDecimal wasPrice) {
        this.wasPrice = wasPrice;
    }

    public BigDecimal getWasPrice() { return wasPrice; }

    public boolean isDiscounted() {
        return discounted;
    }

    public void setDiscounted(boolean discounted) {
        this.discounted = discounted;
    }

    public List<ColorSwatch> getColorSwatches() {
        return colorSwatches;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getThen1Price() {
        return then1Price;
    }

    public void setThen1Price(BigDecimal then1Price) {
        this.then1Price = then1Price;
    }

    public BigDecimal getThen2Price() {
        return then2Price;
    }

    public void setThen2Price(BigDecimal then2Price) {
        this.then2Price = then2Price;
    }
}

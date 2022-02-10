package com.jlp.dresses.domain;

public enum PriceLabelType {

    ShowWasNow,
    ShowWasThenNow,
    ShowPercDiscount;

    public static PriceLabelType getEnumFromString(String value) {
        for(PriceLabelType priceLabelType : PriceLabelType.values()) {
            if(priceLabelType.name().equalsIgnoreCase(value)) {
                return priceLabelType;
            }
        }

        return ShowWasNow;
    }

}

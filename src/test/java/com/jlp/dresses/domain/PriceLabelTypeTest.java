package com.jlp.dresses.domain;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class PriceLabelTypeTest {

    @Test
    public void testGetEnumFromString() {
        assertEquals(PriceLabelType.ShowWasNow, PriceLabelType.getEnumFromString("Not a recognised value"));
        assertEquals(PriceLabelType.ShowWasNow, PriceLabelType.getEnumFromString("ShowWasNow"));
        assertEquals(PriceLabelType.ShowWasThenNow, PriceLabelType.getEnumFromString("SHOWWASTHENNOW"));
        assertEquals(PriceLabelType.ShowPercDiscount, PriceLabelType.getEnumFromString("showpercdiscount"));
    }

}

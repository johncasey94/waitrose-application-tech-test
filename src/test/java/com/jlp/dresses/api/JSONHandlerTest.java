package com.jlp.dresses.api;

import com.jlp.dresses.TestHelper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSONHandlerTest {

    @Test
    public void testGetAllProductInfos() {
        Map<String, ProductInfo> productInfoMap = JSONHandler.getAllProductInfos(TestHelper.getJSONString());

        assertNotNull(productInfoMap);
        assertEquals(24, productInfoMap.size());

        ProductInfo discountedDressProductInfo = productInfoMap.get("5671705");
        assertTrue(discountedDressProductInfo.isDiscounted());
        assertEquals(new BigDecimal("12.50"), discountedDressProductInfo.getNowPrice());
        assertEquals("GBP", discountedDressProductInfo.getCurrency());
        assertEquals(2, discountedDressProductInfo.getColorSwatches().size());

        ProductInfo nonDiscountedDressProductInfo = productInfoMap.get("5597952");
        assertFalse(nonDiscountedDressProductInfo.isDiscounted());
        assertEquals(BigDecimal.TEN.setScale(2), nonDiscountedDressProductInfo.getNowPrice());
        assertEquals("GBP", nonDiscountedDressProductInfo.getCurrency());
        assertEquals(3, nonDiscountedDressProductInfo.getColorSwatches().size());
    }

    @Test
    public void testConvertJSONStringToMap() {
        Map<String, Object> jsonMap = JSONHandler.convertJSONStringToMap(TestHelper.getJSONString());

        assertNotNull(jsonMap);
        assertNotNull(jsonMap.get("products"));
    }

    @Test
    public void convertJSONStringToMap_NotJSON() {
        // assertion to check that no exception is thrown from method
        assertNull(JSONHandler.convertJSONStringToMap("-"));
    }

}

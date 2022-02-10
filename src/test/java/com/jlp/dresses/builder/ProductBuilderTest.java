package com.jlp.dresses.builder;

import com.jlp.dresses.api.ProductInfo;
import com.jlp.dresses.domain.ColorSwatch;
import com.jlp.dresses.domain.PriceLabelType;
import com.jlp.dresses.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

public class ProductBuilderTest {

    private ProductInfo productInfo;

    @Before
    public void setUp() {
        productInfo = new ProductInfo();

        productInfo.setTitle("A Dress");
        productInfo.setDiscounted(true);
        productInfo.setCurrency("GBP");
        productInfo.setWasPrice(BigDecimal.TEN);
        productInfo.setNowPrice(BigDecimal.ONE);
        productInfo.getColorSwatches().add(new ColorSwatch());
    }

    @Test
    public void testFromProductInfo_ProductDiscounted_ShowWasNowRequested() {
        Product product = ProductBuilder.fromProductInfo("123", productInfo, PriceLabelType.ShowWasNow);

        assertNotNull(product);
        assertEquals("123", product.getProductId());
        assertEquals("A Dress", product.getTitle());
        assertEquals("£1.00", product.getNowPrice());
        assertEquals("9", product.getDiscountAmount().toString());
        assertEquals("Was £10.00, now £1.00", product.getPriceLabel());
        assertEquals(1, product.getColorSwatches().size());
    }

    @Test
    public void testFromProductInfo_ProductDiscounted_ShowWasThenNowRequested() {
        productInfo.setThen1Price(new BigDecimal("7.50"));
        Product product = ProductBuilder.fromProductInfo("123", productInfo, PriceLabelType.ShowWasThenNow);

        assertNotNull(product);
        assertEquals("123", product.getProductId());
        assertEquals("A Dress", product.getTitle());
        assertEquals("£1.00", product.getNowPrice());
        assertEquals("9", product.getDiscountAmount().toString());
        assertEquals("Was £10.00, then £7.50, now £1.00", product.getPriceLabel());
        assertEquals(1, product.getColorSwatches().size());
    }

    @Test
    public void testFromProductInfo_ProductDiscounted_ShowWasThenNowRequested_WithThen2() {
        productInfo.setThen1Price(new BigDecimal("7.50"));
        productInfo.setThen2Price(new BigDecimal("6.50"));

        Product product = ProductBuilder.fromProductInfo("123", productInfo, PriceLabelType.ShowWasThenNow);

        assertNotNull(product);
        assertEquals("123", product.getProductId());
        assertEquals("A Dress", product.getTitle());
        assertEquals("£1.00", product.getNowPrice());
        assertEquals("9", product.getDiscountAmount().toString());
        assertEquals("Was £10.00, then £6.50, now £1.00", product.getPriceLabel());
        assertEquals(1, product.getColorSwatches().size());
    }

    @Test
    public void testFromProductInfo_ProductDiscounted_ShowPercDiscountRequested() {
        Product product = ProductBuilder.fromProductInfo("123", productInfo, PriceLabelType.ShowPercDiscount);

        assertNotNull(product);
        assertEquals("123", product.getProductId());
        assertEquals("A Dress", product.getTitle());
        assertEquals("£1.00", product.getNowPrice());
        assertEquals("9", product.getDiscountAmount().toString());
        assertEquals("90% off - now £1.00", product.getPriceLabel());
        assertEquals(1, product.getColorSwatches().size());
    }

    @Test
    public void testFromProductInfo_ProductNotDiscounted() {
        productInfo.setDiscounted(false);
        productInfo.setWasPrice(null);

        Product product = ProductBuilder.fromProductInfo("123", productInfo, PriceLabelType.ShowWasNow);

        assertNotNull(product);
        assertEquals("123", product.getProductId());
        assertEquals("A Dress", product.getTitle());
        assertEquals("£1.00", product.getNowPrice());
        assertNull(product.getDiscountAmount());
        assertNull(product.getPriceLabel());
        assertEquals(1, product.getColorSwatches().size());
    }

}

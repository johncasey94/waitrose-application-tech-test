package com.jlp.dresses.domain;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductComparatorTest {

    private ProductComparator classUnderTest;

    private Product product1;

    private Product product2;

    @Before
    public void setUp() {
        classUnderTest = new ProductComparator();

        product1 = new Product();
        product2 = new Product();
    }

    @Test
    public void testCompare() {
        assertEquals(0, classUnderTest.compare(product1, product2));

        product1.setDiscountAmount(BigDecimal.ONE);
        product2.setDiscountAmount(BigDecimal.TEN);

        assertEquals(1, classUnderTest.compare(product1, product2));
        assertEquals(0, classUnderTest.compare(product1, product1));
        assertEquals(-1, classUnderTest.compare(product2, product1));
    }

}

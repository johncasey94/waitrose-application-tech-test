package com.jlp.dresses;

import com.jlp.dresses.domain.PriceLabelType;
import com.jlp.dresses.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DressesControllerTest {

    @Mock
    private DressesService mockDressesService;

    @InjectMocks
    private DressesController classUnderTest;

    @Before
    public void setUp() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());
        products.add(new Product());
        List<Product> discountedProducts = new ArrayList<>();
        discountedProducts.add(new Product());

        when(mockDressesService.getProducts(any(PriceLabelType.class), eq(false))).thenReturn(products);
        when(mockDressesService.getProducts(any(PriceLabelType.class), eq(true))).thenReturn(discountedProducts);
    }

    @Test
    public void testGetAllDresses() {
        assertEquals(4, classUnderTest.getAllDresses("ShowWasNow").size());
    }

    @Test
    public void testGetDiscountedDresses() {
        assertEquals(1, classUnderTest.getDiscountedDresses("ShowWasNow").size());
    }


}

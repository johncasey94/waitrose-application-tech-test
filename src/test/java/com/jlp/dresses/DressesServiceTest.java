package com.jlp.dresses;

import com.jlp.dresses.api.JohnLewisAPICaller;
import com.jlp.dresses.domain.PriceLabelType;
import com.jlp.dresses.domain.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DressesServiceTest {

    @Mock
    private JohnLewisAPICaller johnLewisAPICaller;

    @InjectMocks
    private DressesService classUnderTest;

    @Before
    public void setUp() {
        when(johnLewisAPICaller.callJohnLewisAPI()).thenReturn(TestHelper.getJSONString());
    }

    @Test
    public void testGetProducts_AllProducts() {
        List<Product> products = classUnderTest.getProducts(PriceLabelType.ShowWasNow, false);

        assertNotNull(products);
        assertEquals(24, products.size());
    }

    @Test
    public void testGetProducts_DiscountedProducts() {
        List<Product> products = classUnderTest.getProducts(PriceLabelType.ShowWasNow, true);

        assertEquals(6, products.size());

        // check the ordering of the products is correct
        // the discount of the nth product in the list should be greater
        // or equal to the discount amount of the nth+1 product
        for(int i = 0; i < products.size() - 1; i++) {
            assertTrue(products.get(i).getDiscountAmount().compareTo(products.get(i + 1).getDiscountAmount()) >= 0);
        }
    }

}

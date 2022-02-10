package com.jlp.dresses.api;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.web.client.RestTemplateBuilder;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class JohnLewisAPICallerTest {

    private JohnLewisAPICaller classUnderTest;

    private RestTemplateBuilder restTemplateBuilder;

    @Before
    public void setUp() {
        restTemplateBuilder = new RestTemplateBuilder();
        classUnderTest = new JohnLewisAPICaller(restTemplateBuilder);
    }

    @Test
    public void testCallJohnLewisAPI() {
        String response = classUnderTest.callJohnLewisAPI();
        assertNotNull(response);
        assertTrue(response.length() > 0);
        assertTrue(response.startsWith("{"));
        assertTrue(response.endsWith("}"));
    }

}

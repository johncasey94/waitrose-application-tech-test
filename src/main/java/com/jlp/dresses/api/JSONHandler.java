package com.jlp.dresses.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jlp.dresses.builder.ColorSwatchBuilder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is used to handle the raw JSON String returned from the John Lewis API
 * To keep things simple, we will either convert the entire JSON response to a Map using Jackson,
 * or pick out the details we need and put them into a class we've defined ourselves
 *
 * @author John Casey
 */
public class JSONHandler {

    public static Map<String, Object> convertJSONStringToMap(String json) {
        try {
            return new ObjectMapper().readValue(json, Map.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static Map<String, ProductInfo> getAllProductInfos(String json) {
        Map<String, Object> jsonMap = convertJSONStringToMap(json);
        Map<String, ProductInfo> productInfoMap = new HashMap<>();

        List<Object> jsonProducts = (List<Object>) jsonMap.get("products");

        for (Object o : jsonProducts) {
            if(o instanceof Map) {
                Map product = (Map) o;

                productInfoMap.put((String) product.get("productId"), buildProductInfo(product));
            }
        }

        return productInfoMap;
    }

    private static ProductInfo buildProductInfo(Map<String, Object> productMap) {
        ProductInfo productInfo = new ProductInfo();

        productInfo.setTitle((String) productMap.get("title"));

        Map priceMap = (Map) productMap.get("price");

        BigDecimal wasPrice = getPriceValue(priceMap.get("was"));
        BigDecimal nowPrice = getPriceValue(priceMap.get("now"));

        productInfo.setWasPrice(wasPrice);
        productInfo.setThen1Price(getPriceValue(priceMap.get("then1")));
        productInfo.setThen2Price(getPriceValue(priceMap.get("then2")));
        productInfo.setNowPrice(nowPrice);
        productInfo.setCurrency((String) priceMap.get("currency"));
        productInfo.setDiscounted(null != wasPrice && nowPrice.compareTo(wasPrice) < 0);

        for(Object colorSwatchMap : (ArrayList) productMap.get("colorSwatches")) {
            productInfo.getColorSwatches().add(ColorSwatchBuilder.fromMap((Map<String, Object>) colorSwatchMap));
        }

        return productInfo;
    }

    /**
     * Prices in the response can either be a single price (e.g. 10GBP) when the price object is a string,
     * or a range (e.g. from 8GBP to 12GBP) when the price object is a Map containing a from and to price.
     * The requirements don't explicitly say how this should be returned to caller if the price is a range,
     * so for this example we'll just use the "from" price if a range of prices is returned for a product.
     *
     * If we can't work out the price we'll just return null and it will be blank in our response
     *
     * @param price - the price element from the API response
     * @return BigDecimal - the price of the product
     */
    private static BigDecimal getPriceValue(Object price) {
        if(price instanceof Map) {
            return new BigDecimal((String) ((Map) price).get("from"));
        } else if (price instanceof String && !"".equals(price)) {
            return new BigDecimal((String) price);
        } else {
            return null;
        }
    }

}
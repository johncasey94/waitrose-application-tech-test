package com.jlp.dresses;

import com.jlp.dresses.api.JSONHandler;
import com.jlp.dresses.api.JohnLewisAPICaller;
import com.jlp.dresses.api.ProductInfo;
import com.jlp.dresses.builder.ProductBuilder;
import com.jlp.dresses.domain.PriceLabelType;
import com.jlp.dresses.domain.Product;
import com.jlp.dresses.domain.ProductComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Class to contain the business logic used by the Dresses RESTful webservice
 *
 * @author John Casey
 */
@Service
public class DressesService {

    @Autowired
    private JohnLewisAPICaller johnLewisAPICaller;

    public List<Product> getProducts(PriceLabelType priceLabelType, boolean onlyShowDiscounts) {
        String jsonFromAPI = johnLewisAPICaller.callJohnLewisAPI();
        Map<String, ProductInfo> productInfoMap = JSONHandler.getAllProductInfos(jsonFromAPI);

        List<Product> products = new ArrayList<>();

        for(Map.Entry<String, ProductInfo> productInfoMapEntry : productInfoMap.entrySet()) {
            ProductInfo productInfo = productInfoMapEntry.getValue();

            if(!onlyShowDiscounts || productInfo.isDiscounted()) {
                products.add(ProductBuilder.fromProductInfo(productInfoMapEntry.getKey(), productInfo, priceLabelType));
            }
        }

        // if only discounted products requested then sort by highest discounts first
        if(onlyShowDiscounts) {
            Collections.sort(products, new ProductComparator());
        }

        return products;
    }

}

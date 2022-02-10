package com.jlp.dresses.domain;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        if(null != o1.getDiscountAmount() && null != o2.getDiscountAmount()) {
            return o2.getDiscountAmount().compareTo(o1.getDiscountAmount());
        } else {
            return 0;
        }
    }

}

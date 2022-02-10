package com.jlp.dresses.builder;

import com.google.common.collect.ImmutableMap;
import com.jlp.dresses.api.ProductInfo;
import com.jlp.dresses.domain.PriceLabelType;
import com.jlp.dresses.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductBuilder {

    private final static ImmutableMap<String, String> CURRENCY_CODE_TO_SYMBOL_MAP =
            ImmutableMap.<String, String>builder()
            .put("GBP", "£")
            .put("EUR", "€")
            .put("USD", "$")
            .build();

    private final static BigDecimal BIG_DECIMAL_100 = new BigDecimal("100");

    public static Product fromProductInfo(String productId, ProductInfo productInfo, PriceLabelType priceLabelTypeEnum) {
        Product product = new Product();

        String currencySymbol = CURRENCY_CODE_TO_SYMBOL_MAP.get(productInfo.getCurrency());

        product.setProductId(productId);
        product.setTitle(productInfo.getTitle());
        product.setNowPrice(currencySymbol + formatDecimalAmount(productInfo.getNowPrice()));
        if(null != productInfo.getWasPrice()) {
            product.setPriceLabel(createPriceLabel(currencySymbol, productInfo, priceLabelTypeEnum));
        }
        product.getColorSwatches().addAll(productInfo.getColorSwatches());

        if(productInfo.isDiscounted()) {
            product.setDiscountAmount(productInfo.getWasPrice().subtract(productInfo.getNowPrice()));
        }

        return product;
    }

    private static String createPriceLabel(String currencySymbol, ProductInfo productInfo, PriceLabelType priceLabelTypeEnum) {
        StringBuilder stringBuilder = new StringBuilder();

        if (priceLabelTypeEnum == PriceLabelType.ShowWasNow) {
            stringBuilder.append("Was ");
            stringBuilder.append(currencySymbol);
            stringBuilder.append(formatDecimalAmount(productInfo.getWasPrice()));
            stringBuilder.append(", ");
        } else if (priceLabelTypeEnum == PriceLabelType.ShowWasThenNow) {
            stringBuilder.append("Was ");
            stringBuilder.append(currencySymbol);
            stringBuilder.append(formatDecimalAmount(productInfo.getWasPrice()));
            if(null != productInfo.getThen2Price()) {
                stringBuilder.append(", then ");
                stringBuilder.append(currencySymbol);
                stringBuilder.append(formatDecimalAmount(productInfo.getThen2Price()));
            } else if (null != productInfo.getThen1Price()) {
                stringBuilder.append(", then ");
                stringBuilder.append(currencySymbol);
                stringBuilder.append(formatDecimalAmount(productInfo.getThen1Price()));
            }
            stringBuilder.append(", ");
        } else if (priceLabelTypeEnum == PriceLabelType.ShowPercDiscount) {
            stringBuilder.append(calculatePercentageDiscount(productInfo));
            stringBuilder.append("% off - ");
        }

        stringBuilder.append("now ");
        stringBuilder.append(currencySymbol);
        stringBuilder.append(formatDecimalAmount(productInfo.getNowPrice()));

        return stringBuilder.toString();
    }

    private static String calculatePercentageDiscount(ProductInfo productInfo) {
        BigDecimal was = productInfo.getWasPrice();
        BigDecimal now = productInfo.getNowPrice();

        BigDecimal percentageDifference = was.divide(now,2, RoundingMode.HALF_UP);
        return BIG_DECIMAL_100.subtract(percentageDifference).setScale(0, RoundingMode.HALF_UP).toString();
    }

    private static String formatDecimalAmount(BigDecimal bigDecimalAmount) {
        // if the price amount is an integer AND is greater than 10 do not show any trailing zeros,
        // in all other scenarios show 2 trailing zeros
        if(bigDecimalAmount.stripTrailingZeros().scale() <= 0 &&
                bigDecimalAmount.compareTo(BigDecimal.TEN) > 0) {
            bigDecimalAmount = bigDecimalAmount.setScale(0);
        } else {
            bigDecimalAmount = bigDecimalAmount.setScale(2);
        }

        return bigDecimalAmount.toString();
    }

}

package com.jlp.dresses;

import com.jlp.dresses.domain.PriceLabelType;
import com.jlp.dresses.domain.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Class containing all RESTful mappings for incoming HTTP requests, currently only 2 GET operations exist for the
 * dresses resource (GET everything, or GET dresses with a discount),
 * but in future iterations more can be added in here (for example a POST for purchasing a dress, a DELETE for updating stock etc)
 */
@RestController
@RequestMapping("/dresses")
public class DressesController {

    private final DressesService dressesService;

    public DressesController(DressesService dressesService) {
        this.dressesService = dressesService;
    }

    @GetMapping
    public List<Product> getAllDresses(@RequestParam(defaultValue = "ShowWasNow") String labelType) {
        PriceLabelType priceLabelType = PriceLabelType.getEnumFromString(labelType);
        List<Product> products = dressesService.getProducts(priceLabelType, false);
        return products;
    }

    @GetMapping("/discounts")
    public List<Product> getDiscountedDresses(@RequestParam(defaultValue = "ShowWasNow") String labelType) {
        PriceLabelType priceLabelType = PriceLabelType.getEnumFromString(labelType);
        List<Product> products = dressesService.getProducts(priceLabelType, true);
        return products;
    }

}

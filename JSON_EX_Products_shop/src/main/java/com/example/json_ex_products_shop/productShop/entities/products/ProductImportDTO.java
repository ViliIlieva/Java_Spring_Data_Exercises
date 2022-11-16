package com.example.json_ex_products_shop.productShop.entities.products;

import java.math.BigDecimal;

public class ProductImportDTO {
    private String name;
    private BigDecimal price;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}

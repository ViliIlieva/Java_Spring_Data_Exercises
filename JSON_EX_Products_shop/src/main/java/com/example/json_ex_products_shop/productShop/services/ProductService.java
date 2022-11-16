package com.example.json_ex_products_shop.productShop.services;

import com.example.json_ex_products_shop.productShop.entities.products.ProductWithoutBuyerDTO;

import java.util.List;

public interface ProductService {
    List<ProductWithoutBuyerDTO> getProductsInPriceRangeForSell(
            float from, float to);
}

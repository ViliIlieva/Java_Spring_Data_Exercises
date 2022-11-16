package com.example.json_ex_products_shop.productShop.services;

import com.example.json_ex_products_shop.productShop.entities.users.UserWithSoldProductsDTO;

import java.util.List;

public interface UserService {
    List<UserWithSoldProductsDTO> getUserWithSoldProducts();
}

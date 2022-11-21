package com.example.json_ex_products_shop.productShop.services;

import com.example.json_ex_products_shop.productShop.entities.users.User;
import com.example.json_ex_products_shop.productShop.entities.users.UserWithSoldProductsDTO;
import com.example.json_ex_products_shop.productShop.entities.users.UserWithSoldProductsDetailDTO;

import java.util.List;

public interface UserService {
    List<UserWithSoldProductsDTO> getUserWithSoldProducts();

    List<UserWithSoldProductsDetailDTO> getUserWithSoldProductsOrderByCount();
}

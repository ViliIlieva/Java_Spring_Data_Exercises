package com.example._06.services;

import com.example._06.entities.Shampoo;

import java.util.List;
import java.util.Set;

public interface ShampooService {
    List<Shampoo> findByBrand(String brand);

    List<Shampoo> findByBrandAndSize(String brand, String size);
    //1
    List<Shampoo> findBySizeOrderById(String size);
    //2
    List<Shampoo> findBySizeOrLabelIdOrderByPrice(String size, long labelId);
    //3
    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(String price);
    //7
    List<Shampoo> findByIngredients(Set<String> ingredients);

}

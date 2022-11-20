package com.example.xml_exercise.productShop.services;


import com.example.xml_exercise.productShop.entities.products.ExportProductsInRangeDTO;

public interface ProductService {
   ExportProductsInRangeDTO getInRange(float from, float to);

}

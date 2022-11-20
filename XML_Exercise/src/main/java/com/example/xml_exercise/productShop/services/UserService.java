package com.example.xml_exercise.productShop.services;

import com.example.xml_exercise.productShop.entities.users.ExportSellersDTO;

public interface UserService {

    ExportSellersDTO findAllWithSoldProducts();
}

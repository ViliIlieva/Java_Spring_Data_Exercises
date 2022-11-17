package com.example.xml_exercise.productShop.repositories;

import com.example.xml_exercise.productShop.entities.products.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

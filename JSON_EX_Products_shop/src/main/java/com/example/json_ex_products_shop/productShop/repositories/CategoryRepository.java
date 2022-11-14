package com.example.json_ex_products_shop.productShop.repositories;

import com.example.json_ex_products_shop.productShop.entities.categories.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}

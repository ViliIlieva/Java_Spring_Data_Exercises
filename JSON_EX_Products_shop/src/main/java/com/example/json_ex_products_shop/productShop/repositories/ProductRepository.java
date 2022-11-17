package com.example.json_ex_products_shop.productShop.repositories;

import com.example.json_ex_products_shop.productShop.entities.categories.CategoryStats;
import com.example.json_ex_products_shop.productShop.entities.products.Product;
import com.example.json_ex_products_shop.productShop.entities.products.ProductWithoutBuyerDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select new com.example.json_ex_products_shop.productShop.entities.products.ProductWithoutBuyerDTO(" +
            "p.name, p.price, p.seller.firstName, p.seller.lastName)" +
            " from Product p" +
            " where p.price > :rangeStart and p.price < :rangeEnd" +
            " and p.buyer is null" +
            " order by p.price asc")
    List<ProductWithoutBuyerDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPriceAsc(
            BigDecimal rangeStart, BigDecimal rangeEnd);

    @Query("select new com.example.json_ex_products_shop.productShop.entities.categories.CategoryStats(" +
            "c.name, count(p), AVG(p.price), SUM(p.price))" +
            " from Product p" +
            " join p.categories c" +
            " group by c")
    List<CategoryStats> getCategoryStats();
}

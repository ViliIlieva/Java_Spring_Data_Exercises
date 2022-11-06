package com.example._06.repositories;

import com.example._06.entities.Shampoo;
import com.example._06.entities.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Repository
public interface ShampooRepository extends JpaRepository<Shampoo, Long> {


    List<Shampoo>findByBrand(String brand);

    List<Shampoo>findByBrandAndSize(String brand, Size size);
    //1
    List<Shampoo> findBySizeOrderById(Size parsed);
    //2
    List<Shampoo> findBySizeOrLabelIdOrderByPrice(Size parsed, long labelId);
    //3
    List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(BigDecimal price);
    //7
    @Query("SELECT s from Shampoo as s" +  //7
            " join s.ingredients as i" +
            " where i.name IN :ingredients")
    List<Shampoo> findByIngredients(@Param (value = "ingredients")
                                    Set<String> ingredients);
}

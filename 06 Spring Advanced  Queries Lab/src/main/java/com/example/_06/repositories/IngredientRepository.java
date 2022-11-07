package com.example._06.repositories;

import com.example._06.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    //4
    List<Ingredient> findByNameStartingWith(String givenLetter);
    //5
    List<Ingredient> findByNameInOrderByPrice(Set<String> ingredients);
    //9
    void deleteByName(String name);
    //10
    @Query("update Ingredient as i set i.price = i.price*1.10")
    @Modifying
    void updateAllPrice();
}

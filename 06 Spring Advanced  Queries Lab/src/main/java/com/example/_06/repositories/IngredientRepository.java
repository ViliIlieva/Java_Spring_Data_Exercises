package com.example._06.repositories;

import com.example._06.entities.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    //4
    List<Ingredient> findByNameStartingWith(String givenLetter);
}

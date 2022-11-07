package com.example._06.services;

import com.example._06.entities.Ingredient;

import java.util.List;
import java.util.Set;

public interface IngredientService {
    //4
    List<Ingredient> findByNameStartingWith(String givenLetter);
    //5
    List<Ingredient> findByNameInOrderByPrice(Set<String> ingredients);
    //9
    void deleteByName(String name);

    void updateAllPrice();
}

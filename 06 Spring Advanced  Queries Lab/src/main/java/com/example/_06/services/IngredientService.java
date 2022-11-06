package com.example._06.services;

import com.example._06.entities.Ingredient;

import java.util.List;

public interface IngredientService {
    //4
    List<Ingredient> findByNameStartingWith(String givenLetter);
}

package com.example._06.services;

import com.example._06.entities.Ingredient;
import com.example._06.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImpl implements IngredientService{

    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override//4
    public List<Ingredient> findByNameStartingWith(String givenLetter) {
        return this.ingredientRepository.findByNameStartingWith(givenLetter);
    }
}

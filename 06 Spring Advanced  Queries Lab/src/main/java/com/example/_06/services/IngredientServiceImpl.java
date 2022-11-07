package com.example._06.services;

import com.example._06.entities.Ingredient;
import com.example._06.repositories.IngredientRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

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

    @Override//5
    public List<Ingredient> findByNameInOrderByPrice(Set<String> ingredients) {
        return this.ingredientRepository.findByNameInOrderByPrice (ingredients);
    }

    @Override
    @Transactional
    public void deleteByName(String name) {
        this.ingredientRepository.deleteByName(name);
    }

    @Override
    @Transactional
    public void updateAllPrice(){
        this.ingredientRepository.updateAllPrice();
    }
}

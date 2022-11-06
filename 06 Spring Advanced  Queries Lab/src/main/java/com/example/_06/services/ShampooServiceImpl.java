package com.example._06.services;

import com.example._06.entities.Shampoo;
import com.example._06.entities.Size;
import com.example._06.repositories.ShampooRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class ShampooServiceImpl implements ShampooService {

    private final ShampooRepository shampooRepository;

    public ShampooServiceImpl(ShampooRepository shampooRepository) {
        this.shampooRepository = shampooRepository;
    }

    @Override
    public List<Shampoo> findByBrand(String brand) {
        return this.shampooRepository.findByBrand(brand);
    }

    @Override
    public List<Shampoo> findByBrandAndSize(String brand, String size){
        Size parsed = Size.valueOf(size.toUpperCase());
        return this.shampooRepository.findByBrandAndSize(brand, parsed);
    }

    @Override//1
    public List<Shampoo> findBySizeOrderById(String size){
        Size parsed = Size.valueOf(size.toUpperCase());
        return this.shampooRepository.findBySizeOrderById(parsed);
    }

    @Override//2
    public List<Shampoo> findBySizeOrLabelIdOrderByPrice(String size, long labelId) {
        Size parsed = Size.valueOf(size.toUpperCase());
        return this.shampooRepository.findBySizeOrLabelIdOrderByPrice(parsed, labelId);
    }
    @Override//3
    public List<Shampoo> findByPriceGreaterThanOrderByPriceDesc(String price){
        BigDecimal parsed = new BigDecimal (price);
        return this.shampooRepository.findByPriceGreaterThanOrderByPriceDesc (parsed);
    }

    @Override//7
    public List<Shampoo> findByIngredients(Set<String> ingredients) {
        return this.shampooRepository.findByIngredients (ingredients);
    }
}

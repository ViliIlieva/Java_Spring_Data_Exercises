package com.example._06;

import com.example._06.entities.Ingredient;
import com.example._06.services.IngredientService;
import com.example._06.services.ShampooService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class Main implements CommandLineRunner { //вече има psvm затова казваме
    //да го наследи и когато вижда Runner значи че трябва да стартира run метода

    private final ShampooService shampooService;
    private final IngredientService ingredientService;

    @Autowired   //конструктор
    public Main(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override //метода от това че имплементира Комманд Лайн
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner (System.in);

        //1.	Select Shampoos by Size
//        String size = scanner.nextLine ();
//        for (Shampoo shampoo : this.shampooService.findBySizeOrderById (size)) {
//            System.out.println (shampoo);
//        }

        //2.	Select Shampoos by Size or Label
//        String size = scanner.nextLine ();
//        long labelId = Long.parseLong (scanner.nextLine ());
//
//        for (Shampoo shampoo : this.shampooService.findBySizeOrLabelIdOrderByPrice (size, labelId)) {
//            System.out.println (shampoo);
//        }

        //3.	Select Shampoos by Price
//        String price = scanner.nextLine ();
//
//        for (Shampoo shampoo : this.shampooService.findByPriceGreaterThanOrderByPriceDesc (price)) {
//            System.out.println (shampoo);
//        }

        //4.	Select Ingredients by Name
//        String givenLetter = scanner.nextLine ();
//
//        for (Ingredient ingredient : this.ingredientService.findByNameStartingWith (givenLetter)) {
//            System.out.println (ingredient);
//        }

        //5.	Select Ingredients by Names



        //7.	Select Shampoos by Ingredients
//        Set<String> ingredients = new HashSet<> ();
//        String nextLine = scanner.nextLine ();
//        while (!nextLine.isBlank ()){
//            ingredients.add(nextLine);
//
//            nextLine = scanner.nextLine ();
//        }
//        for (Shampoo shampoo : this.shampooService.findByIngredients (ingredients)) {
//            System.out.println (shampoo);
//        }


    }
}
